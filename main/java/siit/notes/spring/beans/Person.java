package siit.notes.spring.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Person {
    HelloService helloService1;
    HelloService helloService2;

    @Autowired
    public Person(@Qualifier("es") HelloService helloService) {
        this.helloService1 = helloService;
    }

    public void sayHello() {
        helloService1.sayHello();
        helloService2.sayHello();
    }


    @Autowired
    public void setHelloService2(@Qualifier("ro") HelloService helloService) {
        this.helloService2 = helloService;
    }
}
