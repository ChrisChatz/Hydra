package tools;

public class Request {

	private String connection_id;
	private String answer_json;
	private String question_loc;// A concatenated key of the search request by the client
	private String worker_id;
	
	public Request(String message , String slkey)
	{
		this.connection_id = slkey;
		this.question_loc = message;
		this.answer_json = ""; // the answer from google api
		this.worker_id = "";
	}
   
	public String toString(){
		//to be done with json 
		return this.toString();
	}

	public String getWorker_id() {
		return worker_id; // this has the form of connection_id aka ip address + port
	}

	public void setWorker_id(String worker_id) {
		this.worker_id = worker_id;
	}
	
	public void setAnswer_json(String answer_json)
	{
		this.answer_json = answer_json;
	}
	public String getAnswer_json()
	{
		return this.answer_json;
	}
}
