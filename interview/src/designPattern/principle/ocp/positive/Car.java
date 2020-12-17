package designPattern.principle.ocp.positive;

public class Car {
    private String brand;
    private String color;
    private boolean louyou;
    private double price;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isLouyou() {
        return louyou;
    }

    public void setLouyou(boolean louyou) {
        this.louyou = louyou;
    }

    public double getPrice() {
        //这是反例，违反了开闭原则，因为这里修改了源代码
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", louyou=" + louyou +
                ", price=" + price +
                '}';
    }
}
