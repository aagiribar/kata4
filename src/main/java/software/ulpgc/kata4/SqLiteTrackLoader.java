package software.ulpgc.kata4;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqLiteTrackLoader implements TrackLoader, Closeable {
    private final Connection connection;
    private final static String SQLQuery = "select tracks.Name,Title,genres.Name,Composer,Milliseconds,Bytes,UnitPrice from tracks,albums,genres where tracks.TrackId = tracks.TrackId and tracks.AlbumId = albums.AlbumId and tracks.GenreId = genres.GenreId";

    public SqLiteTrackLoader(String filename) throws SQLException {
        connection = DriverManager.getConnection(getURL(filename));
    }

    private String getURL(String filename) {
        return "jdbc:sqlite:" + filename;
    }

    @Override
    public List<Track> loadAll() throws SQLException {
        return queryAll();
    }

    private List<Track> queryAll() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQLQuery);
        return toList(resultSet);
    }

    private List<Track> toList(ResultSet resultSet) throws SQLException {
        List<Track> result = new ArrayList<>();
        while (true) {
            if (resultSet.next()) {
                result.add(new Track(
                        resultSet.getString("tracks.Name"),
                        resultSet.getString("Title"),
                        resultSet.getString("genres.Name"),
                        resultSet.getString("Composer"),
                        resultSet.getInt("Milliseconds"),
                        resultSet.getInt("Bytes"),
                        resultSet.getDouble("UnitPrice")
                ));
            }
            else return result;
        }
    }

    @Override
    public void close() throws IOException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
