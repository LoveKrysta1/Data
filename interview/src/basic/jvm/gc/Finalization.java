package basic.jvm.gc;

public class Finalization {
    public static Finalization finalization;

    @Override
    protected void finalize() {
        System.out.println("Finalized");
        finalization = this;//死之前重生
    }

    public static void main(String[] args) {
        Finalization f = new Finalization();
        System.out.println("First print:" + f);
        f = null;
        System.gc();
        //休息让GC回收，让上面的垃圾回收线程执行完成
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Second print: " + f);
        System.out.println(Finalization.finalization);
    }
}
