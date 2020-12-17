package designPattern.principle.dependencyReserve.positive;

/**
 * 什么是上层
 * 上层不能依赖于下层，它们都应该依赖于抽象
 * person调用dog的方法 ，所以person是上层
 */

interface Animal {
    void eat();

}

class Person {
    //人依赖于狗，才能喂
    public void feed(Animal animal) {
        System.out.println("开始喂养");
        animal.eat();
    }
}

class Dog implements Animal {
    public void eat() {
        System.out.println("够啃骨头");
    }
}


// ======================================

//变化来了，客户端不仅仅需要喂狗，还需要喂猫

//客户端自己顶一个猫类

class Cat implements Animal {
    public void eat() {
        System.out.println("猫吃鱼");
    }
}

public class AppTest {
    public static void main(String[] args) {
        Person p = new Person();
        Dog d = new Dog();

        p.feed(d);
    }
}
//此时，这种代码违反了依赖倒置，因为，每当下层变动时，上层都要跟着一起变动

//我们希望的事，当下层新增，就很快