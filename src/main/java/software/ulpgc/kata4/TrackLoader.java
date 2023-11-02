package software.ulpgc.kata4;

import java.sql.SQLException;
import java.util.List;

public interface TrackLoader {
    public List<Track> loadAll() throws SQLException;
}
