package siit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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


    @RequestMapping(method = RequestMethod.GET, path = "/mail/confirmation/{email}/")
    @ResponseBody
    public ModelAndView addUser(@PathVariable String email) {
        User user = userService.getByEmail(email);
        ModelAndView mv = new ModelAndView("mail-content");
        mv.addObject("user", user);

        return mv;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/mail/after-mail/{email}/")
    @ResponseBody
    public ModelAndView afterEmailSend(@PathVariable String email) {
        ModelAndView mv = new ModelAndView("after-email-confirmation");
        User user  = new User();
        user.setEmail(email);
        mv.addObject("user", user);

        return mv;
    }

}
