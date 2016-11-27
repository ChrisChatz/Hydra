package network;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketListener extends Thread {

	private int portnumber;
	private ServerSocket serversocket;
	
	
	public ServerSocketListener(int port)
	{	
		this.portnumber = port;
	}

	public void run()
	{
		
		while(true){
			try {
				Socket x=serversocket.accept();
				//Here you must handle the new connection
				System.out.println("New connection");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
	
	public void open() throws IOException
	{
		serversocket=new ServerSocket(portnumber);
	}

	public void close() throws IOException
	{
		try{
			serversocket.close();
		}
		catch(NullPointerException e){e.printStackTrace();}
	}
	
	public static void main(String[] args) {
		ServerSocketListener x=new ServerSocketListener(4321);
		try {
			x.open();
			x.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
