package siit.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Order {
    private Integer id;
    private Integer customerId;
    private String number;
    private LocalDateTime placed;
    private LocalDateTime delivery;
    private int statuscode;
    private List<OrderProduct> orderProducts;
    private Customer customer;

    public Order() {
    }

    public Order(Integer id, Integer customerId, String number, LocalDateTime placed, int statuscode) {
        this.id = id;
        this.customerId = customerId;
        this.number = number;
        this.placed = placed;
        this.delivery = LocalDateTime.now().plus(1, ChronoUnit.DAYS);
        this.statuscode = statuscode;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setPlaced(LocalDateTime placed) {
        this.placed = placed;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public String getNumber() {
        return number;
    }

    public LocalDateTime getPlaced() {
        return placed;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getDelivery() {
        return delivery;
    }

    public void setDelivery(LocalDateTime delivery) {
        this.delivery = delivery;
    }

    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }

    public Double getValue() {
        double sum = 0.0;
        for (OrderProduct orderProduct: getOrderProducts()){
            sum += orderProduct.getValue();
        }
        return sum;
    }
}
