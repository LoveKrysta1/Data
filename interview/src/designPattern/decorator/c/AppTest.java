package designPattern.decorator.c;

/**
 * 业务场景。星巴克卖咖啡，一开始，只有四种咖啡
 * Decaf Espresso DarkRoast HouseBlend
 * <p>
 * 因为所有咖啡都有共性,所有开发人员，把它们的共性上提高一个父类中，Beverage
 * <p>
 * 针对于b包的问题，我们何必为每一种咖啡每一种调料都创建一个类呢？这样做太2了，太笨了！
 * 我们可以直接在父类Beverage中，添加4个boolean属性，分别代表是否加了对应的4中调料
 */

class Beverage {
    private String description;
    private boolean milk, soy, mocha, bubble, gouqi;

    public Beverage(String description) {
        this.description = description;
    }

    public double coast() {
        double total = 0;
        if (milk) {
            total += 0.2;
        }
        if (soy) {
            total += 0.3;
        }
        if (mocha) {
            total += 0.4;
        }
        if (bubble) {
            total += 0.1;
        }
        if (gouqi) {
            total += 0.6;
        }
        return total;
    }

    public String getDescription() {
        String str = description;
        if (milk) {
            str = str + "+牛奶";
        }
        if (soy) {
            str = str + "+豆浆";
        }
        if (mocha) {
            str = str + "+摩卡";
        }
        if (bubble) {
            str = str + "+打泡";
        }
        if (gouqi) {
            str = str + "+枸杞";
        }

        return str;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isMilk() {
        return milk;
    }

    public void setMilk(boolean milk) {
        this.milk = milk;
    }

    public boolean isSoy() {
        return soy;
    }

    public void setSoy(boolean soy) {
        this.soy = soy;
    }

    public boolean isMocha() {
        return mocha;
    }

    public void setMocha(boolean mocha) {
        this.mocha = mocha;
    }

    public boolean isBubble() {
        return bubble;
    }

    public void setBubble(boolean bubble) {
        this.bubble = bubble;
    }

    public boolean isGouqi() {
        return gouqi;
    }

    public void setGouqi(boolean gouqi) {
        this.gouqi = gouqi;
    }
}

class Decaf extends Beverage {
    public Decaf() {
        super("无咖啡因咖啡");
    }

    @Override
    public double coast() {
        return 1 + super.coast();
    }
}

class Espresso extends Beverage {
    public Espresso() {
        super("浓缩咖啡");
    }

    @Override
    public double coast() {
        return 2 + super.coast();
    }
}

class DarkRoast extends Beverage {
    public DarkRoast() {
        super("焦炒咖啡");
    }

    @Override
    public double coast() {
        return 1.5 + super.coast();
    }
}

class HouseBlend extends Beverage {
    public HouseBlend() {
        super("混合咖啡");
    }

    @Override
    public double coast() {
        return 3 + super.coast();
    }
}

//==============================================================

class Tea extends Beverage {

    public Tea() {
        super("茶");
    }

    public double cost() {
        return 2;
    }
}

public class AppTest {
    public static void main(String[] args) {
        Beverage b = new Decaf();
        b.setMilk(true);
        System.out.println(b.getDescription() + ":" + b.coast());
    }
}

/**
 * 优点
 * 1、类没有爆炸，没有出现各种各样的类！
 * 2、星巴克的老板，又加入了一个新的饮料：茶，不会带来什么影响。符合开闭原则
 * <p>
 * 缺点：
 * 1、星巴克的老板，又加入了一种新的调料：枸杞，这势必会违反开闭原则，就要重新改写父类Beverage的cost
 * 方法和getDescription方法，来把这个枸杞加进去
 */
