package siit.notes.spring.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

//@Service
@Component
public class Computer {

    @Autowired
    private Ram ram;
    @Autowired //contex.getBean(Motherboard.class)
    private Motherboard motherboard;

    private String name;

    public Computer() {
        name = "computer 1";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ram getRam() {
        return ram;
    }

    public void setRam(Ram ram) {
        this.ram = ram;
    }

    public Motherboard getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(Motherboard motherboard) {
        this.motherboard = motherboard;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "ram=" + ram +
                ", motherboard=" + motherboard +
                ", name='" + name + '\'' +
                '}';
    }
}
