package siit.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import siit.model.User;

import javax.naming.AuthenticationException;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public User getByNameAndPassword(String name, String password) throws AuthenticationException  {
        String sql = "SELECT users.* FROM users WHERE TRIM(users.name) = TRIM(?) AND pass = crypt(TRIM(?), pass)";
        try {
            return jdbcTemplate.queryForObject(sql, this::mapDbUser, name, password);
        } catch (DataAccessException e){
            throw new AuthenticationException("Wrong username and/or password");
        }
    }

    private User mapDbUser(ResultSet rs, int i) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setActive(rs.getBoolean("isactive"));

        if  (!user.isActive()) throw new SecurityException("User " + user.getName() + " is no longer active! Please contact the admin");

        return user;
    }
}
