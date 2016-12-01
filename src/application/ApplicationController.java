package application;

//import tools.Request;
import java.net.Socket;
import java.util.HashMap;

import network.NetworkController;

public class ApplicationController {
	
	HashMap<String,String> requestworker ;
	HashMap<String,String> requestclient ;
	HashMap<String,String> workerhaspath;
	//create hashtables to contain the worker name (or connection) and the received request from the client
	
	//initialize class
	public ApplicationController()
	{
		requestworker = new HashMap<String,String>();
		requestclient = new HashMap<String,String>();
	//initialize class attributes	
	}
	
	public void callApp(String conid,String message, String role)
	{
		if (role.equals("client"))
		{
			requestclient.put(message, conid);
		}
		else if (role.equals("worker"))
		{
			requestworker.put(message, conid);
		}
	
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
		ApplicationController handler= new ApplicationController();
	}

}

