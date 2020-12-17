package basic.thread;

public class ThreadTest {
    private static void attack() {
        System.out.println("Fight");
        System.out.println("Current Thread is : " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Thread t = new Thread() {
            public void run() {
                attack();
            }
        };
        System.out.println("current main thread is : " + Thread.currentThread().getName());
//        t.run();//用当前线程去执行
        t.start(); //start0=>native方法
    }
}