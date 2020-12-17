package designPattern.principle.composite.a;

//需求：制作一个集合：要求该集合能记录曾经加过多少个元素（不是统计某一时刻集合中有多少个元素）

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

class Myset extends HashSet {
    private int count = 0;

    @Override
    public boolean add(Object o) {
        count++;
        return super.add(o);
    }

    //但凡给集合添加元素的入口，我都需要添加元素
    @Override
    public boolean addAll(Collection c) {
        count += c.size();
        return super.addAll(c);
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
        set.add("a");
        set.add("b");
        set.add("c");
        Set set1 = new HashSet();
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set1.add(4);
        set1.add(5);
        set.addAll(set1);
        //addAll 又调add  所以为13
        System.out.println(set.getCount());
    }
}
// 问题是，在执行addAll之后，count不是3，而是6。为什么呢？因为
//addAll回调了add方法
//所以这样的代码没有解决需求