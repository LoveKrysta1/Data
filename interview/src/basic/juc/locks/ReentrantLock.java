package basic.juc.locks;

/**
 * reentrantLock用于替代synchronized可重入的语义
 */

public class ReentrantLock {
    /**
     * synTest,可重入锁
     */
    synchronized void m1() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
            m2();
        }
    }

    synchronized void m2() {
        System.out.println("m2...");
    }

    private java.util.concurrent.locks.ReentrantLock lock = new java.util.concurrent.locks.ReentrantLock();

    void m1s() {
        try {
            lock.lock();
            System.out.println("m1 is running");
            Thread.sleep(2000);
            m2s();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void m2s() {
        try {
            lock.lock();
            System.out.println("m2 is running");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();//自己写的，测试是否可重入
//        lock.m1();
        lock.m1s();

    }
}
