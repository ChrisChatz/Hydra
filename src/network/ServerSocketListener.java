package network;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketListener extends Thread {

	private int portnumber;
	private ServerSocket serversocket;
	private NetworkController parent;
	
	public ServerSocketListener(int port, NetworkController parent)
	{	
		this.portnumber = port;
		this.parent= parent;
	}

	public void run()
	{
		
		while(true){
			try {
				Socket x=serversocket.accept();
				parent.addSocket(x);//Here you must handle the new connection
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
}
