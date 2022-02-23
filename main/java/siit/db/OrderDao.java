package siit.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import siit.model.Order;
import siit.model.OrderProduct;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class OrderDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private OrderProductDao orderProductDao;

    public List<Order> getAllBy(Integer customerId) {
        String sql = "SELECT * FROM ORDERS WHERE customer_id = ? ORDER BY orders.number";
        List<Order> orders = jdbcTemplate.query(sql, this::extractOrder, customerId);
        return orders;
    }


    private Order extractOrder(ResultSet rs, int rowNumb) throws SQLException {
        Integer id = rs.getInt("id");
        Integer customerId = rs.getInt("customer_id");
        String name = rs.getString("number");
        LocalDateTime placed = rs.getTimestamp("placed").toLocalDateTime();
        return new Order(id, customerId, name, placed);
    }

    public Order getByNumber(String number) {
        String sql = "SELECT * FROM ORDERS WHERE number = ?";
        Order order = null;
        try {
            order = jdbcTemplate.queryForObject(sql, this::extractOrder, number);
        } catch (DataAccessException e) {
            //ops
        }
        return order;
    }

    public Order getByNumberV2(String number) throws Exception {
        String sql = "SELECT * FROM ORDERS WHERE number = ?";
        Order order;
        try {
            order = jdbcTemplate.queryForObject(sql, this::extractOrder, number);
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new Exception(e);
        }
        return order;

    }

    public void add(Order order) {
        String sql = "INSERT INTO ORDERS(customer_id, number, placed) VALUES (?,?,?)";
        jdbcTemplate.update(sql, order.getCustomerId(), order.getNumber(), order.getPlaced());
    }

    public void delete(int orderId) {
        String sql = "DELETE FROM orders WHERE id = ?";
        jdbcTemplate.update(sql, orderId);
    }
}
