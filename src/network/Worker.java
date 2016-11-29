package network;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Worker extends Thread {
	
	Worker(){
		
	}
	public void run(){
		Socket requestSocket = null;
		try {
			requestSocket = new Socket("127.0.0.1", 4321);//Request socket from Server
			String message=new String("Hi");//This message it will change with an answer to a Client request
			DataOutputStream d=new DataOutputStream(requestSocket.getOutputStream());//Send message to SocketListener
			d.writeInt(message.getBytes().length);
			d.write(message.getBytes());
			d.flush();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		new Worker().start();
		

	}

	// worker serves the request 
	public void serveRequest(Request req){ 
		//if the worker has to answer add the answer to the request and send its back to the (Application Ctrler)

		//else asks google


	}

}
