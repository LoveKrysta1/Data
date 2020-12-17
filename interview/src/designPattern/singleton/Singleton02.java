package designPattern.singleton;

public class Singleton02 {
    public static void main(String[] args) {

    }
}

//静态代码块
class Single {
    private Single() {
    }

    private static Single instance;

    static {
        instance = new Single();
    }

    public static Single getInstance() {
        return instance;
    }
}
