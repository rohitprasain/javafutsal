/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Aman
 */
public class BillRepository extends BaseRepository {

    public BillRepository() throws SQLException {
        super();
    }
    
    public boolean insert(Bill bill, ArrayList<BillDetail> billDetails) throws SQLException {   
        connection.setAutoCommit(false);
        
        try {
            int billId = insertBill(bill);
            for(BillDetail billDetail : billDetails) {
                insertBillDetail(billId, billDetail);
            }            
            connection.commit();
            return true;
        }
        catch(SQLException e) {
            connection.rollback();
        }
        finally {
            connection.close();
        }
        return false;
    }

    public int insertBill(Bill bill) throws SQLException {
        String sql = "insert into bills (customer_name, customer_address) "
                + "values (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql, 
                PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, bill.getCustomerName());
        statement.setString(2, bill.getCustomerAddress());
        if(statement.executeUpdate() < 0)
            throw new SQLException("Bill could not be inserted.");
        
        ResultSet rs = statement.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }
    
    public void insertBillDetail(int billId, BillDetail billDetail) throws SQLException {
        String sql = "insert into bill_details values (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, billId);
        statement.setInt(2, billDetail.getProductId());
        statement.setBigDecimal(3, billDetail.getPrice());
        statement.setInt(4, billDetail.getQuantity());
        if(statement.executeUpdate() < 0)
            throw new SQLException("Bill detail could not be inserted.");
    }
    
}
