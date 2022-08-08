package siit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import siit.exceptions.ResourceCannotBeDeleted;
import siit.model.Customer;
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

    @RequestMapping(method = RequestMethod.GET, path = "/products")
    public ModelAndView renderProductList() {
        ModelAndView mav = new ModelAndView("product-list");
        mav.addObject("products", productService.getAll());
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/products/{id}/delete")
    public ModelAndView deleteProduct(@PathVariable("id") Integer productID) {
        ModelAndView mav = new ModelAndView("product-list");
        try {
            productService.delete(productID);

            mav = new ModelAndView("redirect:/products/");
        } catch (ResourceCannotBeDeleted e) {
            mav.addObject("products", productService.getAll());
            mav.addObject("error", e.getMessage());
        }
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/products/{product_id}/edit")
    public ModelAndView renderCustomerEdit(@PathVariable("product_id") int id) {
        ModelAndView mav = new ModelAndView("product-edit");
        mav.addObject("product", productService.getBy(id));
        return mav;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/products/{id}/edit")
    public ModelAndView performCustomerEdit(@ModelAttribute Product product) {
        ModelAndView mav = new ModelAndView("product-edit");
        try {
            productService.update(product);
            mav.setViewName("redirect:/products");
        } catch (IllegalArgumentException e) {
            mav.addObject("error", e.getMessage());
            mav.setViewName("product-edit");
        }

        return mav;
    }


    @RequestMapping(method = RequestMethod.GET, path = "/products/add/")
    public ModelAndView renderProductAdd() {
        ModelAndView mav = new ModelAndView("product-add");
        Product product = new Product();
        mav.addObject("product", product);
        return mav;
    }


    @RequestMapping(method = RequestMethod.POST, path = "/products/add/")
    public ModelAndView performProductAdd(@ModelAttribute Product product) {

        ModelAndView mav = new ModelAndView("product-add");
        try {
            productService.insert(product);
            mav.setViewName("redirect:/products");
        } catch (IllegalArgumentException e) {
            mav.addObject("error", e.getMessage());
            mav.addObject("product", product);
            mav.setViewName("product-add");
        }
        return mav;
    }
}
