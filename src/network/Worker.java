package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class Worker extends Thread {


	public static void main(String[] args) {
		
		Thread thread = new Worker();
		thread.start();
	}
	
	public Worker(){

		
	}
	public void run(){
		Socket serviceSocket = null;
		try {
			serviceSocket = new Socket("127.0.0.1", 4321);//Request socket from Server
			
			String message=new String("Hi");//This message it will change with an answer to a Client request
			DataOutputStream dout=new DataOutputStream(serviceSocket.getOutputStream());//Send message to SocketListener
			dout.writeInt(message.getBytes().length);
			dout.write(message.getBytes());
			dout.flush();
			
			DataInputStream din = new DataInputStream(serviceSocket.getInputStream());
			din.read();
			System.out.println(din);
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	// worker serves the request 
	public void provideService(String string){ 
		//if the worker has to answer
		
		//else asks google

	}
	


}
