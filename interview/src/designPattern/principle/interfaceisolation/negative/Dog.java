package designPattern.principle.interfaceisolation.negative;

public class Dog implements Animal {
    @Override
    public void eat() {
        System.out.println("够啃骨头");
    }

    @Override
    public void swim() {
        System.out.println("狗刨式");
    }

    @Override
    public void fly() {
        throw new RuntimeException("你行你上");
    }
}
