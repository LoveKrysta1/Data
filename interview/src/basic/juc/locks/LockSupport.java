package basic.juc.locks;

/**
 * 传参的
 */
public class LockSupport {

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if (i == 5) {
                    java.util.concurrent.locks.LockSupport.park();
                }
            }
        });

        t.start();

        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("8s后解锁");

        java.util.concurrent.locks.LockSupport.unpark(t);

    }
}
