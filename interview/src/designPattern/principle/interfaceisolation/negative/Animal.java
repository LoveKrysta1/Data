package designPattern.principle.interfaceisolation.negative;

public interface Animal {
    void eat();

    void swim();//这就违反了接口隔离原则了

    void fly();
}
