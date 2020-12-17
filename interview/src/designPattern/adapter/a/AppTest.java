package designPattern.adapter.a;

/**
 * 生活中的插头
 * 适配器
 * 一个类的接口转换成客户希望的另一个接口。适配器模式让那些接口不兼容的类可以一起工作
 * 通俗一点的解释：
 * 根据已有的接口，生成想要的接口
 */

class Calc {
    public int add(int a, int b) {
        return a + b;
    }

}
//==============================================

/**
 * 变化来了，客户端希望计算3个数的和，而Calc的add方法只能接受2个参数
 */

class CalcAdapter {
    private Calc ccc;

    public CalcAdapter(Calc ccc) {
        this.ccc = ccc;
    }

    public int add(int a, int b, int c) {
        return ccc.add(a, ccc.add(b, c));
    }
}


public class AppTest {
    public static void main(String[] args) {
        Calc c = new Calc();
        CalcAdapter ca = new CalcAdapter(c);

        int r = ca.add(1, 2, 3);
        System.out.println(r);
    }
}
