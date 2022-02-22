package siit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import siit.model.OrderProduct;
import siit.service.OrderProductService;

import java.util.List;

@RestController
public class OrderProductsController {
    @Autowired
    OrderProductService orderProductService;
    //    http://localhost:8080/api/customers/1/orders/3/products
    @GetMapping("/api/customers/{cId}/orders/{oId}/products")
    public List<OrderProduct> getAllOrderProductsBy(@PathVariable("cId") Integer customerId, @PathVariable("oId") Integer orderId) {
        return orderProductService.getAllBy(customerId, orderId);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/api/customers/{customerId}/orders/{orderId}/products")
    @ResponseBody
    public OrderProduct addOrUpdateOrderProduct(@PathVariable int customerId, @PathVariable int orderId, @RequestBody OrderProduct orderProduct ){
        return orderProductService.addOrUpdateOrderProduct(customerId, orderId, orderProduct);
    }
    @RequestMapping(method =  RequestMethod.DELETE, path = "/api/customers/{customerId}/orders/{orderId}/products/{orderProductId}")
    public void deleteOrderProduct(@PathVariable int customerId, @PathVariable int orderId, @PathVariable int orderProductId ){
        orderProductService.delete(orderProductId);
    }
}
