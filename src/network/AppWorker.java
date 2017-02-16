package network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import tools.*;

public class AppWorker extends Thread{
	
	private Socket socket;
	private DataInputStream input;
	public HashMap<String,String> AnsweredQuestions;
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
	public String handleMessage(String message) throws IOException{
		Request re = Request.fromString(message);
		String decision = decide(re);
		if (decision.equals("google"))
		{
			String answer = GetGooglePath.getlink(re.getQuestionloc());
			re.setAnswer(answer);
			AnsweredQuestions.put(re.getQuestionloc(),re.getAnswer());
			return re.toString();
		}
		else if(decision.equals("HDD"))
		{
		    BufferedReader br = new BufferedReader(new FileReader("./"+re.getQuestionloc()));
			    try {
			        StringBuilder sb = new StringBuilder();
			        String line = br.readLine();
			        while (line != null) {
			            sb.append(line);
			            sb.append("\n");
			            line = br.readLine();
			        }
					br.close();
			        re.setAnswer(sb.toString());
			    }catch(Exception e)
			    {
			    	System.out.println("file not found");
			    }		
				
			return re.toString();
		}
		else if (decision.equals("Bounded")) //hashmap option
		{
			Random       random    = new Random();
			List<String> keys      = new ArrayList<String>(AnsweredQuestions.keySet());
			String       value = keys.get( random.nextInt(keys.size()) );
			String answer = AnsweredQuestions.get(value);
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
			re.setAnswer(GetGooglePath.getlink(re.getQuestionloc()));
			AnsweredQuestions.put(re.getQuestionloc(),re.getAnswer());
			
			return re.toString();

		}
		else
		{
			re.setAnswer(AnsweredQuestions.get(re.getQuestionloc()));
			return re.toString();
		}
		
	}
	
	public String decide(Request re)
	{
		int bounder = 2;
		if (AnsweredQuestions.size()<bounder && AnsweredQuestions.containsKey(re.getQuestionloc()))
		{
			return "Unbounded";
		}
		else if (AnsweredQuestions.size()==bounder && !AnsweredQuestions.containsKey(re.getQuestionloc()))
		{
			return "Bounded";
		}
		else
		{
			if (checkdisk(re.getQuestionloc()))
			{
				return "HDD";
			}
			else
			{
				return "google";
			}
		}
		
	}
	
	public boolean checkdisk(String questionloc)
	{
		File f = new File("./"+questionloc);
		if (f.exists() && !f.isDirectory())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	

	public void sendMessage(String message){
		StreamHandler.outputStream(message, this.socket);
	}
	
	public static void main(String args[]){
		AppWorker apc = new AppWorker("10.25.177.209", 1888);
		apc.start();
	}
}
