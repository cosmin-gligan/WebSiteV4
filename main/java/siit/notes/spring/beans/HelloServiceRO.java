package siit.notes.spring.beans;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("ro")
public class HelloServiceRO implements HelloService{
    @Override
    public void sayHello() {
        System.out.println("Salut!");
    }
}
