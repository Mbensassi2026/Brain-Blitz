import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TriviaServer {
    private UserStore userStore = new UserStore();
    private Map<String, TriviaGame> userGames = new HashMap<>();
    private Leaderboard leaderboard = new Leaderboard();

    public void startServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/api/login", this::handleLoginRequest);
        server.createContext("/api/register", this::handleRegisterRequest);
        server.createContext("/api/start", this::handleStartGameRequest);
        server.createContext("/api/answer", this::handleAnswerRequest);
        server.createContext("/api/quest", this::handleQuestionRequest);
        server.createContext("/api/submitScore", this::handleSubmitScoreRequest);
        server.createContext("/api/leaderboard", this::handleLeaderboardRequest);
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Server started on port 8000");
    }

    private void handleLoginRequest(HttpExchange exchange) throws IOException {
        handlePostRequest(exchange, data -> {
            boolean isAuthenticated = userStore.authenticateUser(data.get("username"), data.get("password"));

            String response;
            if (isAuthenticated) {
                response = "{\"success\": true}";
                exchange.sendResponseHeaders(200, response.getBytes().length);
            } else {
                response = "{\"success\": false, \"message\": \"Invalid credentials\"}";
                exchange.sendResponseHeaders(403, response.getBytes().length);
            }
            return response;
        });
    }

    private void handleRegisterRequest(HttpExchange exchange) throws IOException {
        handlePostRequest(exchange, data -> {
            boolean isRegistered = userStore.registerUser(data.get("username"), data.get("password"));

            String response;
            if (isRegistered) {
                response = "{\"success\": true}";
                exchange.sendResponseHeaders(200, response.getBytes().length);
            } else {
                response = "{\"success\": false, \"message\": \"User already exists\"}";
                exchange.sendResponseHeaders(409, response.getBytes().length);
            }
            return response;
        });
    }

    private void handleStartGameRequest(HttpExchange exchange) throws IOException {
        handlePostRequest(exchange, data -> {
            String username = data.get("username");
            User user = userStore.getUser(username);
            TriviaGame newGame = new TriviaGame(user, QuestionBank.getQuestions());
            userGames.put(username, newGame);

            String response = "{\"success\": true}";
            exchange.sendResponseHeaders(200, response.getBytes().length);
            return response;
        });
    }

    private void handleAnswerRequest(HttpExchange exchange) throws IOException {
        handlePostRequest(exchange, data -> {
            String username = data.get("username");
            String userAnswer = data.get("answer");

            TriviaGame game = userGames.get(username);
            boolean isCorrect = game.verifyAnswer(userAnswer);

            String response = isCorrect ? "{\"correct\": true}" : "{\"correct\": false}";

            exchange.sendResponseHeaders(200, response.getBytes().length);
            return response;
        });
    }

    private void handleQuestionRequest(HttpExchange exchange) throws IOException {
        handlePostRequest(exchange, data -> {
            String username = data.get("username");
            TriviaGame game = userGames.get(username);
            Question question = game.getNextQuestion();

            String response;
            if (question != null) {
                response = String.format("{\"question\": \"%s\", \"choices\": [\"%s\", \"%s\", \"%s\", \"%s\"]}",
                        question.getQuestionText(), question.getChoices()[0], question.getChoices()[1], question.getChoices()[2], question.getChoices()[3]);
                exchange.sendResponseHeaders(200, response.getBytes().length);
            } else {
                response = "{\"question\": null}";
                exchange.sendResponseHeaders(200, response.getBytes().length);
            }
            return response;
        });
    }

    private void handleSubmitScoreRequest(HttpExchange exchange) throws IOException {
        handlePostRequest(exchange, data -> {
            String username = data.get("username");
            TriviaGame game = userGames.get(username);
            int score = game.getScore();
            leaderboard.addScore(username, score);

            String response = "{\"success\": true, \"score\": " + score + "}";
            exchange.sendResponseHeaders(200, response.getBytes().length);
            return response;
        });
    }

    private void handleLeaderboardRequest(HttpExchange exchange) throws IOException {
        handlePostRequest(exchange, data -> {
            String response = "[";
            List<Leaderboard.UserScore> topScores = leaderboard.getTopScores(10);
            for (int i = 0; i < topScores.size(); i++) {
                Leaderboard.UserScore score = topScores.get(i);
                response += String.format("{\"username\": \"%s\", \"score\": %d}", score.getUsername(), score.getScore());
                if (i < topScores.size() - 1) {
                    response += ", ";
                }
            }
            response += "]";
            exchange.sendResponseHeaders(200, response.getBytes().length);
            return response;
        });
    }

    private void handlePostRequest(HttpExchange exchange, RequestHandler handler) throws IOException {
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

        if ("OPTIONS".equals(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(204, -1);
            return;
        }

        if ("POST".equals(exchange.getRequestMethod())) {
            InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);

            String query = br.readLine();
            Map<String, String> data = parseFormData(query);

            String response = handler.handle(data);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } else {
            exchange.sendResponseHeaders(405, 0);
            exchange.getResponseBody().close();
        }
    }

    private Map<String, String> parseFormData(String formData) {
        Map<String, String> map = new HashMap<>();
        String[] pairs = formData.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            map.put(keyValue[0], keyValue[1]);
        }
        return map;
    }

    @FunctionalInterface
    private interface RequestHandler {
        String handle(Map<String, String> data) throws IOException;
    }

    public static void main(String[] args) throws IOException {
        new TriviaServer().startServer();
    }
}
