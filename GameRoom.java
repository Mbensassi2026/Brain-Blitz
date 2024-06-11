import java.util.List;

public class GameRoom {
    private String roomId;
    private List<Question> questions;
    private int currentQuestionIndex = 0;

    public GameRoom(String roomId) {
        this.roomId = roomId;
        this.questions = QuestionBank.getQuestions();
    }

    public Question getCurrentQuestion() {
        if (currentQuestionIndex < questions.size()) {
            return questions.get(currentQuestionIndex);
        }
        return null; // Return null if there are no more questions
    }

    public Question getNextQuestion() {
        if (currentQuestionIndex < questions.size()) {
            return questions.get(currentQuestionIndex++); // Return current question and increment index
        }
        return null; // Return null if there are no more questions
    }

    public boolean hasMoreQuestions() {
        return currentQuestionIndex < questions.size();
    }
}
