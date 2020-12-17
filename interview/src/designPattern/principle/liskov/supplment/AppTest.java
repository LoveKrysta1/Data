package designPattern.principle.liskov.supplment;

/**
 * 方法重写，在子类和父类中，出现了返回类型相
 * 同、方法名相同、方法参数相同的方法时，构成方法重写
 * <p>
 * 方法重写的两个限制：
 * 1、子类重写父类的方法时，子类方法的访问修饰符不能比父类的更严格
 * 2、子类重写父类的方法时，子类方法不能抛出比父类更多的异常
 * <p>
 * 为什么要有以上这两个限制？
 * 就是为了保证，在子类对象替换成父类对象后，语法不会报错！
 * =》这就是里氏替换原则，要透明的替换
 * <p>
 * 最终就是为了让代码符合里式替换原则
 */

class Fu {
    public void f1() {

    }
}

class zi extends Fu {
    public void f1() {

    }
}


public class AppTest {
    public static void main(String[] args) {

    }
}
