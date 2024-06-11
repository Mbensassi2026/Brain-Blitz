import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


public class TriviaClient {
    public static void main(String[] args) {
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));


        try {
            System.out.println("Enter username:");
            String username = stdIn.readLine();


            while (true) {
                System.out.println("Enter command (start, quest, leaderboard, answer [A/B/C/D]):");
                String command = stdIn.readLine();


                if ("start".equalsIgnoreCase(command.trim())) {
                    startGame(username);
                } else if ("quest".equalsIgnoreCase(command.trim())) {
                    fetchQuestion(username);
                } else {
                    // For simplicity, keep other commands as is
                    handleOtherCommands(command);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void startGame(String username) throws IOException {
        String url = "http://127.0.0.1:8000/api/start";
        String urlParameters = "username=" + URLEncoder.encode(username, StandardCharsets.UTF_8.toString());


        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;


        URL startUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) startUrl.openConnection();
        conn.setDoOutput(true);
        conn.setInstanceFollowRedirects(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        conn.setUseCaches(false);


        try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
            wr.write(postData);
        }


        int responseCode = conn.getResponseCode();
        System.out.println("Response Code: " + responseCode);


        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();


        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();


        System.out.println("Server says: " + response.toString());
    }


    private static void fetchQuestion(String username) throws IOException {
        String url = "http://127.0.0.1:8000/api/quest";
        String urlParameters = "username=" + URLEncoder.encode(username, StandardCharsets.UTF_8.toString());


        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;


        URL questUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) questUrl.openConnection();
        conn.setDoOutput(true);
        conn.setInstanceFollowRedirects(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        conn.setUseCaches(false);


        try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
            wr.write(postData);
        }


        int responseCode = conn.getResponseCode();
        System.out.println("Response Code: " + responseCode);


        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();


        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();


        System.out.println("Server says: " + response.toString());
    }


    private static void handleOtherCommands(String command) {
        // Handle other commands as needed
        System.out.println("Command: " + command);
    }
}
