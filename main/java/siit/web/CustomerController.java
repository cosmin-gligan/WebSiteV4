package siit.web;

import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import siit.exceptions.ResourceCannotBeDeleted;
import siit.model.Customer;
import siit.service.CustomerService;
import siit.service.OrderService;

import java.net.MalformedURLException;
import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;

import static siit.common.Constants.docxPath;
import static siit.common.Constants.reportsPath;

@Controller
@RequestMapping(path = "/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView renderCustomerList() {
        ModelAndView mav = new ModelAndView("customer-list");
        mav.addObject("customers", customerService.getAllCustomers());
        return mav;
    }

    //    /customers/variabila/edit GET
    @RequestMapping(method = RequestMethod.GET, path = "/{customer_id}/edit")
    public ModelAndView renderCustomerEdit(@PathVariable("customer_id") int id) {
        ModelAndView mav = new ModelAndView("customer-edit");
        mav.addObject("customer", customerService.getBy(id));
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/add/")
    public ModelAndView renderCustomerAdd() {
        ModelAndView mav = new ModelAndView("customer-add");
        Customer customer = new Customer();
        mav.addObject("customer", customer);
        return mav;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{id}/edit")
    public ModelAndView performCustomerEdit(@ModelAttribute Customer customer) {
        ModelAndView mav = new ModelAndView("customer-edit");
        try {
            customerService.update(customer);
            mav.setViewName("redirect:/customers");
        } catch (IllegalArgumentException e) {
            mav.addObject("error", e.getMessage());
            mav.setViewName("customer-edit");
        }

        return mav;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/add/")
    public ModelAndView performCustomerAdd(@ModelAttribute Customer customer) {

        ModelAndView mav = new ModelAndView("customer-add");
        try {
            customerService.insert(customer);
            mav.setViewName("redirect:/customers");
        } catch (IllegalArgumentException e) {
            mav.addObject("error", e.getMessage());
            mav.addObject("customer", customer);
            mav.setViewName("customer-add");
        }
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/invoice")
    @ResponseBody
    public Double getCustomerTotalInvoiceValue(@PathVariable("id") Integer customerID) {

        return customerService.getTotalSales4Customer(customerID);

    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/delete")
    public ModelAndView deleteCustomer(@PathVariable("id") Integer customerID) {
        ModelAndView mav = new ModelAndView("customer-list");
        try {
            customerService.delete(customerID);

            mav = new ModelAndView("redirect:/customers/");
        } catch (ResourceCannotBeDeleted e) {
            mav.addObject("customers", customerService.getAllCustomers());
            mav.addObject("error", e.getMessage());
        }
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/reports")
    public ModelAndView generateReports(@PathVariable("id") Integer customerID) {
        ModelAndView mav = new ModelAndView();

        Customer customer = customerService.getBy(customerID);
        customer.setOrders(orderService.getBy(customerID));

        String reportFile = customerService.generateSalesReport4Customer(customer);

        mav = new ModelAndView("redirect:/reports/" + reportFile);

        return mav;
    }

}
