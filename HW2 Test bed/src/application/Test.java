package application;

import application.DatabaseHelper;
import java.sql.SQLException;
import java.util.ArrayList;

public class Test {
    
    static int numPassed = 0;    // Counter for passed tests
    static int numFailed = 0;    // Counter for failed tests

    static DatabaseHelper databaseHelper = new DatabaseHelper();
    
    public static void main(String[] args) {
        try {
            databaseHelper.connectToDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        System.out.println("-----------------------------------------------------------");
        System.out.println("\nApplication Testing Automation");
        
        try {
            testQuestionClass();
            testQuestionsClass();
            testAnswerClass();
            testAnswersClass();
        } catch (Exception e) {
            e.printStackTrace();
            numFailed++;
        }
        
        System.out.println("--------------------------------------------------");
        System.out.println();
        System.out.println("passed: " + numPassed);
        System.out.println("failed: " + numFailed);
        databaseHelper.closeConnection();
    }

    private static void performTestCase(int testCase, boolean condition, String testName) {
        System.out.println("-------------------------------------\nTest case: " + testCase);
        System.out.println("Test: " + testName);
        
        if (condition) {
            System.out.println("Test passed:");
            numPassed++;
        } else {
            System.out.println("Test failed:");
            numFailed++;
        }
    }
    
    private static void testQuestionClass() throws Exception {
        System.out.println("\n-------------------------------------------");
        User testUser = new User("JohnDoe", "password123", "user");
        Question q = new Question("What is Java?", "Programming", testUser);
        performTestCase(1, q.getText().equals("What is Java?"), "Question text retrieval");
        performTestCase(2, q.getCategory().equals("Programming"), "Question category retrieval");
        performTestCase(3, q.getUser().equals(testUser), "Question user retrieval");
        
        q.setText("Updated Java Question");
        q.setCategory("Updated Category");
        performTestCase(4, q.getText().equals("Updated Java Question"), "Set text method");
        performTestCase(5, q.getCategory().equals("Updated Category"), "Set category method");
        
        // Test exception for empty text
        try {
            q.setText("");
            performTestCase(6, false, "Set empty text");
        } catch (Exception e) {
            performTestCase(6, true, "Set empty text");
        }
        
        // Test exception for empty category
        try {
            q.setCategory("");
            performTestCase(7, false, "Set empty category");
        } catch (Exception e) {
            performTestCase(7, true, "Set empty category");
        }
    }
    
    private static void testQuestionsClass() throws Exception {
        System.out.println("\n-----------------------------------------------");
        Questions questions = new Questions();
        User user1 = new User("Alice", "pass", "user");
        Question q1 = new Question("What is OOP?", "Programming", user1);
        questions.addQuestions(q1);
        
        // Adding and retrieving question
        performTestCase(8, questions.getAllQuestion().contains(q1), "Adding and retrieving a question");
        
        // Searching by keyword
        ArrayList<Question> searchResults = questions.searchByKeyWord("OOP");
        performTestCase(9, searchResults.contains(q1), "Searching question by keyword");
        
        // Test searching by empty keyword
        try {
            questions.searchByKeyWord("");
            performTestCase(10, false, "Searching with empty keyword ");
        } catch (Exception e) {
            performTestCase(10, true, "Searching with empty keyword ");
        }
        
        // Test searching by category
        ArrayList<Question> categorySearchResults = questions.searchByCategory("Programming");
        performTestCase(11, categorySearchResults.contains(q1), "Searching question by category");
    }
    
    private static void testAnswerClass() throws Exception {
        System.out.println("\n-------------------------------------------------------");
        User user2 = new User("Bob", "pass", "user");
        Question q2 = new Question("What is a Class?", "Programming", user2);
        Answer a = new Answer(q2, "A class is a blueprint for objects.", new User("Charlie", "pass", "user"));
        
        performTestCase(12, a.getText().equals("A class is a blueprint for objects."), "Answer text retrieval");
        performTestCase(13, a.getQuestion().equals(q2), "Answer question linkage");
        
        a.setText("Updated Answer Text");
        performTestCase(14, a.getText().equals("Updated Answer Text"), "Set text method for Answer");
        
        // Test setting empty answer text
        try {
            a.setText("");
            performTestCase(15, false, "Set empty answer text");
        } catch (Exception e) {
            performTestCase(15, true, "Set empty answer text");
        }
    }
    
    private static void testAnswersClass() throws Exception {
        System.out.println("\n--------------------------------------------------------");
        Answers answers = new Answers();
        User user3 = new User("Dave", "pass", "user");
        Question q3 = new Question("What is Inheritance?", "Programming", user3);
        Answer a = new Answer(q3, "Inheritance allows a class to use properties of another class.", new User("Eve", "pass", "user"));
        
        answers.addAnswer(a);
        performTestCase(16, answers.getAllAnswer().contains(a), "Adding and retrieving an answer");
        
        ArrayList<Answer> answersForQuestion = answers.getAnswerByQuestion(q3);
        performTestCase(17, answersForQuestion.contains(a), "Retrieving answers by question");
        
        // Test no answers for non-existing question
        Question nonExistingQuestion = new Question("What is Polymorphism?", "Programming", new User("Eve", "pass", "user"));
        try {
            answers.getAnswerByQuestion(nonExistingQuestion);
            performTestCase(18, false, "Retrieving answers for non-existing question");
        } catch (Exception e) {
            performTestCase(18, true, "Retrieving answers for non-existing question");
        }
    }
}
