package application;

//import tools.Request;
import java.net.Socket;
import java.util.HashMap;

import network.NetworkController;

public class ApplicationController {
	
	HashMap<String,String> requestworker ;
	HashMap<String,String> requestclient ;
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
	
	public void Rearrange()
	{
		//rearrange the data from dropped workers.
	}
	
	public static void main(String[] args){
		ApplicationController handler= new ApplicationController();
	}
}

