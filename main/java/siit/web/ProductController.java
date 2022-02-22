package siit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import siit.model.Product;
import siit.service.OrderProductService;
import siit.service.ProductService;

import java.util.List;

@Controller
@RequestMapping(path = "")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    OrderProductService orderProductService;

    @GetMapping("/api/products")
    @ResponseBody
    public List<Product> getProductsByName(@RequestParam("term") String name){
        return productService.getAllByName(name);
    }

}
