package designPattern.principle.composite.b;

//需求：制作一个集合：要求该集合能记录曾经加过多少个元素（不是统计某一时刻集合中有多少个元素）

import java.util.HashSet;
import java.util.Set;


/**
 * 针对于a包的问题，addAll会回调add方法，我们修改代码如下，把addAll删除掉
 * 不要重写父类HashSet的addAll
 * 反正父类的addAll本身就会去回调add
 */
class Myset extends HashSet {
    private int count = 0;

    @Override
    public boolean add(Object o) {
        count++;
        return super.add(o);
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
 此时这个代码看起来好像很完美，已经满足了需求了

 问题是，目前这个代码，必须依赖于这样一个事实，就是HashSet的addAll方法必须去
 回调add方法，万一将来的jdk版本中，HashSet的addAll实现代码，突然不再回调add方法
 了，则在将来的这个jdk版本中，我们自定义的这个MySet就被撼动

 比如，HashMap，在jdk1.6 1.7 1.8中，底层实现分别换了3次
 */