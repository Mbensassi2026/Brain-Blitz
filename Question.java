public class Question {
    private String questionText;
    private String[] choices;
    private String correctAnswer;

    public Question(String questionText, String[] choices, String correctAnswer) {
        this.questionText = questionText;
        this.choices = choices;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getChoices() {
        return choices;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
