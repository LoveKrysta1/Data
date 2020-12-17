package designPattern.strategy.c;

/**
 * 针对于b包中的问题，程序猿需要判断每个鸭子子类，谁会不会叫，谁会不会飞，不会叫的，就重写quack方法，不会
 * 飞的就重写fly方法
 * 这个工作量是很大的！
 * <p>
 * 我们那些不会飞的鸭子，压根就没有fly1方法，不会叫的鸭子，压根就没有quack方法
 * <p>
 * 把这两个经常在子类中变化的方法，从父类中分出来，分成两个接口，Quackable flyable
 */

interface Quackable {
    void quack();
}

interface Flyable {
    void fly();
}

abstract class Duck {

    public void swim() {
        System.out.println("游泳...");
    }

    public abstract void display();
}

class MallardDuck extends Duck implements Quackable, Flyable {
    @Override
    public void display() {
        System.out.println("外观是野鸭");
    }

    @Override
    public void quack() {
        System.out.println("叫");
    }

    @Override
    public void fly() {
        System.out.println("飞");
    }
}

class RedHeadDuck extends Duck implements Quackable, Flyable {

    @Override
    public void display() {
        System.out.println("红头鸭");
    }

    @Override
    public void quack() {
        System.out.println("叫");
    }

    @Override
    public void fly() {
        System.out.println("飞");
    }
}

class RubberDuck extends Duck implements Quackable {
    @Override
    public void display() {
        System.out.println("外观是橡皮鸭");
    }


    @Override
    public void quack() {
        System.out.println("模拟叫");
    }
}

class DecoyDuck extends Duck {

    @Override
    public void display() {
        System.out.println("外观是幼儿鸭");
    }
}

public class AppTest {
    public static void main(String[] args) {
        RubberDuck d = new RubberDuck();
        d.swim();
        d.quack();
        d.display();
    }
}
/**
 * 思考，这样问题解决了吗？没有！
 * <p>
 * 以前是：每加入一个新的鸭子角色，程序员就要判断，这个新鸭子角色是否会飞，是否会叫，不会飞的就重写飞方法
 * 不会叫的就重写叫方法
 * 现在是：每加入一个新的鸭子角色，程序员就要判断，这个新鸭子角色是否会飞，是否会叫，不会飞的就不实现Flyable接口，
 * 不会叫的就不实现Quackable接口
 * <p>
 * 如此，程序员仍然没有减少工作量啊？ 仍然要不断地判断新鸭子角色！
 * <p>
 * 另外一个缺点是，fly和qucck方法没有重用性可言，比如48种鸭子，有8种不会飞，那么飞方法就要在40个鸭子
 * 子类中重复40次
 * <p>
 * 此时，应用杠：老师，从jdk1.8开始，接口中的方法就有默认实现！接口1.8default！此时，48种鸭子，有8种不会飞，那么飞方法只需要
 * 在Flyable中定义一个默认实现即可，只写一次
 * <p>
 * 解释：对于48种鸭子，有12种飞行方法，又该如何？？飞行方法，仍然在子类中重复
 */