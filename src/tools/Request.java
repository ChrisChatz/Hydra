package tools;

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
		//to be done with json 
		return this.toString();
	}
	
	public Request stringToReq(String m){
		Request x =  new Request("","");
		return x;
	}
	
	
}

   
	
	

	