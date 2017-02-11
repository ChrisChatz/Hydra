package application;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Set;

import network.NetworkController;
import tools.Request;

public class ApplicationController {

	NetworkController clientNc;
	NetworkController workerNc;
	WorkerRepCollection wrp ;
	//create hashtables to contain the worker name (or connection) and the received request from the client
	
	//initialize class
	public ApplicationController()
	{
		clientNc=new NetworkController(4321, "client");
		clientNc.setApplicationController(this);
		workerNc=new NetworkController(1888, "worker");
		workerNc.setApplicationController(this);
		wrp = new WorkerRepCollection();
	//initialize class attributes	
	}
	
	public void AliveConnections(String conid,String role)
	{
		if (role.equals("worker")){
			System.out.println("This worker is up and running "+ conid);
			WorkerRep w = new WorkerRep(conid,true,new ArrayList<String>());
			wrp.collection.add(w);
		}
	}
	
	public void callApp(String conid,String message, String role)//add to hashmaps
	{
		if(role.equals("client")){
			System.out.println("Received new message from client: " + conid);
			Request re = new Request(conid, message);
			this.sendRequestToWorker(re);
		}
		if(role.equals("worker")){
			System.out.println("Received new message from worker: " + conid);
			Request re = Request.fromString(message);
			System.out.println(re.getConnection_id());
			this.clientNc.sendRequest(re, re.getConnection_id());
		}
	}
	
	public void sendRequestToWorker(Request re){
		String slkey = this.wrp.whoWillServeQuestion(re.getQuestionloc());
		workerNc.sendRequest(re, slkey);
	}
	
	public static void main(String[] args){
		ApplicationController appCon= new ApplicationController();
	}

}

