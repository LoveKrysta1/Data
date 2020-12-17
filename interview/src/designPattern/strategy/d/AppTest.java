package designPattern.strategy.d;

/**
 * 针对于c包的问题：需改代码如下
 * <p>
 * 是时候，把飞行和叫方法，从鸭子类中分离出来了！(注意，这里的分离，与c包中分离不一样)
 */

interface FlyBehavior {
    public void fly();
}

class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("用翅膀飞");
    }
}

class FlyWithRocket implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("背上绑个窜天猴，飞！！！！");
    }
}

class FlyWithKick implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("被人一脚踢飞！！！");
    }
}

interface QuackBehavior {
    void quack();
}

class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("嘎嘎叫");
    }
}

class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("吱吱叫");
    }
}

class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("沉默的叫声");
    }
}

abstract class Duck {

    protected FlyBehavior fb;
    protected QuackBehavior qb;

    public void swim() {
        System.out.println("游泳...");
    }

    public abstract void display();

    public void performFly() {
        fb.fly();
    }

    public void performQuack() {
        qb.quack();
    }

    public FlyBehavior getFb() {
        return fb;
    }

    public void setFb(FlyBehavior fb) {
        this.fb = fb;
    }

    public QuackBehavior getQb() {
        return qb;
    }

    public void setQb(QuackBehavior qb) {
        this.qb = qb;
    }
}

class MallardDuck extends Duck {
    public MallardDuck() {
        this.fb = new FlyWithWings();
        this.qb = new Quack();
    }

    @Override
    public void display() {
        System.out.println("外观是野鸭");
    }

}

class RedHeadDuck extends Duck {
    public RedHeadDuck() {
        this.fb = new FlyWithWings();
        this.qb = new Quack();
    }

    @Override
    public void display() {
        System.out.println("红头鸭");
    }

}

class RubberDuck extends Duck {
    public RubberDuck() {
        this.fb = new FlyWithWings();
        this.qb = new Squeak();
    }

    @Override
    public void display() {
        System.out.println("外观是橡皮鸭");
    }
}

class DecoyDuck extends Duck {

    @Override
    public void display() {
        System.out.println("外观是幼儿鸭");
    }
}

//=========================================
class FlyWithCat implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("头上绑个竹蜻蜓");
    }
}

public class AppTest {
    public static void main(String[] args) {
        RubberDuck d = new RubberDuck();
        d.setFb(new FlyWithCat());

        d.performFly();

        d.performQuack();
        d.swim();
        d.display();
    }
}
/**
 * 此时，针对于48中鸭子，有12种飞行方法而言，每种飞行方法，写一次！
 */