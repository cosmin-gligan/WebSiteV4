package siit.notes.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import siit.notes.spring.beans.Computer;
import siit.notes.spring.beans.Motherboard;
import siit.notes.spring.beans.MyBean;
import siit.notes.spring.beans.Ram;

@Configuration
@ComponentScan(basePackages = {"siit.notes.spring.beans"})
public class ProjectConfig {

    @Bean("b1")
    public MyBean myBean1() {
        MyBean bean = new MyBean();
        bean.setText("Hello");
        return bean;
    }

    @Bean("b2")
//    @Primary // annotarea asta defineste default instance cand avem mai multe beans de acelasi tip
    public MyBean myBean2() {
        MyBean bean = new MyBean();
        bean.setText("World");
        return bean;
    }

    @Bean
    public Ram ram() {
        Ram bean = new Ram();
        bean.setName("Ram1");
        return bean;
    }

    @Bean
    public Motherboard motherboard() {
        Motherboard bean = new Motherboard();
        bean.setName("motherboard 1");
        return bean;
    }

    @Bean("c2")
    @Primary
    public Computer computer() {
        Computer c = new Computer();
        c.setMotherboard(motherboard());
        c.setRam(ram());
        c.setName("Computer2");
        return c;
    }

}
