package network;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import tools.StreamHandler;

public class AppClient extends Thread{
	
	private Socket socket;
	private DataInputStream input;
	
	public AppClient(String host, int port){
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
				}
			}catch(IOException e){e.printStackTrace();break;}
		}
	}
	
	public void sendMessage(String message){
		StreamHandler.outputStream(message, this.socket);
	}
	
	public static void main(String args[]){
		AppClient apc = new AppClient("127.0.0.1", 4321);
		apc.start();
		try {
			for(int i=0;i<4;i++){
				apc.sendMessage("eksarxeia,kalamata");
				Thread.sleep(2000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
