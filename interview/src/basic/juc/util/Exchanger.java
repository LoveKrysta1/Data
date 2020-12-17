package basic.juc.util;

/**
 * 两个人之间互相交换数据，两两配对!!!
 * 泛型指定参数
 */
public class Exchanger {
    static java.util.concurrent.Exchanger<String> exchanger = new java.util.concurrent.Exchanger<>();

    public static void main(String[] args) {
        new Thread(() -> {
            String s = "T1";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T1").start();

        new Thread(() -> {
            String s = "T2";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);
        }, "T2").start();
    }

}
