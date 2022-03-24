package siit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import siit.db.CustomerDao;
import siit.exceptions.ResourceCannotBeDeleted;
import siit.exceptions.ResourceExistsException;
import siit.model.Customer;
import siit.model.Order;
import siit.reports.xls.CustomerReports;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static siit.utils.CustomValidators.*;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CustomerReports customerReports;

    public void update(Customer customer) {
        phoneNoValidator(customer.getPhone());
        nameValidator(customer.getName());
        emailValidator(customer.getEmail());
        customerDao.update(customer);
    }

    public void insert(Customer customer) {
//        System.out.println(customer );
        Optional<Customer> duplicateCustomer = Optional.ofNullable(getByName(customer.getName()));
        if (!duplicateCustomer.isEmpty())
            throw new ResourceExistsException("There already exists a customer with the name " + customer.getName());
        nameValidator(customer.getName());
        phoneNoValidator(customer.getPhone());
        emailValidator(customer.getEmail());
        customer.setBirthday(LocalDate.of(1990, 01, 01));

        customerDao.add(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    public Customer getBy(int id) {
        Customer customer = customerDao.getBy(id); // fara orders
        customer.setOrders(orderService.getBy(id));

        return customer;
    }

    public Customer getByName(String name) {
        return customerDao.getByName(name);
    }

    public Double getTotalSales4Customer(int id) throws EmptyResultDataAccessException {
        Customer customer = customerDao.getBy(id); // fara orders
        customer.setOrders(orderService.getBy(id));

        double totalVanzari = 0;

        for (Order order : customer.getOrders()) {

            totalVanzari += order.getValue();

        }

        return totalVanzari;
    }


    public void delete(Integer customerID) {
        Customer customer = customerDao.getBy(customerID);
        customer.setOrders(orderService.getBy(customerID));
        if (customer.getOrders().size() != 0)
            throw new ResourceCannotBeDeleted("Clientul are comenzi introduse! Nu poate fi sters.");
        customerDao.delete(customerID);
    }

    public String generateSalesReport4Customer(Customer customer){
        return customerReports.generateSalesReport(customer);
    }

}
