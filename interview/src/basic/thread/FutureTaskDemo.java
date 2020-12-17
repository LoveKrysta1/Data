package basic.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<String> futureTask = new FutureTask<String>(new MyCallable());
        Thread thread = new Thread(futureTask);
        thread.start();
        if (!futureTask.isDone()) {
            System.out.println("task has not finished, please wait!");
        }
        System.out.println("task return: " + futureTask.get());
    }
}
