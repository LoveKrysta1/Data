package designPattern.template.a;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 测试ArrayList和LinkedList的增加效率，查询效率
 */

public class AppTest {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 100000; i++) {
            list.add(0, 1);
        }

        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }
}

/**
 * 此时，每次要测试的代码发生变化时，都势必要修改原有的代码，如果修改之后，又需要测试之前的代码
 * 那么又要把之前的代码写回来！
 */
