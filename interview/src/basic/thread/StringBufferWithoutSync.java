package basic.thread;

/**
 * 锁消除，jvm优化，尽管代码理由synchronized也会消除掉
 */


public class StringBufferWithoutSync {
    public void add(String str1, String str2) {
        //StringBuffer是线程安全，由于sb只会在append方法中使用，不可能被其他线程引用
        //因此sb属于不可能共享的资源，JVM会自动消除内部的锁
        StringBuffer sb = new StringBuffer();
        sb.append(str1).append(str2);
    }

    public static void main(String[] args) {
        StringBufferWithoutSync withoutSync = new StringBufferWithoutSync();
        for (int i = 0; i < 1000; i++) {
            withoutSync.add("aaa", "bbb");
        }
    }
}
