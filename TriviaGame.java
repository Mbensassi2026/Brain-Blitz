import java.util.List;

public class TriviaGame {
    private User user;
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    public TriviaGame(User user, List<Question> questions) {
        this.user = user;
        this.questions = questions;
    }

    public User getUser() {
        return user;
    }

    public Question getNextQuestion() {
        if (currentQuestionIndex < questions.size()) {
            return questions.get(currentQuestionIndex++);
        }
        return null;
    }

    public boolean verifyAnswer(String userAnswer) {
        if (currentQuestionIndex == 0) return false; // no question has been asked yet
        Question currentQuestion = questions.get(currentQuestionIndex - 1);
        boolean isCorrect = currentQuestion.getCorrectAnswer().equals(userAnswer);
        if (isCorrect) {
            score += 5;
        }
        return isCorrect;
    }

    public int getScore() {
        return score;
    }
}
