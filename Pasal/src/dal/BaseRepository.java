/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Aman
 */
public class BaseRepository {
    
    protected Connection connection;
    
    public BaseRepository() throws SQLException {
        String url = "jdbc:mariadb://localhost:3306/pasal";
        String dbUsername = "root";
        String dbPassword = "";
        connection = DriverManager.getConnection(url, dbUsername, dbPassword);
    }
    
}
