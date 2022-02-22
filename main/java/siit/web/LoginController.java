package siit.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
//    @GetMapping
    protected ModelAndView displayLoginForm() {
        ModelAndView mav = new ModelAndView("login");
        return mav;

    }

    @RequestMapping(method = RequestMethod.POST)
    protected ModelAndView performLogin(HttpSession session, @RequestParam("user") String userName, @RequestParam String password) {
        ModelAndView mav = new ModelAndView();

        if (userName.equals(password)) {
            //logare cu succes
//            mav.addObject("logged_user", userName);
            session.setAttribute("logged_user", userName);
            mav.setViewName("redirect:/customers");
        } else {
            String errorMessage = "User and password do not match!";
            mav.addObject("error", errorMessage);
            mav.setViewName("login");
        }
        return mav;
    }
}
