package designPattern.iterator.a;

//该类演示如何给数组扩容


import java.util.Arrays;

public class AppTest {
    public static void main(String[] args) {
        int[] a = {11, 22, 33, 44, 55};
//        System.out.println(a.length);
        System.out.println(Arrays.toString(a));

        a = Arrays.copyOf(a, 7);//复制a 到 新长度为7的，a本来是5，多以逼着多两个0, 0
        System.out.println(Arrays.toString(a));

    }

}
