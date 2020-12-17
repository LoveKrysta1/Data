package designPattern.decorator.a;

/**
 * 业务场景。星巴克卖咖啡，一开始，只有四种咖啡
 * Decaf Espresso DarkRoast HouseBlend
 * <p>
 * 因为所有咖啡都有共性,所有开发人员，把它们的共性上提高一个父类中，Beverage
 */

abstract class Beverage {
    private String description;

    public Beverage(String description) {
        this.description = description;
    }

    public abstract double coast();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

class Decaf extends Beverage {
    public Decaf() {
        super("无咖啡因咖啡");
    }

    @Override
    public double coast() {
        return 1;
    }
}

class Espresso extends Beverage {
    public Espresso() {
        super("浓缩咖啡");
    }

    @Override
    public double coast() {
        return 2;
    }
}

class DarkRoast extends Beverage {
    public DarkRoast() {
        super("焦炒咖啡");
    }

    @Override
    public double coast() {
        return 1.5;
    }
}

class HouseBlend extends Beverage {
    public HouseBlend() {
        super("混合咖啡");
    }

    @Override
    public double coast() {
        return 3;
    }
}

//==============================================================


public class AppTest {
    public static void main(String[] args) {
        Beverage b = new Decaf();
        Beverage b2 = new Espresso();
        Beverage b3 = new DarkRoast();
        Beverage b4 = new HouseBlend();

        System.out.println(b.getDescription() + ":" + b.coast());
        System.out.println(b2.getDescription() + ":" + b2.coast());
        System.out.println(b3.getDescription() + ":" + b3.coast());
        System.out.println(b4.getDescription() + ":" + b4.coast());
    }
}

/**
 * 目前，这段代码，看起来是没有问题的的
 * <p>
 * 问题是，变化来了
 * 星巴克的老板为了提高自身的竞争力，想起了新的业务，调料，也就是可以给咖啡中放调料
 * 牛奶、豆浆、摩卡、泡沫（只是为了好玩）
 * <p>
 * 如何设计，以应对这种变化呢？
 */
