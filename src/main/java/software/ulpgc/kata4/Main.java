package software.ulpgc.kata4;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        try (SqLiteTrackLoader loader = new SqLiteTrackLoader("chinook.db")) {
            List<Track> tracks = loader.loadAll();
            for (Track track : tracks) {
                System.out.println(track);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
