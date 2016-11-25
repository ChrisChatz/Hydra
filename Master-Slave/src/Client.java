import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	
	static String hostName = "localhost";
	static Socket socket = null;
	
	
	public static void main(String[] args) {
		
		PrintWriter output = null;
		BufferedReader input = null;
		Scanner scan= new Scanner(System.in);
		
		try {
			
			socket = new Socket(hostName, 15432); // open connection 
			System.out.println("Connected");
			
			//opens a PrintWriter on the socket input autoflush mode
			output = new PrintWriter(socket.getOutputStream(), true);
			
			//opens a BufferedReader on the socket
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			
			String message, response;
			
			do
            {
                System.out.print("Enter message: ");
                message =  scan.nextLine();
                output.println(message);   
                
                response = input.readLine();   
                System.out.println("\nSERVER> " + response);
                
            }while (!message.equals("***CLOSE***"));
	        
			
		}
		catch (UnknownHostException e) {
			System.err.println("Unknown host: " + hostName);
			System.exit(1);
		}
		catch (ConnectException e) {
			System.err.println("Connection refused by host: " + hostName);
			System.exit(1);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			socket.close();
			System.out.flush();
		}
		catch (IOException e ) {
			System.out.println("Couldn't close socket");
		}
		
		
	}

}
