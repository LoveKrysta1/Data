package designPattern.principle.interfaceisolation.positive;

public class Dog implements Eatable, Swimable {
    @Override
    public void eat() {
        System.out.println("吃");
    }

    @Override
    public void swim() {
        System.out.println("游泳");
    }
}
