package siit.db;

import siit.config.DatabaseConfig;
import siit.model.Customer;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerDaoOld {

    DataSource dataSource;

    public CustomerDaoOld() {
        this.dataSource = DatabaseConfig.getInstance();
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT ID, NAME, BIRTHDAY FROM CUSTOMERS")) {

            while (rs.next()){
                customers.add(extractCustomer(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    private Customer extractCustomer(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
//        LocalDate bDay = rs.getDate("birthday").toLocalDate();

        return new Customer(id, name);
    }

}
