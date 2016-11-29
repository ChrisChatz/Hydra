package network;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestClient extends Thread {
	
	TestClient(){
		
	}
	public void run(){
		Socket requestSocket = null;
		try {
			requestSocket = new Socket("127.0.0.1", 4321);//Request socket from Server
			String message=new String("Hi");
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
		new TestClient().start();

	}

}
