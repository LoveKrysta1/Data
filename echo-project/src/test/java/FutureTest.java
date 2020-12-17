import org.junit.Test;

import java.util.concurrent.*;

public class FutureTest {

    @Test
    public void testFuture() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> f = executorService.submit(new Callable<String>() {
            public String call() throws Exception {
                System.out.println("获取数据中，等待五秒");
                Thread.sleep(5000L);

                return "weapon handsome";
            }
        });
        System.out.println("主线程就执行，因为我是线程池大爷");

        String s = f.get();
        System.out.println("获取到的数据：" + s);
    }
}
