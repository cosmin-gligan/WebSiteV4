package siit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.db.OrderDao;
import siit.exceptions.EmptyResourceException;
import siit.exceptions.ResourceExistsException;
import siit.model.Order;
import siit.model.OrderProduct;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderProductService orderProductService;

    @Autowired
    private CustomerService customerService;

    public List<Order> getBy(Integer customerId) {
        List<Order> orders = orderDao.getAllBy(customerId);
        for (Order order: orders){
            List<OrderProduct> orderProducts = orderProductService.getAllBy(customerId, order.getId());
            order.setOrderProducts(orderProducts);
        }

        return orders;
    }

    public Order getBy(Integer customerId, Integer orderId) {
        List<Order> orders = getBy(customerId);
        for (Order order : orders) {
            if (order.getId().equals(orderId)) {
                order.setCustomer(customerService.getBy(customerId));
                return order;
            }
        }
        throw new RuntimeException("Order with id = " + orderId + " does not exist!");
    }

    public Order getByNumber(String number) {
        return orderDao.getByNumber(number);
    }

    public boolean getByNumberV2(String number) {
        Boolean result = false;
        try {
            orderDao.getByNumberV2(number);
        } catch (Exception e) {
            result = true;
        }
        return result;
    }

    public void add(Order order) {
        if ( order.getNumber() == null || order.getNumber().trim().length() == 0)
            throw new EmptyResourceException("Order number cannot be blank");

        Order duplicateOrder = getByNumber(order.getNumber());
        if (duplicateOrder != null ){
            throw new ResourceExistsException("Order with number " + order.getNumber() + " already exists !");
        }
        order.setPlaced(LocalDateTime.now());
        orderDao.add(order);
    }

    public void submitOrder(Order order){

        orderDao.submitOrder(order);

    }

    public void delete(int orderId) {
        orderDao.delete(orderId);
    }
}
