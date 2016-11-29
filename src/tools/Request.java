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

	public String getConnection_id() {
		return connection_id;
	}

	public void setConnection_id(String connection_id) {
		this.connection_id = connection_id;
	}

	public String getAnswer_json() {
		return answer_json;
	}

	public void setAnswer_json(String answer_json) {
		this.answer_json = answer_json;
	}

	public String getQuestion_loc() {
		return question_loc;
	}

	public void setQuestion_loc(String question_loc) {
		this.question_loc = question_loc;
	}

	public String getWorker_id() {
		return worker_id;
	}

	public void setWorker_id(String worker_id) {
		this.worker_id = worker_id;
	}
	
	
}
