package network;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;


public class SocketListener  extends Thread{

	private Socket socket;
	private DataInputStream input;
	private NetworkController parent;
	
	public SocketListener(Socket socket, NetworkController parent)
	{	
		this.socket = socket;
		this.parent= parent;
		try {
			this.input=new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void run()
	{
		while(true){
			try{
				//Takes message from Clients and Workers
				String message;
				int length= input.readInt();
				if(length>0){
					byte[] b=new byte[length];
					input.readFully(b);
					message= new String(b,"UTF-8");
					String key = socket.getRemoteSocketAddress().toString();
					parent.getMessageFromSL(message, key);//Send the message of Client to the Network Controller
				}
			}catch(IOException e){e.printStackTrace();break;}
		}
	}

}
