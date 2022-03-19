package siit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import siit.exceptions.EmptyResourceException;
import siit.exceptions.ResourceExistsException;
import siit.model.Customer;
import siit.model.Order;
import siit.service.CustomerService;
import siit.service.OrderService;
import siit.utils.HttpUtils;
import siit.utils.PdfUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static siit.common.Constants.pdfPath;

@Controller
@RequestMapping("")
public class OrderController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;

    @Resource
    HttpUtils httpUtils;

    @Resource
    PdfUtils pdfUtils;



    @RequestMapping(method = RequestMethod.GET, path = "/customers/{customerId}/orders")
    public ModelAndView renderOrderPage(@PathVariable Integer customerId) {
        ModelAndView mav = new ModelAndView("customer-orders");
        Customer dbCustomer = customerService.getBy(customerId);
        mav.addObject("customer", dbCustomer);

        return mav;
    }

    //    http://localhost:8080/customers/1/orders/add
    @RequestMapping(method = RequestMethod.GET, path = "/customers/{customerId}/orders/add")
    public ModelAndView renderCustomerOrderAddPage(@PathVariable Integer customerId) {
        ModelAndView mav = new ModelAndView("customer-order-add");
        Customer customer = customerService.getBy(customerId);
        mav.addObject("customer", customer);
        return mav;
    }

    //    http://localhost:8080/customers/1/orders/add
    @RequestMapping(method = RequestMethod.POST, path = "/customers/{customerId}/orders/add")
    public ModelAndView addOrder(@ModelAttribute Order order) {
        ModelAndView modelAndView = new ModelAndView("customer-order-add");
        try{
            orderService.add(order);
            modelAndView.setViewName("redirect:/customers/" + order.getCustomerId() + "/orders");
        } catch (EmptyResourceException | ResourceExistsException e){
            Customer customer = new Customer();
            customer.setId(order.getCustomerId());
            modelAndView.addObject("customer", customer);
            modelAndView.addObject("error", e.getMessage());
        }

        return modelAndView;
    }



//    http://localhost:8080/api/customers/1/orders/3
    @RequestMapping(method = RequestMethod.GET, path = "/api/customers/{cId}/orders/{oId}")
    @ResponseBody
    public Order getOrderForOrderEdit(@PathVariable("cId") Integer customerId, @PathVariable("oId") Integer orderId){
        return orderService.getBy(customerId, orderId);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/customers/{customerId}/orders/{orderId}/delete")
    public ModelAndView deleteOrder(@PathVariable int customerId, @PathVariable int orderId){
        ModelAndView mav = new ModelAndView("redirect:/customers/" + customerId + "/orders");
        orderService.delete(orderId);
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/customers/{customerId}/orders/{orderId}/printPDF")
    @ResponseBody
    public ModelAndView printPDF(@PathVariable int customerId, @PathVariable int orderId) {
        Order order = orderService.getBy(customerId, orderId);

        String htmlInvoice = null;
        String fileName = order.getNumber() + ".pdf";


        pdfUtils.printPDF("/api/print/" + order.getCustomerId() + "/orders/" + order.getId(), "/invoices/" + fileName );

        ModelAndView mav = new ModelAndView("redirect:/invoices/" + fileName);

        mav.addObject("order", order);

        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/print/{customerId}/orders/{orderId}")
    public ModelAndView printHTML(@PathVariable int customerId, @PathVariable int orderId) {
        ModelAndView mav = new ModelAndView("invoicePDF");
        Order order = orderService.getBy(customerId, orderId);
        mav.addObject("order", order);

        return mav;
    }



    @GetMapping(value="/invoices/{fileName}",produces= MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody byte[] displayPDF(@PathVariable String fileName) {
        try {
            FileInputStream fis = new FileInputStream(new File(pdfPath + "invoices/" + fileName));
            byte[] targetArray = new byte[fis.available()];
            fis.read(targetArray);
            return targetArray;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}
