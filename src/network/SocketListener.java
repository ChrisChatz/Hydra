package network;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;


public class SocketListener  extends Thread{

	private Socket socket;
	private DataInputStream input;
	
	public SocketListener(Socket socket)
	{	
		this.socket = socket;
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
				//String message=null;
				int length= input.readInt();
				if(length>0){
					byte[] b=new byte[length];
					input.readFully(b);
					System.out.println(new String(b,"UTF-8"));
				}
			}catch(IOException e){e.printStackTrace();break;}
		}
	}

}
