package application;

import java.util.HashMap;

import network.NetworkController;
import tools.Request;

public class ApplicationController {
	
	HashMap<String,String> requestworker ;
	HashMap<String,String> requestclient ;
	HashMap<String,String> workerhaspath;
	private int counter=1;
	NetworkController workerNc;
	NetworkController clientNc;
	
	//create hashtables to contain the worker name (or connection) and the received request from the client
	
	//initialize class
	public ApplicationController()
	{
		requestworker = new HashMap<String,String>();
		requestclient = new HashMap<String,String>();
		NetworkController workerNc=new NetworkController(4321,"client");
		NetworkController clientNc=new NetworkController(4321,"worker");
	//initialize class attributes	
	}
	
	public void callApp(String conid,String message, String role)//add to hashmaps
	{
		
		if (role.equals("client"))
		{
			requestclient.put(conid,message);
			Request req=new Request(conid,message);
			req.setWorker_id(workerWho(req,counter));//set worker id
			counter=counter+1;
			workerNc.sendRequest(req,req.getWorker_id());
		}
		else if (role.equals("worker"))
		{
			Request req = new Request("","");
			req.stringToReq(message);
			requestworker.put(req.getWorker_id(),req.getAnswer());
			clientNc.sendRequest(req, req.getConnection_id());
		}
		
	
	}
	
	public String workerWho(Request r,int i){//ApplicationController decides where to send the Clients request
		
		if(i%2==0)
		{
			
		}
		else
		{
			
		}
		
		return "";
	}
	
	
	public HashMap<String, String> getRequestworker() {
		return requestworker;
	}

	public HashMap<String, String> getRequestclient() {
		return requestclient;
	}

	public HashMap<String, String> getWorkerhaspath() {
		return workerhaspath;
	}

	public void Rearrange()
	{
		//rearrange the data from dropped workers.
	}
	
	public static void main(String[] args){
		ApplicationController appCon= new ApplicationController();
	}

}

