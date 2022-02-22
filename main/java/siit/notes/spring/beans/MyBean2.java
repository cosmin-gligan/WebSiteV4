package siit.notes.spring.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;


//@Service
//@Repository
//@Controller
@Component
public class MyBean2 {

    private String text;

//    public MyBean2() {
//        text = "default text value";
//    }

    @PostConstruct
    public void init() {
        text = "default value";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "MyBean{" +
                "text='" + text + '\'' +
                '}';
    }
}
