package siit.service;

import siit.model.User;

import javax.naming.AuthenticationException;
import java.util.Optional;

public interface UserRegistration {

    public void addUser(User user) throws AuthenticationException;
    public User getByEmail(String email);
    public void inactivateUser(User user);

}
