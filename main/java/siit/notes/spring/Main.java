package siit.notes.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import siit.model.Customer;
import siit.notes.spring.beans.*;
import siit.notes.spring.config.ProjectConfig;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class, DatabaseConfig.class);

        MyBean bean1 = context.getBean("b1", MyBean.class);
        MyBean bean2 = context.getBean("b2", MyBean.class);
        MyBean bean3 = context.getBean("b1", MyBean.class);

//      aceeiasi instanta pentru toate referintele (singleton)
//        bean1.setText("Alt text");

        System.out.println(bean1);
        System.out.println(bean2);
        System.out.println(bean3);
        System.out.println("-----------------------------------");
        MyBean2 myBean2 = context.getBean(MyBean2.class);
        System.out.println(myBean2);

        System.out.println("-----------------------------------");
        Computer computer = context.getBean(Computer.class);
        System.out.println(computer);

        System.out.println("-----------------------------------");
        Person person = context.getBean(Person.class);
        person.sayHello();

        System.out.println("-----------------------------------");
        JdbcTemplate jdbcTemplate = context.getBean("myJdbcTemplate", JdbcTemplate.class);
//        jdbcTemplate.update("", Collections.emptyList());
        Customer c = new Customer(0, "Generatedname", "0975754473", "generatedEmail@email.com", LocalDate.now());
        String query = "INSERT INTO CUSTOMERS(name, phone, email) VALUES(?, ?, ?)";
        jdbcTemplate.update(query, c.getName(), c.getPhone(), c.getEmail());
        System.out.println("S-a inserat customerul = " + c);


        String selectQuery = "select * from CUSTOMERS;";

        List<Customer> customers = jdbcTemplate.query(selectQuery, customerMapper());

        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    private static RowMapper<Customer> customerMapper() {
        return new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
                Date date = rs.getDate("birthday");
                Customer customer = new Customer(rs.getInt("id"), rs.getString("name"));
                customer.setDate(date == null ? LocalDate.now() : date.toLocalDate());
                return customer;
            }
        };
    }
}
