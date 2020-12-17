package designPattern.Factory.c;


//针对工厂方法的问题，当有多个产品等级时（食物、饮料、甜品...） 工厂类就会很多！！
//修改代码如下，使用抽象工厂模式


import javax.swing.plaf.SpinnerUI;

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

interface Drink {
    public void drink();
}

class Cola implements Drink {

    @Override
    public void drink() {
        System.out.println("well coal");
    }
}


class IcePeak implements Drink {

    @Override
    public void drink() {
        System.out.println("从小就喝 ，冰峰");
    }
}


interface Factory {
    public Food getFood();

    public Drink getDrink();
}

class KFCFactory implements Factory {

    @Override
    public Food getFood() {
        return new Hamburger();
    }

    @Override
    public Drink getDrink() {
        return new Cola();
    }
}

class SanQingFactory implements Factory {

    @Override
    public Food getFood() {
        return new RiceNoodle();
    }

    @Override
    public Drink getDrink() {
        return new IcePeak();
    }
}

class Bussiness {
    public static void taste(Factory ff) {
        Food f = ff.getFood();
        Drink c = ff.getDrink();
        System.out.println("评委1，品尝");
        f.eat();
        c.drink();

        Food f1 = ff.getFood();
        Drink c1 = ff.getDrink();
        System.out.println("评委2，品尝");
        f1.eat();
        c1.drink();

        Food f3 = ff.getFood();
        Drink c3 = ff.getDrink();
        System.out.println("评委3，品尝");
        f3.eat();
        c3.drink();
    }
}


//===============================================
//拓展
class LP implements Food {

    @Override
    public void eat() {
        System.out.println("宝鸡热干面");
    }
}

class Fenta implements Drink {

    @Override
    public void drink() {
        System.out.println("喝芬达");

    }
}

class sbFactory implements Factory {

    @Override
    public Food getFood() {
        return new LP();
    }

    @Override
    public Drink getDrink() {
        return new Fenta();
    }
}


public class AppTest {
    public static void main(String[] args) {
//        Food f = new Hamburger();
//        f.eat();
//        FoodFactory ff = new HamburgerFactory();
//
//        Food food = ff.getFood();
//
//        food.eat();
        Factory ff = new sbFactory();

        Bussiness.taste(ff);

    }
}

/**
 * 抽象工厂的
 * 优点：1、仍然有简单工厂和工厂方法的优点
 * 2、更重要的事，抽象工厂把工厂类的数量减少了！无论有多少个产品等级，工厂就一套。
 * <p>
 * 杠点：
 * 1、为什么三秦工厂中，就必须是米线搭配冰峰呢？为什么就不能是米线搭配可乐呢？
 * <p>
 * 解释：
 * 抽象工厂中，可以生产多个产品，这多个产品之间，必须有内在联系。
 * 同一个工厂中的产品都属于同一个产品簇！不能把不同产品簇中的产品混合到一个抽象工厂的实现类中
 * <p>
 * 缺点：
 * 1、当产品等级发生变化时（增加产品登记、删除产品等级），都要引起所有以前工厂代码的修改
 * 这就违反了“开闭原则”！
 * <p>
 * 结论：
 * 当产品登记比较固定时，可以考虑使用抽象工厂
 * 如果产品等级经常变化，则不建议使用抽象工厂
 */

