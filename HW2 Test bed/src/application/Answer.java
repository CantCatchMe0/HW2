package application;
//import databasePart1.DatabaseHelper;
import application.User;
public class Answer {
    private int id; // Answer Id
    private Question question; // Question that the answer is trying to respond to
    private String text; // Answer content
    private boolean isHelpful=false;
    private User user;
    //private static final DatabaseHelper databaseHelper = new DatabaseHelper();
    
    public Answer() {
    	question = null;
    	text = null;
    	id = -1;
    	user = null;
    }
    
    public Answer(Question question, String text, User user) throws Exception {
        if (text == null || text.trim().isEmpty()) {
            throw new Exception("Text can not be empty");
        }
        if (question == null) {
            throw new Exception("Question cannot be null");
        }
        this.question = question;
        this.text = text;
        //this.id = databaseHelper.addAnswer(this); 
        this.user = user;
    }

    public User getUser() {
    	return user;
    }
    
    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Question getQuestion() {
        return question;
    }
    
    public void setQuestion(Question question) {
    	this.question = question;
    }
    
    public void setUser(User user) {
    	this.user = user;
    }
    
    public void setId(int id) {
    	this.id = id;
    }

    public void setText(String text) throws Exception {
        if (text == null || text.trim().isEmpty()) {
            throw new Exception("Text can not be empty");
        } else {
            this.text = text;
        }
        //databaseHelper.updateAnswer(this);
    }
    
    public void setHelpful(boolean helpful) {
    	this.isHelpful=helpful;
    	//databaseHelper.updateAnswer(this);
    }
    
    public boolean getHelpful() {
    	return isHelpful;
    }
    
    public String getAnswer() { 
        // return the Question in a form that is printable
        return "Answer ID: " + id + "\n" + 
               "Question: " + question.getText() + "\n" + 
               "Category: " + question.getCategory() + "\n" + 
               "Answer: " + text + "\n";
    }
}
