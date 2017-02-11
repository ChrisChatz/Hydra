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
	

	public NetworkController(int port , String role){
		this.role = role;
		this.startServerSocketListener(port);
		this.socketmap= new HashMap<String, Socket>();
		this.slmap = new HashMap<String, SocketListener>();
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
		apiC.AliveConnections(key, this.role);
	}
	
	public void getMessageFromSL(String message, String slkey){
		apiC.callApp(slkey, message, this.role);
	}
	
	public void setApplicationController(ApplicationController apiC){
		this.apiC = apiC;
	}
	
	public HashMap<String, Socket> getSocketMap(){
		return this.socketmap;
	}

	public void sendRequest(Request req,String connId){
		try{
			String m=req.toString();
			Socket socket = socketmap.get(connId);
			StreamHandler.outputStream(m, socket);
		}catch(Exception e){
			System.out.println("Connection lost with: " + connId);
			this.socketmap.remove(connId);
			this.slmap.remove(connId);
		}
	}

}
