import java.util.HashMap;
import java.util.Map;

public class UserStore {
    private Map<String, String> users = new HashMap<>(); // Stores usernames and passwords

    public boolean authenticateUser(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    public boolean registerUser(String username, String password) {
        if (users.containsKey(username)) {
            return false; // User already exists
        }
        users.put(username, password);
        return true;
    }

    public User getUser(String username) {
        String password = users.get(username);
        return new User(username, password); // assuming User class has this constructor
    }
}
