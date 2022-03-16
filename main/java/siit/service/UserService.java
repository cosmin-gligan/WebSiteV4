package siit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import siit.db.UserDao;
import siit.exceptions.EmptyResourceException;
import siit.exceptions.ResourceExistsException;
import siit.model.User;

import javax.naming.AuthenticationException;
import java.util.Optional;

@Service
public class UserService implements UserRegistration{
    @Autowired
    UserDao userDao;

    public User getByNameAndPassword(String name, String password) throws AuthenticationException {
        if ( name == null || name.trim().length() == 0 )
            throw new AuthenticationException("Username cannot be blank");
        if ( password == null || password.trim().length() == 0)
            throw new AuthenticationException("Password cannot be blank");

        return userDao.getByNameAndPassword(name, password);

    }

    private Optional<User> getByNameOrEmail(String name, String email){
        return Optional.ofNullable(userDao.getByNameOrEmail(name, email));
    }

    @Override
    public void addUser(User user) throws AuthenticationException {
        if ( user.getName() == null || user.getName().trim().length() == 0)
            throw new EmptyResourceException("Name cannot be blank");
        if ( user.getEmail() == null || user.getEmail().trim().length() == 0)
            throw new EmptyResourceException("Email cannot be blank");
        if ( user.getPassword() == null || user.getPassword().trim().length() == 0)
            throw new EmptyResourceException("Password cannot be blank");
        Optional<User> duplicateUser = getByNameOrEmail(user.getName(), user.getPassword());
        if (!duplicateUser.isEmpty())
            throw new ResourceExistsException("Numele sau email-ul sunt folosite deja! Reincercati");

        userDao.addUser(user);
    }

    @Override
    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    public User getByName(String name) throws AuthenticationException {
        return userDao.getByName(name);
    }

    @Override
    public void inactivateUser(User user) {
        userDao.inactivateUser(user);
    }

}
