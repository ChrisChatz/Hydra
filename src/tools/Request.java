package tools;

public class Request {

	private String connection_id;
	private String answer_json;
	private String question_loc;
	private String worker_id;
	
	public Request(String message , String slkey)
	{
		this.connection_id = slkey;
		this.question_loc = message;
		this.answer_json = "";
		this.worker_id = "";
	}
   
	public String toString(){
		//to be done with json 
		return this.toString();
	}
}
