package application;

import java.util.ArrayList;

public class WorkerRep {

	String connId;
	boolean active;
	ArrayList<String> questions_served;
	
	public WorkerRep(String connId, boolean active, ArrayList<String> questions_served) {
		this.connId = connId;
		this.active = active;
		this.questions_served = questions_served;
	}
	
	public boolean has_done_search(String question){
		return this.questions_served.contains(question);
	}
	
	public void addQuestion(String question){
		this.questions_served.add(question);
	}

	public String getConnId() {
		return connId;
	}

	public void setConnId(String connId) {
		this.connId = connId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public ArrayList<String> getQuestions_served() {
		return questions_served;
	}

	public void setQuestions_served(ArrayList<String> questions_served) {
		this.questions_served = questions_served;
	}
	
}
