package application;

import java.util.HashMap;

import network.NetworkController;
import tools.Request;

public class ApplicationController {
	
	HashMap<String,String> requestworker ;
	HashMap<String,String> requestclient ;
	HashMap<String,String> workertoclient;// socket of the client and the socket of the worker
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
		NetworkController workerNc=new NetworkController(1888,"worker");
		NetworkController clientNc=new NetworkController(4321,"client");
	//initialize class attributes	
	}
	
	public void callApp(String conid,String message, String role)//add to hashmaps
	{
		
		if (role.equals("client"))
		{
			requestclient.put(conid,message);
			Request req=new Request(conid,message);
			req.setWorker_id(workerWho(req,counter));//set worker id
			workertoclient.put(req.getWorker_id(),conid);
			counter=counter+1;
			workerNc.sendRequest(req,req.getWorker_id());
		}
		else if (role.equals("worker"))
		{
			Request req = new Request("",""); // we got to find the question of the client
			req.setAnswer(message);
			req.setWorker_id(conid);
			requestworker.put(req.getWorker_id(),req.getAnswer());
			req.setConnection_id(workertoclient.get(conid));
			clientNc.sendRequest(req, req.getConnection_id());
		}
	
	}
	
	public String workerWho(Request r,int i){//ApplicationController decides where to send the Clients request
		String answer; // that's the name of the worker responsible
		if(i%2==0)
		{
			
			workerhaspath.put("0",r.getQuestionloc());// we have to add the put the name of the worker as key and the path
			answer = "0";
		}
		else
		{
			workerhaspath.put("1",r.getQuestionloc());// we have to add the put the name of the worker as key and the path
			answer = "1";
		}
		
		return answer;
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

