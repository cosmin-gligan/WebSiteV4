package siit.notes.spring.beans;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("es")
public class HelloServiceES implements HelloService{
    @Override
    public void sayHello() {
        System.out.println("Hola!");
    }
}
