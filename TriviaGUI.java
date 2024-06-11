import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TriviaGUI {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private TriviaGame game;
    private Leaderboard leaderboard = new Leaderboard();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TriviaGUI window = new TriviaGUI();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TriviaGUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(28, 77, 75, 16);
        frame.getContentPane().add(lblUsername);

        usernameField = new JTextField();
        usernameField.setBounds(115, 74, 130, 26);
        frame.getContentPane().add(usernameField);
        usernameField.setColumns(10);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(28, 122, 75, 16);
        frame.getContentPane().add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(115, 117, 130, 26);
        frame.getContentPane().add(passwordField);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(115, 155, 117, 29);
        frame.getContentPane().add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Authenticate user and start game
                User user = new User(username, password);
                game = new TriviaGame(user, QuestionBank.getQuestions());

                JOptionPane.showMessageDialog(frame, "Login successful. Starting game.");
                frame.dispose();
                startGame();
            }
        });
    }

    private void startGame() {
        JFrame gameFrame = new JFrame("Trivia Game");
        gameFrame.setBounds(100, 100, 450, 300);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.getContentPane().setLayout(null);

        // Add components for the game (e.g., question label, answer buttons, etc.)

        gameFrame.setVisible(true);
    }

    private void showFinalScore() {
        JFrame finalScoreFrame = new JFrame("Final Score");
        finalScoreFrame.setBounds(100, 100, 450, 300);
        finalScoreFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        finalScoreFrame.getContentPane().setLayout(null);

        JLabel lblFinalScore = new JLabel("Your final score is: " + game.getScore());
        lblFinalScore.setBounds(115, 74, 200, 26);
        finalScoreFrame.getContentPane().add(lblFinalScore);

        JButton btnShowLeaderboard = new JButton("Show Leaderboard");
        btnShowLeaderboard.setBounds(115, 155, 200, 29);
        finalScoreFrame.getContentPane().add(btnShowLeaderboard);

        btnShowLeaderboard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showLeaderboard();
            }
        });

        finalScoreFrame.setVisible(true);
    }

    private void showLeaderboard() {
        JFrame leaderboardFrame = new JFrame("Leaderboard");
        leaderboardFrame.setBounds(100, 100, 450, 300);
        leaderboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        leaderboardFrame.getContentPane().setLayout(null);

        JLabel lblLeaderboard = new JLabel("Leaderboard:");
        lblLeaderboard.setBounds(28, 20, 100, 16);
        leaderboardFrame.getContentPane().add(lblLeaderboard);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBounds(28, 50, 400, 200);
        leaderboardFrame.getContentPane().add(textArea);

        StringBuilder leaderboardText = new StringBuilder();
        List<Leaderboard.UserScore> topScores = leaderboard.getTopScores(10);
        for (Leaderboard.UserScore score : topScores) {
            leaderboardText.append(score.getUsername()).append(": ").append(score.getScore()).append("\n");
        }
        textArea.setText(leaderboardText.toString());

        leaderboardFrame.setVisible(true);
    }
}
