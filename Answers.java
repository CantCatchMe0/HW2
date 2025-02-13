package application;
import java.util.ArrayList;
//import databasePart1.DatabaseHelper;
public class Answers {
	ArrayList<Answer> allAnswer;
	ArrayList<Answer> subsetAnswer;
	//private static final DatabaseHelper databaseHelper = new DatabaseHelper();
	
	public Answers() {
		//allAnswer = databaseHelper.getAllAnswer();
		allAnswer = new ArrayList<Answer>();
		subsetAnswer = new ArrayList<Answer>();
	}
	
	public void addAnswer(Answer answer) {
		allAnswer.add(answer);
		//databaseHelper.addAnswer(answer);
	}
	
	public void deleteAnswer(Answer answer) {
		allAnswer.removeIf(a -> a.getId()==answer.getId());
		//databaseHelper.deleteAnswer(answer);
	}
	
	public ArrayList<Answer> getAllAnswer(){
		return allAnswer;
	}
	
	public ArrayList<Answer> searchByKeyWord(String keyword)throws Exception{
		subsetAnswer.removeAll(subsetAnswer);
		for(Answer q : allAnswer) {
			if(q.getText().toLowerCase().contains(keyword.toLowerCase())) {
				subsetAnswer.add(q);
			}
		}
		if(subsetAnswer.isEmpty()) {
			throw new Exception("Answer Not Found");
		}
		return subsetAnswer;
	}
	
	public Answer getAnswerById(int id)throws Exception {
		for(int i=0; i<allAnswer.size();i++) {
			if(allAnswer.get(i).getId()==id) {
				return allAnswer.get(i);
			}	
		}
		throw new Exception("Answer doesn't exist");
	}
	
	public ArrayList<Answer> getAnswerByQuestion(Question question) throws Exception {
		
		subsetAnswer.removeAll(subsetAnswer);
		for(Answer a : allAnswer) {
			if(a.getQuestion().equals(question)) {
				subsetAnswer.add(a);
			}
		}
		if(subsetAnswer.isEmpty()) {
			throw new Exception("Answer not found");
		}
		return subsetAnswer;
	}
	
	public void printAllAnswer(ArrayList<Answer> answers) {
		for(Answer a: answers) {
			System.out.println(a.getAnswer());
			System.out.println("------------------------------------");
		}
	}
}
