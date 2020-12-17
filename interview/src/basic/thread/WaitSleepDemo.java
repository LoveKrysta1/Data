package basic.thread;

public class WaitSleepDemo {
    public static void main(String[] args) {
        final Object lock = new Object();

        new Thread(() -> {
            System.out.println("thread is waiting to get lock");
            synchronized (lock) {
                try {
                    System.out.println("thread A get lock");
                    Thread.sleep(20);
                    System.out.println("thread A do wait method");
                    lock.wait();
                    System.out.println("thread A is done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            System.out.println("thread is waiting to get lock");
            synchronized (lock) {
                try {
                    System.out.println("thread B get lock");
                    System.out.println("thread B is sleeping");
                    Thread.sleep(10);
                    System.out.println("thread B do wait method");
                    System.out.println("thread B is done");
                    lock.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
