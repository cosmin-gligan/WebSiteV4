package siit.web;

import siit.db.CustomerDaoOld;
import siit.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/customers_db")
public class HelloCustomersDB extends HttpServlet {

    CustomerDaoOld customerDaoOld = new CustomerDaoOld();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.append("<html><table border=1><tr><th>ID</th><th>Name</th></tr>");

        List<Customer> allCustomers = customerDaoOld.getAllCustomers();
        for (Customer customer : allCustomers) {
            writer.append("<tr><td>")
                    .append(String.valueOf(customer.getId()))
                    .append("</td><td>")
                    .append(customer.getName())
                    .append("</td></tr>");
        }

        writer.append("</table></html>");
    }
}
