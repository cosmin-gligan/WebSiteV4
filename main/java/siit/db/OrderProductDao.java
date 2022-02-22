package siit.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import siit.model.OrderProduct;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderProductDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<OrderProduct> getAllBy(Integer customerId, Integer orderId) {
        String sql = "SELECT op.* FROM orders_products op JOIN products p ON p.id = op.product_id WHERE op.order_id = ? ORDER BY p.name";
        return jdbcTemplate.query(sql, this::extractOrderProduct, orderId);
    }

    private OrderProduct extractOrderProduct(ResultSet rs, int rowNumb) throws SQLException {
        Integer id = rs.getInt("id");
        Integer orderId = rs.getInt("order_id");
        Integer productId = rs.getInt("product_id");
        Double quantity = rs.getDouble("quantity");
        return new OrderProduct(id, orderId, productId, quantity);
    }

    public void update(OrderProduct orderProduct) {
        String sql = "UPDATE orders_products SET quantity = ? WHERE orders_products.id = ?";
        jdbcTemplate.update(sql, orderProduct.getQuantity(), orderProduct.getId());
    }

    public void insert(OrderProduct orderProduct) {
        String sql = "INSERT INTO orders_products( order_id, product_id, quantity ) VALUES ( ?, ?, ? )";
        jdbcTemplate.update(sql, orderProduct.getOrderId(), orderProduct.getProductId(), orderProduct.getQuantity());
    }

    public void delete(int orderProductId) {
        String sql = "DELETE FROM orders_products WHERE orders_products.id = ?";
        jdbcTemplate.update(sql, orderProductId);
    }
}
