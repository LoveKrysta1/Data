package designPattern.principle.liskov.negative;

/**
 * 继承的作用
 * 1、提高代码的重用性
 * 2、多肽的前提。
 * <p>
 * 两个类能不能发生继承关系的依据是什么？
 * a.有没有 is a 关系
 * b.在两个类有了is a关系之后，还要考虑子类对象之后，业务逻辑是否变化！如果变化，则不能发生继承关系。
 * <p>
 * <p>
 * 正方形和长方形有“is a”关系，那么我们能不能让正方行类直接去继承长方形类呢？现在不能了！！
 * 为什么呢？因为还要考虑业务场景，看看在特定的业务场景下，正方形替换了长方形以后，业务逻辑是否变化！
 */

class Utils {
    public static void transform(Rectangle r) {
        while (r.getWidth() <= r.getLength()) {
            r.setWidth(r.getWidth() + 1);
            System.out.println(r.getWidth() + ":" + r.getLength());
        }
    }
}

class Rectangle {
    private double length;
    private double width;


    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }
}

class Square extends Rectangle {

    private double sidelength;

    @Override
    public double getLength() {
        return sidelength;
    }

    @Override
    public void setLength(double length) {
        this.sidelength = length;
    }

    @Override
    public double getWidth() {
        return sidelength;
    }

    @Override
    public void setWidth(double width) {
        this.sidelength = width;
    }
}

public class AppTest {
    public static void main(String[] args) {
        Rectangle r = new Rectangle();
        r.setWidth(12);
        r.setLength(20);

        Utils.transform(r);//业务场景，无法替换了，不符合里式替换原则
    }
}
