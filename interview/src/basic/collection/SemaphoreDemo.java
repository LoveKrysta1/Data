package basic.collection;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    public static void main(String[] args) {
        //线程池
        ExecutorService exec = Executors.newCachedThreadPool();
        //只能5个线程同时访问
        final Semaphore semp = new Semaphore(5);
        for (int i = 0; i < 20; i++) {
            final int NO = i;
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    //获取许可
                    try {
                        semp.acquire();
                        System.out.println("Accessing: " + NO);
                        Thread.sleep((long) (Math.random() * 10000));
                        semp.release();
                    } catch (InterruptedException e) {

                    }

                }
            };
            exec.execute(run);
        }
        //退出线程池
        exec.shutdown();
    }
}
