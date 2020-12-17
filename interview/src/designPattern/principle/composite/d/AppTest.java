package designPattern.principle.composite.d;

//需求：制作一个集合：要求该集合能记录曾经加过多少个元素（不是统计某一时刻集合中有多少个元素）

import com.sun.tools.classfile.Opcode;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**
 * 针对c包
 * 修改代码如下：
 */
class Myset extends HashSet {

    private int count = 0;

    public boolean add2(Object o) {
        count++;
        return super.add(o);
    }
    //这次重写addAll不再依赖脆弱的父类


    public boolean addAll2(Collection c) {
        boolean bb = false;
        for (Object obj : c) {
            if (add2(c)) {
                bb = true;
            }
        }
        return bb;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

public class AppTest {
    public static void main(String[] args) {
        Myset set = new Myset();
        set.add2("a");
        set.add2("b");
        set.add2("c");
        Set set1 = new HashSet();
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set1.add(4);
        set1.add(5);
        set.addAll2(set1);
        //addAll 又调add  所以为13
        System.out.println(set.getCount());
    }
}
/*

    还要用户看文档
    用了add2好像还行
    但是JDK如果以后有add2就麻烦了

* */