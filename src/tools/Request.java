package tools;

import com.google.gson.*;

public class Request {

	private String connection_id;
	private String question_loc;// A concatenated key of the search request by the client
	private String worker_id;
	private String answer;
	
	public Request(String slkey,String message)
	{
		this.connection_id = slkey;
		this.question_loc = message;
		this.answer = ""; // the answer from google api
		this.worker_id = "";
	}
	
	public String getConnection_id() {
		return connection_id;
	}

	public String getWorker_id() {
		return worker_id; // this has the form of connection_id aka ip address + port
	}

	public void setWorker_id(String worker_id) {
		this.worker_id = worker_id;
	}
	
	public void setAnswer(String answer_json)
	{
		this.answer = answer_json;
	}
	public String getAnswer()
	{
		return this.answer;
	}
	
	public String toString(){
		return new Gson().toJson(this);
	}
	
	public static Request fromString(String str){
		return new Gson().fromJson(str, Request.class);
	}
	
//	public Request stringToReq(String m){
//		pass
//	}
	public String getQuestionloc()
	{
		return this.question_loc;
	}
	public void setConnection_id(String connection_id) {
		this.connection_id = connection_id;
	}
	
}

   
	
	

	