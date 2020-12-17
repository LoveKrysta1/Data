package basic.juc.locks;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 需求：
 * 10个消费者  2个生产者 满10个消费， 没有就生产 用condition
 */

class test {
    private final int MAX = 10;
    private LinkedList list = new LinkedList();
    ReentrantLock lock = new ReentrantLock();
    Condition producer = lock.newCondition();
    Condition consumer = lock.newCondition();

    void put(Object o) {
        try {
            lock.lock();
            while (list.size() == MAX) {
                try {
                    producer.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(o);
            consumer.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    Object get() {
        try {
            lock.lock();
            producer.signalAll();
            while (list.size() == 0) {
                try {
                    consumer.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return list.remove();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return -1;
    }


}

public class condition {

    public static void main(String[] args) {
        test test = new test();
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    test.put(Thread.currentThread().getName());
                }
            }).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    Object o = test.get();
                    System.out.println(Thread.currentThread().getName() + " " + o);
                }
            }).start();
        }
    }
}
