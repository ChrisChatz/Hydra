package application;

import java.util.ArrayList;
import java.util.Random;

public class WorkerRepCollection {
	
	ArrayList<WorkerRep> collection;
	
	public WorkerRepCollection(){
		collection = new ArrayList<WorkerRep>();
	}
	
	public String whoServedQuestion(String question){
		for(WorkerRep rep: collection){
			if(rep.has_done_search(question)){
				return rep.getConnId();
			}
		}
		return null;
	}
	
	public WorkerRep pickRandomWorkerRep(){
		return collection.get(new Random().nextInt(collection.size()));
	}
	
	public String whoWillServeQuestion(String question){
		String previousServer = this.whoServedQuestion(question);
		//kapoy edw prepei na mpei enas elegxos an o worker poy exei apantisei (h oxi tin erwtisi) einai zwntanos
		if(previousServer!=null){
			System.out.println("request will be handled by : "+previousServer); 
			return previousServer;
		}
		else{
			WorkerRep rep = this.pickRandomWorkerRep();
			rep.addQuestion(question);
			System.out.println("request will be handled by : "+rep.getConnId());
			return rep.getConnId();
		}
	}
}
