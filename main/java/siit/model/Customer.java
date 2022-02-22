package siit.model;

import java.time.LocalDate;
import java.util.List;

public class Customer {
    private int id;
    private String name;
    private String phone;
    private String email;
    LocalDate date;
    List<Order> orders;


    public Customer() {
    }

    public Customer(int id, String name) {
        this(id, name, null, null, null);

    }

    public Customer(int id, String name, String phone) {
        this(id, name, phone, null, null);
    }

    public Customer(int id, String name, String phone, String email, LocalDate date) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", date=" + date +
                '}';
    }

}
