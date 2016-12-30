package tools;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class StreamHandler {
	
	public static void outputStream(String s, Socket i){
		try {
			DataOutputStream d = new DataOutputStream(i.getOutputStream());
			d.writeInt(s.getBytes().length);
			d.write(s.getBytes());
			d.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String inputStream(DataInputStream din) {
		String messageIn="";
		try {
			int length= din.readInt();
			if(length>0){
				byte[] b=new byte[length];
				din.readFully(b);
				messageIn= new String(b,"UTF-8");
			System.out.println(messageIn);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return messageIn;
	}
}
