package designPattern.Factory.a;

//针对于a包的问题，服务器端代码一旦修改，客户端代码也要跟着修改！！
//修改代码如下，使用简单工厂设计模式。

//抽象产品
interface Food {
    void eat();
}

//具体产品
class Hamburger implements Food {

    @Override
    public void eat() {
        System.out.println("吃汉堡包");
    }
}

class RiceNoodle implements Food {

    @Override
    public void eat() {
        System.out.println("过桥米线");
    }
}

class Lp implements Food {

    @Override
    public void eat() {
        System.out.println("凉皮");
    }
}

class FoodFactory {
    public static Food getFood(int n) {
        Food food = null;
        switch (n) {
            case 1:
                food = new Hamburger();
                break;
            case 2:
                food = new RiceNoodle();
                break;
        }
        return food;
    }
}


//===============================================


public class AppTest {
    public static void main(String[] args) {
//        Food f = new Hamburger();
//        f.eat();
        Food food = FoodFactory.getFood(2);

        food.eat();
    }
}


/**
 * 这种设计相当脆弱！！  为什么呢？  因为，只要作者修改了具体产品的类名，name客户端代码，也要随之一起改变
 * 这样服务器端代码，和客户端代码就是耦合的！
 * 我们希望的效果是，无论服务器端代码如何修改，客户端代码都应该不知道，不用修改客户端代码
 * <p>
 * 简单工厂
 * 优点
 * 1、把具体产品的类型，从客户端代码中，解耦出来
 * 2、服务器端，如果修改了具体产品的类名，客户端也知道！
 * 这便符合了“面向接口编程”的思想
 * <p>
 * 缺点
 * 1、客户端不得不死记硬背那些常量与具体产品的映射关系，比如1对应汉堡包，2对应米线
 * 2、如果具体产品特别多，则简单工厂就会变得十分臃肿，比如有100个具体产品，则需要在简单工厂的switch中写出100个case
 * 3、最重要的事，变化来了，客户端需要扩展具体产品的时候，势必要修改简单工厂中的代码，这就违反了开闭原则
 * 没有源代码，作者代码不能改，简单工厂解决不了，扩展性差，只是对已有的产品解耦
 */

/**
 * 简单工厂
 * 优点
 * 1、把具体产品的类型，从客户端代码中，解耦出来
 * 2、服务器端，如果修改了具体产品的类名，客户端也知道！
 * 这便符合了“面向接口编程”的思想
 *
 * 缺点
 * 1、客户端不得不死记硬背那些常量与具体产品的映射关系，比如1对应汉堡包，2对应米线
 * 2、如果具体产品特别多，则简单工厂就会变得十分臃肿，比如有100个具体产品，则需要在简单工厂的switch中写出100个case
 * 3、最重要的事，变化来了，客户端需要扩展具体产品的时候，势必要修改简单工厂中的代码，这就违反了开闭原则
 * 没有源代码，作者代码不能改，简单工厂解决不了，扩展性差，只是对已有的产品解耦
 */
