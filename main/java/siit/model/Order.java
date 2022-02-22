package siit.model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private Integer id;
    private Integer customerId;
    private String number;
    private LocalDateTime placed;
    private List<OrderProduct> orderProducts;

    public Order() {
    }

    public Order(Integer id, Integer customerId, String number, LocalDateTime placed) {
        this.id = id;
        this.customerId = customerId;
        this.number = number;
        this.placed = placed;
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

    public Double getValue() {
        double sum = 0.0;
        for (OrderProduct orderProduct: getOrderProducts()){
            sum += orderProduct.getValue();
        }
        return sum;
    }
}
