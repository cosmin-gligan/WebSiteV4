package siit.notes.spring.beans;

public class MyBean {
    private String text;

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
