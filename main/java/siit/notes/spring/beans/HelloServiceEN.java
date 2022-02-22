package siit.notes.spring.beans;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("en")
public class HelloServiceEN implements HelloService {

    @Override
    public void sayHello() {
        System.out.println("Hello!");
    }
}
