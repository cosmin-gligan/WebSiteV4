package siit.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import siit.model.User;

import javax.naming.AuthenticationException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class UserDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public User getByName(String name) {
        String sql = "SELECT users.* FROM users WHERE TRIM(users.name) = TRIM(?)";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, this::mapDbUser, name);
        }catch (DataAccessException e){
        }
        return user;
    }

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

//        if  (!user.isActive()) throw new SecurityException("User " + user.getName() + " is no longer active! Please contact the admin");

        return user;
    }

    public User getByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, this::mapDbUser, email);
        } catch (DataAccessException e) {
            //ops
        }
        return user;
    }

    public void inactivateUser(User user) {
        String sql = "Update users SET isactive = FALSE WHERE id = ?";
        jdbcTemplate.update(sql,user.getId());
    }


    public void addUser(User user) {
        String sql = "Insert into users (name, email, pass) values (? , ? , ?)";
        jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPassword());
    }

    public User getByNameOrEmail(String name, String email) {
        String sql = "SELECT users.* FROM users WHERE (users.name ~* ? OR email ~* ?)";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, this::mapDbUser, name, email);
        } catch (DataAccessException e) {
            //ops
        }
        return user;
    }
}
