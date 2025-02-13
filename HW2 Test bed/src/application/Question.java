package application;
//import databasePart1.DatabaseHelper;
import application.User;
public class Question {
    private String text; 				// Content of the question
    private String category; 			// Category of the question
    private int id;
    private boolean isSolved = false;
    private int baseQuestionId=-1;
    private User user;
    //private static final DatabaseHelper databaseHelper = new DatabaseHelper();
    
    public Question() {
    	text = null;
    	category = null;
    	id = -1;
    	user = null;
    }
    
    public Question(String text, String category, User user) throws Exception {
        if (text == null || text.trim().isEmpty()) {
            throw new Exception("Text can not be empty");
        } else if (category == null || category.trim().isEmpty()) {
            throw new Exception("Category can not be empty");
        }
        this.text = text;
        this.category = category;
        //this.id = databaseHelper.addQuestion(this);
        this.user = user;
    }
    
    public Question(String text, String category, int baseQuestionId, User user) throws Exception {
        if (text == null || text.trim().isEmpty()) {
            throw new Exception("Text can not be empty");
        } else if (category == null || category.trim().isEmpty()) {
            throw new Exception("Category can not be empty");
        }
        this.text = text;
        this.category = category;
        this.baseQuestionId = baseQuestionId;
        this.user = user;
        //this.id = databaseHelper.addQuestion(this);
    }

    public String getText() {
        return text;
    }

    public String getCategory() {
        return category;
    }
    
    public int getBaseQuestionId() {
    	return baseQuestionId;
    }
    
    public int getId() {
    	return id;
    }
    
    public User getUser() {
    	return user;
    }
    
    public void setUser(User user) {
    	this.user=user;
    }
    
    public void setBaseQuestionId(int id) {
    	baseQuestionId = id;
    	//databaseHelper.updateQuestion(this);
    }
    
    public void setSolved(boolean solved) {
    	isSolved = solved;
    	//databaseHelper.updateQuestion(this);
    }
    
    public boolean getSolved() {
    	return isSolved;
    }

    public void setText(String text) throws Exception {
        if (text == null || text.trim().isEmpty()) {
            throw new Exception("Text can not be empty");
        } else {
            this.text = text;
        }
        //databaseHelper.updateQuestion(this);
    }
    
    public void setId(int id) {
    	this.id = id;
    }

    public void setCategory(String category) throws Exception {
        if (category == null || category.trim().isEmpty()) {
            throw new Exception("Category can not be empty");
        } else {
            this.category = category;
        }
        //databaseHelper.updateQuestion(this);
    }

    public String getQuestion() {
        return "Question ID: " + this.id + "\n" + "Question: " + text + "\n" + "Category: " + category + "\n";
    }
    

}
