package designPattern.proxy.b;

/**
 * 有以下功能，能做加减乘除的类
 */

interface ICalc {
    int add(int a, int b);

    int sub(int a, int b);

    int mul(int a, int b);

    int div(int a, int b);
}

class CalcImpl implements ICalc {

    @Override
    public int add(int a, int b) {
        System.out.println("add 方法开始，参数是:" + a + "," + b);
        int r = a + b;
        System.out.println("add 方法结束，结果是：" + r);
        return r;
    }

    @Override
    public int sub(int a, int b) {
        System.out.println("sub 方法开始，参数是:" + a + "," + b);
        int r = a - b;
        System.out.println("sub 方法结束，结果是：" + r);
        return r;
    }

    @Override
    public int mul(int a, int b) {
        System.out.println("mul 方法开始，参数是:" + a + "," + b);
        int r = a * b;
        System.out.println("mul 方法结束，结果是：" + r);
        return r;
    }

    @Override
    public int div(int a, int b) {
        System.out.println("div 方法开始，参数是:" + a + "," + b);
        int r = a / b;
        System.out.println("div 方法结束，结果是：" + r);
        return r;
    }
}

public class AppTest {
    public static void main(String[] args) {
        ICalc c = new CalcImpl();
        c.add(1, 2);
        c.sub(1, 2);
        c.mul(1, 2);
        c.div(1, 2);
    }
}
/*
目前，需求已经解决，但是，有问题
1、代码在重复，如果需求再次变化，使用英文输出日志，纪要改好多地方
2、代码急剧膨胀，核心业务代码，与非核心业务代码，耦合在一起
3、需求要求加入求余，开方，立方...

所以这种做法太二了
 */