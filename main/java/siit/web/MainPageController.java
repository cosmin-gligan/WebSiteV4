package siit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import siit.exceptions.ResourceCannotBeDeleted;
import siit.model.Customer;
import siit.service.CustomerService;
import siit.service.OrderService;

@Controller
@RequestMapping(path = "/main")
public class MainPageController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView renderCustomerList() {
        ModelAndView mav = new ModelAndView("main-page");
        return mav;
    }

}
