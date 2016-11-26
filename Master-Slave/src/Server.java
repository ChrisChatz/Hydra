import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {
	
/*	public static String getIp() throws IOException{
	    URL whatismyip = new URL("http://icanhazip.com");
	    BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
	    return in.readLine();
	}*/
	
	public static void main(String[] args) {

		
		AtomicInteger numThreads = new AtomicInteger(0); // 
		
		// the list of threads  is kept in a linked list
		ArrayList<Thread> list= new ArrayList<Thread>();
		
		
		try {	
			
			String in = "127.0.0.1"; // localhost
			//String in = "46.190.55.129"; // trying to open connection thought external IP
			InetAddress addr = InetAddress.getByName(in);
			
			// listen for incoming connections on port 15432
			ServerSocket socket = new ServerSocket(15432,50,addr);
			System.out.println("Server listening on port 15432");

			
			// loop until program is stopped
			while(true){
				
				// accept a new connection
				Socket client = socket.accept();
				
				// start a new ServerThread to handle the connection and send output to the client
				Thread thrd = new Thread(new ServerThread(client));
				
				list.add(thrd); // add the thread to the list
				
				thrd.start(); // start the thread
				
				numThreads.incrementAndGet(); // increment by one the value on numThread.
				System.out.println("Thread " + numThreads.get() + " started");
			}
		
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
	}
}
