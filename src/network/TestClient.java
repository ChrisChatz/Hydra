package network;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import tools.StreamHandler;

public class TestClient extends Thread {
	TestClient(){
	}
	StreamHandler strHan = new StreamHandler();
	
	public void run(){
		Socket requestSocket = null;
	
			try {
				requestSocket = new Socket("127.0.0.1", 4321);
				String message=new String("DWSE PASA RE KARAGIOZH");
				strHan.outputStream(message, requestSocket);
				Thread.sleep(2000);
				strHan.outputStream(message, requestSocket);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//Request socket from Server
 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	public static void main(String[] args) {
		new TestClient().start();

	}

}
