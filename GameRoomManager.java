import java.util.Map;
import java.util.HashMap;

public class GameRoomManager {
    private Map<String, GameRoom> gameRooms;

    public GameRoomManager() {
        gameRooms = new HashMap<>();
        gameRooms.put("default", new GameRoom("default"));
    }

    public GameRoom getRoom(String roomId) {
        return gameRooms.computeIfAbsent(roomId, GameRoom::new);
    }
}
