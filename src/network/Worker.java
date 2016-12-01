package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

public class Worker extends Thread {
	 
	 HashMap<String, String> listofpaths ;

	public static void main(String[] args) {
		
		Thread thread = new Worker();
		thread.start();
	}
	
	public Worker(){
		
		listofpaths = new HashMap<String, String>();
		
		//Test paths
 		listofpaths.put("Pallini,Pikermi", "Pallini,Pikermi,Dir");
		listofpaths.put("Koropi,Pikermi", "Koropi,Pikermi,Dir");
		listofpaths.put("Gerakas,Pallini", "Gerakas,Pallini,Dir");
		
	}
	public void run(){
		Socket serviceSocket = null;
		try {
			serviceSocket = new Socket("127.0.0.1", 15432);//Request socket from Server
			
			DataInputStream din = new DataInputStream(serviceSocket.getInputStream()); // Input Stream
			String inputstring = inputStreamToString(din);
			
			// Creates the answer and send its back
			String answer = provideAnswer(inputstring);	
			
			DataOutputStream dout=new DataOutputStream(serviceSocket.getOutputStream()); // Output Stream
			dout.writeInt(answer.getBytes().length);
			dout.write(answer.getBytes());
			dout.flush();	
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// worker serves the request 
	public String provideAnswer(String questionstr){ 
		if ( searchList(questionstr) != null) {
			System.out.println("Yes I have it !");
			return (searchList(questionstr));
		}
		else {
			return null;		
		}
	}
	
	// scan listofpaths
	public String searchList(String keystr){
		String valuestr;
		valuestr = listofpaths.get(keystr);	
		return valuestr;
	}
	
	// That ...
	public String inputStreamToString(DataInputStream din) {
		String messagein = null;
		try {
			int length= din.readInt();
			if(length>0){
				byte[] b=new byte[length];
				din.readFully(b);
				messagein= new String(b,"UTF-8");
			System.out.println("Server asks for: " + messagein);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return messagein;
	}


}
