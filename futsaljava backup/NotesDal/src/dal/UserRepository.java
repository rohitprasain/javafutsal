package dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserRepository extends BaseRepository {

    public UserRepository() throws SQLException {
        super();
    }

    public boolean login(String username, String password)
            throws SQLException {
        Statement statement = connection.createStatement();
        String sql = String.format("select * from users where " + "username = '%s' and password = '%s'", username, password);
        ResultSet rs = statement.executeQuery(sql);
        return rs.next();
    }

    public boolean insert(String username, String password)
            throws SQLException {
        Statement statement = connection.createStatement();
        String sql = String.format("insert into users (username, password) " + "values ('%s', '%s')", username, password);
        return statement.executeUpdate(sql) > 0;
    }

    public boolean delete(int id)
            throws SQLException {
        Statement statement = connection.createStatement();
        String sql = String.format("delete from users where user_id = %d", id);
        return statement.executeUpdate(sql) > 0;
    }

    public boolean update(int id, String username, String password)
            throws SQLException {
        Statement statement = connection.createStatement();
        String sql = String.format("update users set username ='%s' , password = '%s' where user_id = %d", username, password, id);
        return statement.executeUpdate(sql) > 0;
    }

    public ArrayList<User> getAll() throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "select * from users";
        ResultSet rs = statement.executeQuery(sql);
        ArrayList<User> list = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("user_id");
            String username = rs.getString("username");
            String password = rs.getString("password");
            list.add(new User(id, username, password));
        }
        return list;
    }
}
