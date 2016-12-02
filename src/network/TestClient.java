package network;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import tools.StreamHandler;

public class TestClient extends Thread {
	StreamHandler strHan;
	TestClient(){
		
	}
	public void run(){
		Socket requestSocket = null;
	
			try {
				requestSocket = new Socket("127.0.0.1", 4321);
				String message=new String("Hi");
				strHan.outputStream(message, requestSocket);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//Request socket from Server
			
			

		
	}
	public static void main(String[] args) {
		new TestClient().start();

	}

}
