package hello.springbootbasic.dto;

public class CarDto {
    private String name;
    private int price;
    private String owner;

    public CarDto() {
    }

    public CarDto(String name, String owner, int price) {
        this.name = name;
        this.owner = owner;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CarDto{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", owner='" + owner + '\'' +
                '}';
    }
}
