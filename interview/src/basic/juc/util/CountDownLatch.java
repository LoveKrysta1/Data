package basic.juc.util;

/**
 * 倒数门栓
 */

public class CountDownLatch {
    public static void main(String[] args) {
        usingJoin();
        usingCountDownLatch();
    }

    private static void usingCountDownLatch() {
        Thread[] threads = new Thread[100];
        java.util.concurrent.CountDownLatch latch = new java.util.concurrent.CountDownLatch(threads.length);

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                int result = 0;
                for (int j = 0; j < 10000; j++) {
                    result += j;
                    latch.countDown();//每个线程结束就减一
                }
            });
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        try {
            latch.await();//变成 0  就结束了 ，等线程结束，阻塞
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end latch");
    }

    private static void usingJoin() {
        Thread[] threads = new Thread[100];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                int result = 0;
                for (int j = 0; j < 10000; j++) {
                    result += j;
                }
            });
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
//                System.out.println(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("end join");
    }
}
