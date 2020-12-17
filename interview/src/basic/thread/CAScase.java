package basic.thread;

/**
 * 编译
 */
public class CAScase {
    public volatile int value;

    public void add() {
        value++;
    }
}
