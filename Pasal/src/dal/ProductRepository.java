/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Aman
 */
public class ProductRepository extends BaseRepository {

    public ProductRepository() throws SQLException {
        super();
    }
    
    public ArrayList<Product> getAll() throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "select * from products";
        ArrayList<Product> products = new ArrayList<>();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("id");
            String productName = rs.getString("product_name");
            BigDecimal price = rs.getBigDecimal("price");
            int quantity = rs.getInt("quantity");
            products.add(new Product(id, productName, price, quantity));
        }
        connection.close();
        return products;
    }
    
}