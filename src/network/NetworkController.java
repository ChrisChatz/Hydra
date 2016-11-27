package network;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

public class NetworkController {
	ServerSocketListener sslistener;
	HashMap<String, Socket> socketmap;
	HashMap<String, SocketListener> slmap;
	
	public NetworkController(int port){
		startServerSocketListener(port);
		socketmap= new HashMap<String, Socket>();
		slmap = new HashMap<String, SocketListener>();
		
	}
	public void startServerSocketListener(int port){
		try{
			sslistener= new ServerSocketListener(port, this);
			sslistener.open();
			sslistener.start();
		
		}catch(IOException e){e.printStackTrace();}
	}
	
	public void addSocket(Socket s){
		String key = s.getRemoteSocketAddress().toString()+ s.getPort();
		socketmap.put(key, s);
		SocketListener sl = new SocketListener(s, this);
		slmap.put(key,sl);
		sl.start();
			
	}
	public void getMessageFromSL(String message, String slkey){
		System.out.println("Received message "+ message +" from "+slkey);
	}
	public static void main(String[] args){
		NetworkController theman = new NetworkController(4321);
	}
}
