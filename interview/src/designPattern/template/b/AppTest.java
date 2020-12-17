package designPattern.template.b;

import java.util.LinkedList;
import java.util.List;

/**
 * 使用模板方法模式，解决a包中的问题
 */

abstract class Template {
    public void template() {
        long start = System.currentTimeMillis();
        code();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public abstract void code();
}

//====================================
class A extends Template {

    @Override
    public void code() {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 1000000; i++) {
            list.add(0, 1);
        }
    }
}


public class AppTest {
    public static void main(String[] args) {

        Template t = new A();
        t.template();

    }
}

/**
 * 此时，每次要测试的代码发生变化时，都势必要修改原有的代码，如果修改之后，又需要测试之前的代码
 * 那么又要把之前的代码写回来！
 */
