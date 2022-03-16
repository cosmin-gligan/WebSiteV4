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
////    http://localhost:8080/user/{customerId}/orders GET
public class UserController {

    @Autowired
    private UserService userService;

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
            modelAndView.addObject("user", user);
            modelAndView.setViewName("redirect:/mail/confirmation-email");
        } catch (AuthenticationException | ResourceExistsException | EmptyResourceException e) {
            modelAndView.addObject("error", e.getMessage());
        }

        return modelAndView;
    }


}
