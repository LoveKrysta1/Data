package designPattern.decorator.d;

/**
 * 业务场景。星巴克卖咖啡，一开始，只有四种咖啡
 * DecafEspressoDarkRoastHouseBlend
 * <p>
 * 因为所有咖啡都有共性,所有开发人员，把它们的共性上提高一个父类中，Beverage
 * <p>
 * <p>
 * 针对c包的问题，我们，要使用“装饰器设计模式”来解决我们！
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

/**
 * 判断两个类之间能不能有继承关系，主要看这两个类之间有没有 “is a”关系。并且还要符合里氏替换原则！
 * <p>
 * 以上只是原则，不是语法强制的！也就是说，在特定情况下，可以违反这个规则，必须在装饰器模式中就是这样。
 * <p>
 * 尽管调料不是饮料，但是为了制作出装饰器模式，我们也只能让调料去继承饮料！
 */

abstract class Condiment extends Beverage { //调料不仅继承饮料还关联饮料
    //让调料类，关联饮料类
    protected Beverage beverage;

    public Condiment(Beverage beverage) {
        super("调料");
        this.beverage = beverage;
    }
}

class Milk extends Condiment {

    //这一步不太懂
    public Milk(Beverage beverage) {
        super(beverage);
    }

    @Override
    public double coast() {
        return beverage.coast() + 0.2;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + "牛奶";
    }
}

class Soy extends Condiment {

    //这一步不太懂
    public Soy(Beverage beverage) {
        super(beverage);
    }

    @Override
    public double coast() {
        return beverage.coast() + 0.3;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + "豆浆";
    }
}

class Mocha extends Condiment {

    //这一步不太懂
    public Mocha(Beverage beverage) {
        super(beverage);
    }

    @Override
    public double coast() {
        return beverage.coast() + 0.3;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + "摩卡";
    }
}

class Bubble extends Condiment {

    //这一步不太懂
    public Bubble(Beverage beverage) {
        super(beverage);
    }

    @Override
    public double coast() {
        return beverage.coast() + 0.1;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + "打泡";
    }
}


//==============================================================

//扩展性好到爆炸  草  无敌的！！！
class Tea extends Beverage {
    public Tea() {
        super("茶");
    }

    @Override
    public double coast() {
        return 3;
    }
}

class Gouji extends Condiment {
    public Gouji(Beverage beverage) {
        super(beverage);
    }

    @Override
    public double coast() {
        return beverage.coast() + 0.4;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + "枸杞";
    }
}

public class AppTest {
    public static void main(String[] args) {
//        Beverage b = new Decaf();
//        Beverage m = new Milk(b);
//        Mocha mc  = new Mocha(m);
        Beverage tea = new Tea();
        Beverage k = new Milk(tea);
        Beverage c = new Gouji(k);
        System.out.println(c.getDescription() + ":" + c.coast());
    }
}

/**
 * 1.加一个新的饮料，不会违反开闭原则
 * 为了保护开闭原则才有装饰器这个东西  无敌的
 * <p>
 * <p>
 * 缺点：
 * 1、类还是优点多，但是我们已经尽力了！！
 */
