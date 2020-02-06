package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseRepository {
    
    protected Connection connection;
    
    public BaseRepository() throws SQLException {
        String url = "jdbc:mariadb://localhost:3306/nccs_a";
        String dbUsername = "root";
        String dbPassword = "";
        connection = DriverManager.getConnection(url, dbUsername, dbPassword);
    }   
}
