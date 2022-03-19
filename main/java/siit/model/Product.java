package siit.model;

public class Product {
    private Integer id;
    private String name;
    private Double weight;
    private Double price;
    private String image;

    public Product(Integer id) {
        this.id = id;
    }

    public Product(Integer id, String name, Double weight, Double price) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.image = "/images/products/not-found.png";
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getPrice() {
        return price;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}';
    }
}
