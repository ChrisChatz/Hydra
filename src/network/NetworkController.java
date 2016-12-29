package network;
import application.ApplicationController;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

import tools.Request;
import tools.StreamHandler;

public class NetworkController {
	ServerSocketListener sslistener;
	HashMap<String, Socket> socketmap;
	HashMap<String, SocketListener> slmap;
	private String role;
	private ApplicationController apiC;
	StreamHandler strHan;
	

	public NetworkController(int port , String role){
		this.role = role;
		startServerSocketListener(port);
		socketmap= new HashMap<String, Socket>();
		slmap = new HashMap<String, SocketListener>();
		
	}

	public void startServerSocketListener(int port){
		try{
			sslistener= new ServerSocketListener(port, this);
			sslistener.open();
			sslistener.start();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void addSocket(Socket s){
		String key = s.getRemoteSocketAddress().toString();
		System.out.println(key);
		socketmap.put(key, s);
		SocketListener sl = new SocketListener(s, this);
		slmap.put(key,sl); 
		sl.start();
	}
	
	public void getMessageFromSL(String message, String slkey){
 		apiC.callApp(slkey, message, this.role); // creates the hashmaps of clients and workers
		
	}



	public void sendRequest(Request req,String connId)
	{		
			String m=req.toString();
			Socket socket = socketmap.get(connId);
			strHan.outputStream(m, socket);
					
	}
	
	
}
