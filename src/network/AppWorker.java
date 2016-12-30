package network;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import tools.*;

public class AppWorker extends Thread{
	
	private Socket socket;
	private DataInputStream input;
	
	public AppWorker(String host, int port){
		try{
			this.socket = new Socket(host, port);
			this.input = new DataInputStream(socket.getInputStream());
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public void run(){
		while(true){
			try{
				//Takes message from Clients and Workers
				String message;
				int length= input.readInt();
				if(length>0){
					byte[] b=new byte[length];
					input.readFully(b);
					message= new String(b,"UTF-8");
					System.out.println(message);
					this.sendMessage(handleMessage(message));
				}
			}catch(IOException e){e.printStackTrace();break;}
		}
	}
	
	public static String handleMessage(String message){
		Request re = Request.fromString(message);
		re.setAnswer("::TESTING::");
		return re.toString();
	}
	
	public void sendMessage(String message){
		StreamHandler.outputStream(message, this.socket);
	}
	
	public static void main(String args[]){
		AppWorker apc = new AppWorker("127.0.0.1", 1888);
		apc.start();
	}
	
}
