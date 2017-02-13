package network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Random;

import tools.*;

public class AppWorker extends Thread{
	
	private Socket socket;
	private DataInputStream input;
	public static	HashMap<String,String> AnsweredQuestions;
	public AppWorker(String host, int port){
		try{
			this.socket = new Socket(host, port);
			this.input = new DataInputStream(socket.getInputStream());
			this.AnsweredQuestions = new HashMap<String,String>();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public void run(){
		while(true){
			try{
				//Takes message from Clients and Workers
				int counter = 0;
				//while (counter<2)
				//{
					String message;
					int length= input.readInt();
					if(length>0){
						byte[] b=new byte[length];
						input.readFully(b);
						message= new String(b,"UTF-8");
						this.sendMessage(handleMessage(message));
					}
	//			counter++;
				//}
	//			this.sendMessage("FALL");
			}catch(IOException e){e.printStackTrace();break;}
		}
	}
	public static String handleMessage(String message) throws IOException{
		Request re = Request.fromString(message);
		if (AnsweredQuestions.containsKey(re.getQuestionloc()))
		{
			if (AnsweredQuestions.size()<20){
				return AnsweredQuestions.get(re.getQuestionloc());
			}
			else{
				writetodisk(re.getQuestionloc(), AnsweredQuestions.get(re.getQuestionloc()));
				re.setAnswer(AnsweredQuestions.get(re.getQuestionloc()));
				return re.toString();
			}
		}
		else
		{
			System.out.println("mpike");
			String response = retrievefromdisk(re.getQuestionloc());
			if (response.equals("!FOUND"))
			{
				String answer = GetGooglePath.getlink(re.getQuestionloc());
				re.setAnswer(answer);
				AnsweredQuestions.put(re.getQuestionloc(),re.getAnswer());
				return re.toString();
			}
			else
			{
				re.setAnswer(response);
				return re.toString();
			}
		}
	}
	public static void writetodisk(String question,String response) throws IOException
	{
		String value;
		String answer;
		value = AnsweredQuestions.get(new Random().nextInt(AnsweredQuestions.size()));
		answer= AnsweredQuestions.get(value);
		AnsweredQuestions.remove(value);
		try{
	    	File file =new File("./"+value);
	    	if(!file.exists()){
	    	   file.createNewFile();
	    	}
	    	FileWriter fw = new FileWriter(file,true);
	    	BufferedWriter bw = new BufferedWriter(fw);
	    	bw.write(answer);
	    	bw.close();
	      }catch(IOException ioe){
	         System.out.println("Exception occurred:");
	    	 ioe.printStackTrace();
	       }
		AnsweredQuestions.put(question, response);
	}
	
	public static String retrievefromdisk(String value) throws IOException
	{
		String answer = "";
	    BufferedReader br = new BufferedReader(new FileReader(value));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();
	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
			br.close();
	        return sb.toString();
	    }catch(Exception e)
	    {
	    	System.out.println("file not found");
	    	answer = "!FOUND";
	    }
		return answer;
	}

	public void sendMessage(String message){
		StreamHandler.outputStream(message, this.socket);
	}
	
	public static void main(String args[]){
		AppWorker apc = new AppWorker("127.0.0.1", 1888);
		apc.start();
	}
}
