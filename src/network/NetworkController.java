package network;
import application.ApplicationController;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

import tools.Request;

public class NetworkController {
	ServerSocketListener sslistener;
	HashMap<String, Socket> socketmap;
	HashMap<String, SocketListener> slmap;
	String role;
	ApplicationController apiC;

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
		socketmap.put(key, s);
		SocketListener sl = new SocketListener(s, this);
		slmap.put(key,sl); 
		sl.start();
	}
	
	public void getMessageFromSL(String message, String slkey){
		apiC.callApp(slkey, message, this.role); // creates the hashmaps of clients and workers
	}


	public void sendResponse(String connection_id ,String message)
	{
		try
		{
			String temp;
			if (this.role.equals("client")){
				temp = apiC.getRequestclient().get(message);
			}else
			{
				temp = apiC.getRequestworker().get(message);
			}
			Socket socket = socketmap.get(temp);		
			DataOutputStream d = new DataOutputStream(socket.getOutputStream());
			d.writeInt(message.getBytes().length);
			d.write(message.getBytes());
			d.flush();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args){
		NetworkController clientnc = new NetworkController(4321,"client");
		NetworkController workernc = new NetworkController(4321,"worker");

	}
}
