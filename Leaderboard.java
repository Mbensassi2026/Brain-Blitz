import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Leaderboard {
    private List<UserScore> scores;

    public Leaderboard() {
        this.scores = new ArrayList<>();
        // Adding sample scores
        this.scores.add(new UserScore("Bilal Ahmad", 35));
        this.scores.add(new UserScore("Mohammed Bensassi", 30));
        this.scores.add(new UserScore("Ahmad Nasrallah", 25));
    }

    public void addScore(String username, int score) {
        scores.add(new UserScore(username, score));
        scores.sort(Comparator.comparingInt(UserScore::getScore).reversed());
    }

    public List<UserScore> getTopScores(int n) {
        return scores.subList(0, Math.min(n, scores.size()));
    }

    public static class UserScore {
        private String username;
        private int score;

        public UserScore(String username, int score) {
            this.username = username;
            this.score = score;
        }

        public String getUsername() {
            return username;
        }

        public int getScore() {
            return score;
        }
    }
}