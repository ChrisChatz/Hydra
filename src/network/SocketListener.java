package network;
import java.net.Socket;


public class SocketListener  extends Thread{

	private Socket socket;
	
	public SocketListener(Socket socket)
	{	
		this.socket = socket;
	}

	public void run()
	{
		
	}

}
