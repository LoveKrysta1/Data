package basic.juc.util;

public class Semaphore {
    public static void main(String[] args) {
        //允许两个线程同时执行
        java.util.concurrent.Semaphore semaphore = new java.util.concurrent.Semaphore(1);

        new Thread(() -> {
            //先要得到许可
            try {
                semaphore.acquire();//阻塞方法，如果acquire不到的话，就停在这里 ->获得
                System.out.println("T1 running");
                Thread.sleep(2000);
                System.out.println("T1 is still running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();//执行完毕后 记得release掉 才能恢复
            }
        }).start();

        new Thread(() -> {
            //先要得到许可
            try {
                semaphore.acquire();//阻塞方法，如果acquire不到的话，就停在这里 ->获得
                System.out.println("T2 running");
                Thread.sleep(2000);
                System.out.println("T2 is still running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();//执行完毕后 记得release掉 才能恢复
            }
        }).start();
    }
}
