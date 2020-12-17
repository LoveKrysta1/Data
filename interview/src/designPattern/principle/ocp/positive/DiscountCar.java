package designPattern.principle.ocp.positive;


//这是扩展出来的，不是改出来的
public class DiscountCar extends Car {
    @Override
    public double getPrice() {
        return super.getPrice() * 0.8;
    }
}
