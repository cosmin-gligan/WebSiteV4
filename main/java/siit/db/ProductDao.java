package siit.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import siit.model.Customer;
import siit.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Product getBy(Integer productId) {
        String sql = "SELECT * FROM products p WHERE id =?";
        return jdbcTemplate.queryForObject(sql, this::extractProduct, productId);
    }

    private Product extractProduct(ResultSet rs, int rowNumb) throws SQLException {
        Integer id = rs.getInt("id");
        String name = rs.getString("name");
        Double weight = rs.getDouble("weight");
        Double price = rs.getDouble("price");
        String image = rs.getString("image");
        return new Product(id, name, weight, price, image);
    }

    public List<Product> getAllByName(String name) {
        String sql = "SELECT p.* FROM products p WHERE p.name ~* ? ORDER BY p.name";
        return jdbcTemplate.query(sql, this::extractProduct, name);
    }

    public List<Product> getAll() {
        String sql = "SELECT p.* FROM products p ORDER BY p.name";
        return jdbcTemplate.query(sql, this::extractProduct);
    }

    public void delete(Integer productID) {
        String sql = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(sql, productID);
    }

    public void update(Product product) {
        String sql = "UPDATE products SET name = ?, weight = ?, price = ? WHERE products.id = ?";
        jdbcTemplate.update(sql, product.getName(), product.getWeight(), product.getPrice(), product.getId());
    }

    public void insert(Product product) {
        String sql = "INSERT INTO products ( name, weight, price ) VALUES ( ?, ?, ? )";
        jdbcTemplate.update(sql, product.getName(), product.getWeight(), product.getPrice());
    }

    public Product getByName(String name) {
        Product product = null;

        String sql = "SELECT * FROM products WHERE name = ?";

        try {
            return jdbcTemplate.queryForObject(sql, this::extractProduct, name);
        } catch ( EmptyResultDataAccessException e) {
            //nu am tratat intentionat, sa mearga NULL daca nu exista
        }
        return product;
    }
}
