package basic.juc.locks;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock {

    static volatile boolean flag;
    private static final ReentrantReadWriteLock reentrantReadWriterLock = new ReentrantReadWriteLock();
    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriterLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriterLock.writeLock();

    public static void main(String[] args) {
//        new Thread(()->read()).start();
//        new Thread(()->read()).start();
//        new Thread(()->read()).start();
//        new Thread(()->write()).start();
//        new Thread(()->write()).start();
        new Thread(() -> writeLockToReadLock()).start();
        new Thread(() -> writeLockToReadLock()).start();
        new Thread(() -> writeLockToReadLock()).start();
    }

    private static void read() {
        String threadName = Thread.currentThread().getName();
        readLock.lock();
        try {
            System.out.println(threadName + "-获取读锁（readLock），读取数据...");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(threadName + "-释放读锁(readLock)");
            readLock.unlock();
        }
    }

    private static void write() {
        String threadName = Thread.currentThread().getName();
        writeLock.lock();
        try {
            System.out.println(threadName + "-获取写锁(writeLock)，写入数据...");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(threadName + "释放写锁(writeLock)");
            writeLock.unlock();
        }
    }

    //Downgrade 
    private static void writeLockToReadLock() {
        String threadName = Thread.currentThread().getName();

        readLock.lock();
        if (!flag) {
            System.out.println("must release read lock before acquiring write lock");
            readLock.unlock();
            writeLock.lock();
            try {
                //Recheck state because another thread might have
                //acquired write lock and changed state before we did
                if (!flag) {
                    System.out.println("change the state");
                    flag = true;
                }
                //Downgrade by acquiring read lock before releasing write lock
                readLock.lock();
            } finally {
                writeLock.unlock();//Unlock write ,still hole read
            }
        }

        try {
            System.out.println("read lock still be hold");
        } finally {
            readLock.unlock();
        }

    }
}


