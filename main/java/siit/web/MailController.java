package siit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import siit.exceptions.EmptyResourceException;
import siit.exceptions.ResourceExistsException;
import siit.model.User;
import siit.service.UserService;

import javax.naming.AuthenticationException;

@Controller
@RequestMapping("")

public class MailController {

    @Autowired
    private UserService userService;


    @RequestMapping(method = RequestMethod.GET, path = "/mail/confirmation-email")
    public ModelAndView addUser(@ModelAttribute User user) {
        System.out.println("am ajuns aici: " + user.toString());
        ModelAndView modelAndView = new ModelAndView("mail-content");
        modelAndView.addObject("user", user);

        return modelAndView;
    }


}
