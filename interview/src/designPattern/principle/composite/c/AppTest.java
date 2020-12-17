package designPattern.principle.composite.c;

//需求：制作一个集合：要求该集合能记录曾经加过多少个元素（不是统计某一时刻集合中有多少个元素）

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**
 * 针对b包的问题，MySet必须依赖于这样一个事实，addAll必须回调add，但是jdk未来的版本，不会做这个保证！
 * 修改代码如下，我们自己亲自重写addAll，这次重写addAll，不再让count累加c.size()了，
 * 而是保证addAll一定会调用add
 */
class Myset extends HashSet {
    private int count = 0;

    @Override
    public boolean add(Object o) {
        count++;
        return super.add(o);
    }
    //这次重写addAll不再依赖脆弱的父类


    @Override
    public boolean addAll(Collection c) {
        boolean bb = false;
        for (Object obj : c) {
            if (add(c)) {
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
/*
    此时，这个代码看起来好像很完美，已经满足了需求了

    问题是，
    1、如果在新的jdk版本中，HashSet突然多了一个元素加入集合的入口方法，addSome，这个
    addSome使我们始料未及的，我们的MySet根本没有重写新版本中出现的addSome方法，这样在新版本，我们的MySet也
    继承了addSome方法，当使用addSome方法添加元素时，根本不会去统计元素的数量

    2、我们重写了addAll方法，和add方法，要知道，在整个HashSet的所有方法中，难免有一些其他方法，
    会依赖于addAll方法和add方法的。我们没头没脑地重写了别人类中的某些方法，就会导致其他依赖于
    这些方法的方法，出现问题！！！
 */