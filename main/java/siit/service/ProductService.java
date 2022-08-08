package siit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.db.ProductDao;
import siit.exceptions.ResourceExistsException;
import siit.model.Customer;
import siit.model.Product;

import java.util.List;
import java.util.Optional;

import static siit.utils.CustomValidators.nameValidator;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

    public Product getBy(Integer productId) {
        return productDao.getBy(productId);
    }

    public List<Product> getAllByName(String name){
        return productDao.getAllByName(name);
    }
    public List<Product> getAll(){
        return productDao.getAll();
    }

    public void delete(Integer productID) {
        productDao.delete(productID);
    }

    public void update(Product product) {
        productDao.update(product);
    }

    public void insert(Product product) {
        productDao.insert(product);
    }

    private void checkProduct(Product product){
        nameValidator(product.getName());
        Optional<Product> duplicateCustomer = Optional.ofNullable(getByName(product.getName()));
        if (!duplicateCustomer.isEmpty())
            throw new ResourceExistsException("There already exists a product with the name " + product.getName());
    }

    private Product getByName(String name) {
        return productDao.getByName(name);
    }

}
