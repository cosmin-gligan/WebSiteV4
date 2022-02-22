package siit.notes.spring.beans;

public class Ram {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Ram{" +
                "name='" + name + '\'' +
                '}';
    }
}
