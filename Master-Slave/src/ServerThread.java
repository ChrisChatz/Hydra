import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;
import java.util.Scanner;

import javax.xml.ws.AsyncHandler;

public class ServerThread extends Thread{
	
	Socket client = null;
	PrintWriter output;
	BufferedReader input;
	Scanner scan= new Scanner(System.in);
	
	public ServerThread(Socket client){ 
		this.client = client;
	}
	
	// server site 
	static String parse(String inputString) {
		int inputInt = Integer.parseInt(inputString);
		String commandString = "";
		switch (inputInt) {
			// Date
			case 1:
				commandString = "This 1";
				break;

			
			case 2:
				commandString = "This 2";
				break;

		}

		return commandString;
	}
	
	public void run(){
		System.out.print("Accepted connnection. ");
		
		try {
			
			// open a new PrintWriter and BufferedReader on the socket
			output = new PrintWriter(client.getOutputStream(),true);
			input = new BufferedReader(new InputStreamReader(client.getInputStream()));
			System.out.print("Reader and Writer created. \n");
			

			
			String message, response;
			
			do
            {

                response = input.readLine();    
                message = parse(response);
                output.println(message); 
                
            }while (!message.equals("***CLOSE***"));
	        
	        
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
			
			//close the connection to the client
			try {
				client.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Output closed.");
		}
		
	}
}
