package siit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.model.OrderProduct;
import siit.db.OrderProductDao;
import siit.model.Product;

import java.util.List;

@Service
public class OrderProductService {
    @Autowired
    OrderProductDao orderProductsDao;
    @Autowired
    ProductService productService;

    public List<OrderProduct> getAllBy(Integer customerId, Integer orderId) {
        List<OrderProduct> orderProducts = orderProductsDao.getAllBy(customerId, orderId);
        for(OrderProduct orderProduct : orderProducts) {
            Product product = productService.getBy(orderProduct.getProductId());
            orderProduct.setProduct(product);
        }

        return orderProducts;
    }


    public OrderProduct addOrUpdateOrderProduct(int customerId, int orderId, OrderProduct orderProduct) {
        List<OrderProduct> orderProducts = getAllBy(customerId, orderId);

        for ( OrderProduct item : orderProducts){
            if ( item.getProductId().equals(orderProduct.getProductId()) ){
                item.setQuantity(item.getQuantity() + orderProduct.getQuantity());
                update(item);
                return item;
            }
        }
        //produsul nu exista in Order, il adaugam
        orderProduct.setProduct(productService.getBy(orderProduct.getProductId()));
        OrderProduct orderProductInsert = orderProductsDao.insert(orderProduct);
        orderProduct.setId(orderProductInsert.getId());
        return orderProduct;
    }

    private void update(OrderProduct orderProduct) {
        orderProductsDao.update(orderProduct);
    }

    private OrderProduct insert(OrderProduct orderProduct){
        return orderProductsDao.insert(orderProduct);
    }

    public void delete(int orderProductId) {
        orderProductsDao.delete(orderProductId);
    }
}
