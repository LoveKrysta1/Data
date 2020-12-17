package basic.thread;

public class SyncBlockAndMethod {
    public void syncsTask() {
        //同步代码库
        synchronized (this) {
            System.out.println("Hello");
        }
    }

    public synchronized void syncTask() {
        System.out.println("Hello Again");
    }
}
