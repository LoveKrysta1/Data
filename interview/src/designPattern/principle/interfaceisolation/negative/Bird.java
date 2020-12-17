package designPattern.principle.interfaceisolation.negative;

public class Bird implements Animal {
    @Override
    public void eat() {
        System.out.println("吃");
    }

    @Override
    public void swim() {
        throw new RuntimeException("你行你上");
    }

    @Override
    public void fly() {
        System.out.println("我飞");
    }
}
