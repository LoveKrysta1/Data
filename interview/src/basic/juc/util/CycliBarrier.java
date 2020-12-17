package basic.juc.util;

/**
 * 栅栏Barrier
 */

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CycliBarrier {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(20, new Runnable() {
            @Override
            public void run() {
                System.out.println("满人发车");
            }
        });

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    barrier.await();//不满就等着
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
