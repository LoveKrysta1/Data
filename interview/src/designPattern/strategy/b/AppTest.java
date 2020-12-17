package designPattern.strategy.b;

/**
 * 游戏公司的老总们开会，得出一个提高本公司游戏竞争力的方案，要求让游戏中的鸭子能飞起来
 * 把其他竞争者远远甩在身后！
 * <p>
 * 程序员就会像，是时候展现我们面向对象程序员的威力了！我只需要在父类Duck中，添加一个fly方法，
 * 那么所有Duck的子类，也都具备fly方法。
 * <p>
 * 此时，问题看似解决了，但实际上出现了更麻烦的问题，所有Duck的子类通通会飞了，要知道，父类中的
 * 方法，并不是所有子类都能通用的！！比如橡皮鸭子也会飞了，橡皮鸭没有生命的
 */

/**
 * 当涉及“维护”时，为了“复用”目的使用继承结局并不完美
 */

abstract class Duck {
    public void quack() {
        System.out.println("嘎嘎");
    }

    public void swim() {
        System.out.println("游泳...");
    }

    public void fly() {
        System.out.println("我飞...");
    }

    public abstract void display();
}

class MallardDuck extends Duck {
    @Override
    public void display() {
        System.out.println("外观是野鸭");
    }
}

class RedHeadDuck extends Duck {

    @Override
    public void display() {
        System.out.println("红头鸭");
    }
}

class RubberDuck extends Duck {
    @Override
    public void display() {
        System.out.println("外观是橡皮鸭");
    }

    //因为橡皮鸭不会想真实的鸭子一样叫，所以模拟了叫声
    //结果继承了DUCK，橡皮鸭也能飞了，程序员背锅
    @Override
    public void quack() {
        System.out.println("吱吱叫...");
    }

    @Override
    public void fly() {
        System.out.println("橡皮鸭最终没有飞起来");
    }
}

//因为橡皮鸭不会飞，却又继承了Duck中的fly方法，所以我们可以重写fly方法

public class AppTest {
    public static void main(String[] args) {
        Duck d = new RedHeadDuck();
        d.quack();
        d.swim();
        d.fly();
        d.display();
    }
}
/*
    看起来，问题好像解决了，但是并没有，问题是，变化不断出现，一会儿加个
    木头鸭子，一会加个鸭子超人，一会儿加个怪鸭伯爵
    程序猿就要在每次添加新的鸭子角色时，都会判断，新的鸭子会不会叫，会不会飞
    针对于不同额鸭子要有不同的处理方法，这样也很麻烦，只不过是从一个梦跌入另一个噩梦
 */