package siit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import siit.email.RegistrationEmail;
import siit.exceptions.EmptyResourceException;
import siit.exceptions.ResourceExistsException;
import siit.model.User;
import siit.service.UserService;

import javax.naming.AuthenticationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@Controller
@RequestMapping("")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RegistrationEmail registrationEmail;

    @RequestMapping(method = RequestMethod.GET, path = "/user/add")
    public ModelAndView renderCustomerOrderAddPage() {
        ModelAndView mav = new ModelAndView("user-edit");
        return mav;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/user/add")
    public ModelAndView addUser(@ModelAttribute User user) {
        ModelAndView modelAndView = new ModelAndView("user-edit");
        try {
            userService.addUser(user);
            registrationEmail.sendEmail(registrationEmail.getEmailContent(user), user);
            modelAndView.setViewName("redirect:/mail/after-mail/" + user.getEmail() + "/");
            modelAndView.addObject("user", user);
        } catch (AuthenticationException
                    | ResourceExistsException
                    | EmptyResourceException
                    | InterruptedException
                    | ExecutionException
                    |TimeoutException
        e) {
            modelAndView.addObject("error", e.getMessage());
        }

        return modelAndView;
    }


}
