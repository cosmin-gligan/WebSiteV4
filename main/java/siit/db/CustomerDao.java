package siit.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import siit.config.DatabaseConfig;
import siit.model.Customer;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Customer> getAllCustomers() {
        return jdbcTemplate.query("SELECT * FROM customers ORDER BY customers.name", this::extractCustomer);

    }

    public Customer getBy(int customerId) throws EmptyResultDataAccessException {
        String sql = "SELECT * FROM CUSTOMERS WHERE id = ?";

        try {
            return jdbcTemplate.queryForObject(sql, this::extractCustomer, customerId);
        } catch ( EmptyResultDataAccessException e) {
            String errorMsg = "NU am gasit customerul cu ID-ul " + customerId;
            throw new EmptyResultDataAccessException(errorMsg, 1);
        }
    }

    private Customer extractCustomer(ResultSet rs, int rowNumb) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String phone = rs.getString("phone");
        String email = rs.getString("email");
        String address = rs.getString("address");
        LocalDate birthday = rs.getDate("birthday").toLocalDate();

        return new Customer(id, name, phone, email, birthday, address);
    }

    public void update(Customer customer) {
        String sql = "UPDATE CUSTOMERS SET name=?, phone=?, email = ?, address = ? WHERE id =?";
        jdbcTemplate.update(sql, customer.getName(), customer.getPhone(), customer.getEmail(), customer.getAddress(),customer.getId());
    }

    public void add(Customer customer){

        String sql = "INSERT INTO customers(name, phone, email, address, birthday) VALUES ( ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, customer.getName(), customer.getPhone(), customer.getEmail(), customer.getAddress(), customer.getBirthday());

    }

    public Customer getByName(String name) {
        Customer customer = null;
        String sql = "SELECT * FROM CUSTOMERS WHERE name = ?";

        try {
            return jdbcTemplate.queryForObject(sql, this::extractCustomer, name);
        } catch ( EmptyResultDataAccessException e) {
            //nu am tratat intentionat, sa mearga NULL daca nu exista
        }
        return customer;
    }

    public void delete(Integer customerID) {
        String sql = "DELETE FROM customers WHERE id = ?";
        jdbcTemplate.update(sql, customerID);
    }
}
