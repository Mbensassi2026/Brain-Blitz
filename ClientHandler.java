import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {
    private final Socket socket;
    private final GameRoom gameRoom;
    private PrintWriter out;
    private BufferedReader in;

    public ClientHandler(Socket socket, GameRoom gameRoom) {
        this.socket = socket;
        this.gameRoom = gameRoom;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                processCommand(inputLine);
            }
        } catch (IOException e) {
            System.err.println("Error in ClientHandler: " + e.getMessage());
        } finally {
            closeResources();
        }
    }

    private void processCommand(String inputLine) {
        if (inputLine.startsWith("ANSWER ")) {
            verifyAnswer(inputLine.substring(7).trim());
        } else if ("quest".equalsIgnoreCase(inputLine.trim())) {
            sendCurrentQuestion();
        } else if ("start".equalsIgnoreCase(inputLine.trim())) {
            startGame();
        } else if ("leaderboard".equalsIgnoreCase(inputLine.trim())) {
            sendLeaderboard();
        } else {
            out.println("Unknown command");
        }
    }

    private void startGame() {
        // Logic to start the game for the client
    }

    private void sendLeaderboard() {
        // Logic to send the leaderboard to the client
    }

    private void sendCurrentQuestion() {
        Question currentQuestion = gameRoom.getCurrentQuestion();
        if (currentQuestion != null) {
            out.println("Question: " + currentQuestion.getQuestionText());
            char choiceLabel = 'A';
            for (String choice : currentQuestion.getChoices()) {
                out.println(choiceLabel++ + ". " + choice);
            }
            out.println("End of question.");
        } else {
            out.println("No more questions available.");
            gameRoom.getNextQuestion();  // Use the correct method name here
        }
    }

    private void verifyAnswer(String userAnswer) {
        Question currentQuestion = gameRoom.getCurrentQuestion();
        if (currentQuestion != null && userAnswer != null) {
            String correctAnswer = currentQuestion.getCorrectAnswer();  // Corrected from getAnswer() to getCorrectAnswer()

            if (userAnswer.equalsIgnoreCase(correctAnswer)) {
                out.println("Correct! Well done.");
            } else {
                out.println("Incorrect. The correct answer was: " + correctAnswer);
            }
            gameRoom.getNextQuestion();  // Use the correct method name here
        } else {
            out.println("No current question available or invalid answer format.");
        }
    }

    private void closeResources() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            System.err.println("Error closing resources: " + e.getMessage());
        }
    }
}
