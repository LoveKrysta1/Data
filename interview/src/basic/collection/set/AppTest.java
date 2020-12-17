package basic.collection.set;


import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * hashSet  = 》 重写 hashcode 和 equals 方法 =》才能去重  先判断hashcode重写=》再进入equals比较
 * treeSet =》使用这个要泛型实现comparable接口，或者一个类实现Comparator<customer>=>不用重写hashcode和equals（自动搞了）
 * =》忽略不写比较条件就不管那一项，都不写比较就按插入顺序只要一个
 * < -1 是升序
 */

public class AppTest {
    public static void main(String[] args) {
        customer weapon1 = new customer("weapon", 22);
        customer weapon2 = new customer("jacky", 22);
        customer weapon3 = new customer("weapon", 23);
        customer weapon4 = new customer("weapon", 25);

        HashSet<customer> set = new HashSet<>();
        set.add(weapon1);
        set.add(weapon2);
        set.add(weapon3);

        TreeSet<customer> set2 = new TreeSet<>(new customerComparator());
        set2.add(weapon4);
        set2.add(weapon1);
        set2.add(weapon2);
        set2.add(weapon3);

        Iterator<customer> iterator = set2.iterator();
        while (iterator.hasNext()) {
            customer next = iterator.next();
            System.out.println(next.getName() + " " + next.getAge());
        }
    }
}
