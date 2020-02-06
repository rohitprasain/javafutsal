package dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerRepository extends BaseRepository {

    public CustomerRepository() throws SQLException {
        super();
    }

    public boolean login(String name, String contact , String time)
            throws SQLException {
        Statement statement = connection.createStatement();
        String sql = String.format("select * from customers where " + "name = '%s' "+"contact = '%s' "+" time = '%s'", name, contact , time);
        ResultSet rs = statement.executeQuery(sql);
        return rs.next();
    }

    public boolean insert(String name, String contact , String time)
            throws SQLException {
        Statement statement = connection.createStatement();
        String sql = String.format("insert into customers (name, contact, time) " + "values ('%s', '%s', '%s')", name, contact , time);
        return statement.executeUpdate(sql) > 0;
    }

    public boolean delete(int id)
            throws SQLException {
        Statement statement = connection.createStatement();
        String sql = String.format("delete from customers where id = %d", id);
        return statement.executeUpdate(sql) > 0;
    }

    public boolean update(int id, String name, String contact , String time)
            throws SQLException {
        Statement statement = connection.createStatement();
        String sql = String.format("update customers set name ='%s' , contact = '%s' , time = '%s' where id = %d", name, contact,time, id);
        return statement.executeUpdate(sql) > 0;
    }

    public ArrayList<Customer> getAll() throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "select * from customers order by time asc";
        ResultSet rs = statement.executeQuery(sql);
        ArrayList<Customer> list = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String contact = rs.getString("contact");
            String time = rs.getString("time");
            list.add(new Customer(id, name,contact, time));
        }
        return list;
    }
}
