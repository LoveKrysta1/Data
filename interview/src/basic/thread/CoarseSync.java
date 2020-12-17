package basic.thread;

/**
 * 锁粗化，很多锁，一连窜操作，jvm优化
 */
public class CoarseSync {
    public static String copyString100Times(String target) {
        int i = 0;
        StringBuffer sb = new StringBuffer();
        while (i < 100) {
            sb.append(target);
        }
        return sb.toString();
    }
}
