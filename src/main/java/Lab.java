

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionUtil;

/**
 * JDBC stands for Java DataBase Connectivity.  It is utilized to connect our java code with a database.
 * JDBC will allow us to execute SQL statements from java and retrieve the result set of that query to be utilized in java
 *
 * JDBC datatypes to know:
 *  - Connection: Creates an active connection to the database.
 *  - Statement: An object that represents a SQL statement to be executed.
 *  - ResultSet: An object that represents the virtual table return from a query (Only needed for executing DQL statements)
 *
 * Background:
 * Assume we have the following table:
 *      songs table
 *      |   id  |      title        |        artist         |
 *      -----------------------------------------------------
 *      |1      |'Let it be'        |'Beatles'              |
 *      |2      |'Hotel California' |'Eagles'               |
 *      |3      |'Kashmir'          |'Led Zeppelin'         |
 *
 * Assignment: Write JDBC logic in the methods below to achieve the following
 *                  - create a new song in our songs database table
 *                  - retrieve all songs from our database table
 *
 * If this is your first time working with JDBC, I recommend reading through the JDBCWalkthrough file that displays how to use JDBC for a similar scenario.
 */
public class Lab {

    public void createSong(Song song)  {
        //write jdbc code here
        try(Connection connection = ConnectionUtil.getConnection();) {
            String sql = "insert into songs (id, title, artist) values(?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, 4);
            statement.setString(2, "Te amo");
            statement.setString(3, "Anselmo Ralph");

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.print("A new song was successfully inserted.");
            }
            
        } catch (SQLException exception) {
            // TODO: handle exception
            exception.printStackTrace();
        }
    }

    public List<Song> getAllSongs(){
        List<Song> songs = new ArrayList<>();

        //write jdbc code here
        try(Connection connection = ConnectionUtil.getConnection()) {
            String sql = "select * from songs";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                songs.add(new Song(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("artist")));
            }

        } catch (SQLException exception) {
            // TODO: handle exception
            exception.printStackTrace();
        }

        return songs;
    }
}
