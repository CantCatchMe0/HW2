package application;
//import databasePart1.DatabaseHelper;
import java.util.ArrayList;
import application.Answers;

public class Questions {
	private ArrayList<Question> allQuestion; 		// store all questions
	private ArrayList<Question> subsetQuestion; 	// store all the result of searching
	private ArrayList<Question> solvedQuestion;		// store for list of question that is already solved
	private ArrayList<Question> unsolvedQuestion;	// store for list of question remain unsolved
	//private static final DatabaseHelper databaseHelper = new DatabaseHelper();
	private Answers answers = new Answers();
	public Questions() {
		allQuestion = new ArrayList<Question>();
		subsetQuestion = new ArrayList<Question>();
		solvedQuestion = new ArrayList<Question>();
		unsolvedQuestion = new ArrayList<Question>();
	}
	
	public void updateQuestion(){
		for(Question q: allQuestion) {
			if(q.getSolved()) {
				solvedQuestion.add(q);
			}else {
				unsolvedQuestion.add(q);
			}
		}
	}
	
	public void addQuestions(Question question) {
		//databaseHelper.addQuestion(question);
		//allQuestion = databaseHelper.getAllQuestion();
		allQuestion.add(question);
		updateQuestion();
	}
	
	public void deleteQuestions(Question question) {
		allQuestion.removeIf(q -> q.equals(question));
		//databaseHelper.deleteQuestion(question);
		//allQuestion = databaseHelper.getAllQuestion();
	}
	
	public ArrayList<Question> getAllQuestion(){
		
		return allQuestion;
	}
	
	public ArrayList<Question> searchByKeyWord(String keyword) throws Exception{
		subsetQuestion.removeAll(subsetQuestion);
		if(keyword.trim().isEmpty()||keyword==null) {
			throw new Exception ("Keyword can not be empty");
		}
		for(Question q : allQuestion) {
			if(q.getText().toLowerCase().contains(keyword.toLowerCase()) || q.getCategory().toLowerCase().contains(keyword.toLowerCase())) {
				subsetQuestion.add(q);
			}
		}
		try {
			if(subsetQuestion.isEmpty())
			throw new Exception("Question Not Found");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return subsetQuestion;
	}
	
	public ArrayList<Question> searchByCategory(String category)throws Exception{
		subsetQuestion.removeAll(subsetQuestion);
		for(Question q : allQuestion) {
			if(q.getCategory().toLowerCase().contains(category.toLowerCase())) {
				subsetQuestion.add(q);
			}
			
		}
		try {
			if(subsetQuestion.isEmpty()) {
				throw new Exception("Question Not Found");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return subsetQuestion;
	}
	
	public Question getQuestionById(int id)throws Exception {
		
		for(int i=0; i<allQuestion.size();i++) {
			if(allQuestion.get(i).getId()==id) {
				return allQuestion.get(i);
			}	
		}
		throw new Exception("Question doesn't exist");
	}
	
	public void printAllQuestions() throws Exception {
		for(Question q:allQuestion) {
			System.out.println("Question Id: " + q.getId());
			answers.printAllAnswer(answers.getAnswerByQuestion(q));
		}
	}
	
	public void printSolvedQuestions() throws Exception {
		for(Question q:solvedQuestion) {
			System.out.println("Question Id: " + q.getId());
			answers.printAllAnswer(answers.getAnswerByQuestion(q));
		}
	}
	
	public void printUnsolvedQuestions() throws Exception {
		for(Question q:unsolvedQuestion) {
			System.out.println("Question Id: " + q.getId());
			answers.printAllAnswer(answers.getAnswerByQuestion(q));
		}
	}
	
	public void printCategoryRelatedQuestions(Question question, boolean solve) throws Exception {
		subsetQuestion = searchByCategory(question.getCategory());
		for (Question q: subsetQuestion) {
			if(q.getSolved()==solve) {
				System.out.println("Question Id: " + q.getId());
				answers.printAllAnswer(answers.getAnswerByQuestion(q));
			}
		}
	}
	
	public void printKeywordRelatedQuestions(String keyword, boolean solve) throws Exception {
		subsetQuestion = searchByKeyWord(keyword);
		for (Question q: subsetQuestion) {
			if(q.getSolved()==solve) {
				System.out.println("Question Id: " + q.getId());
				answers.printAllAnswer(answers.getAnswerByQuestion(q));
			}
		}
	}
	
	public void printUserQuestions(User user) {
		for(Question q: allQuestion) {
			if(q.getUser().equals(user)) {
				System.out.println(q.getQuestion());
				System.out.println("------------------------------------");
			}
		}
	}
}
