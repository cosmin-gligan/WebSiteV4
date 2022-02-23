package siit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.db.UserDao;
import siit.model.User;

import javax.naming.AuthenticationException;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public User getByNameAndPassword(String name, String password) throws AuthenticationException {
        if ( name == null || name.trim().length() == 0 )
            throw new AuthenticationException("Username cannot be blank");
        if ( password == null || password.trim().length() == 0)
            throw new AuthenticationException("Password cannot be blank");

        return userDao.getByNameAndPassword(name, password);

    }
}
