package network;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

import tools.Request;

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
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void addSocket(Socket s){
		String key = s.getRemoteSocketAddress().toString()+ s.getPort();
		socketmap.put(key, s);//Add new socket to a HashMap
		SocketListener sl = new SocketListener(s, this);
		slmap.put(key,sl);//Add a new SocketListener to a HashMap
		sl.start();
	}
	
	public void getMessageFromSL(String message, String slkey){
		Request req = new Request(message,slkey);
		System.out.println(req.toString());
		System.out.println("Received message "+ message +" from "+slkey);
	}
	
	public void sendRequest(Request req, String key){
		try{
			String request = req.toString();
			Socket socket = socketmap.get(key);
			DataOutputStream d = new DataOutputStream(socket.getOutputStream());
			d.writeInt(request.getBytes().length);
			d.write(request.getBytes());
			d.flush();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		NetworkController theman = new NetworkController(4321);
	}
}
