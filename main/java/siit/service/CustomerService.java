package siit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import siit.db.CustomerDao;
import siit.model.Customer;
import siit.model.Order;
import siit.utils.NumberUtils;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private OrderService orderService;

    public void update(Customer customer) {
//        phone number Validation -> phone sa contina doar cifre, un anumit numar de caractere
        phoneNoValidator(customer.getPhone());
        nameValidator(customer.getName());
        customerDao.update(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    public Customer getBy(int id) {
        Customer customer = customerDao.getBy(id); // fara orders
        customer.setOrders(orderService.getBy(id));

        return customer;
    }

    public Double getTotalSales4Customer(int id) throws EmptyResultDataAccessException {
        Customer customer = customerDao.getBy(id); // fara orders
        customer.setOrders(orderService.getBy(id));

        double totalVanzari = 0;

        for (Order order : customer.getOrders()){

            totalVanzari += order.getValue();

        }
        //http://localhost:8080/customers/1/invoice
//        System.out.println("Total vanzari 4 Customer ID " + totalVanzari);

        return totalVanzari;
    }

    private void phoneNoValidator(String phoneNo) {
        if (phoneNo == null || phoneNo.trim().length() == 0) {
            throw new IllegalArgumentException("Numarul de telefon nu a fost completat !");
        }

        char[] phoneNoDigits = phoneNo.toCharArray();
        for (int i = 0; i < phoneNoDigits.length; i++) {
            if (i == 0) {
                if (String.valueOf(phoneNoDigits[i]).equals("+"))
                    continue; // primul caracter e plus, trecem mai departe
            }
            NumberUtils.validateDigit(String.valueOf(phoneNoDigits[i]));
        }
    }

    public void nameValidator(String name){
        if (name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException("Name cannot be blank!");
        }
    }


}
