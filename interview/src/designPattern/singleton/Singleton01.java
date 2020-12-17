package designPattern.singleton;

public class Singleton01 {
    public static void main(String[] args) {
        Singleton singleton01 = Singleton.getInstance();
        Singleton singleton02 = Singleton.getInstance();
        System.out.println(singleton01 == singleton02);
        System.out.println(singleton01.hashCode() == singleton02.hashCode());
    }
}

//饿汉式 静态变量
class Singleton {
    private Singleton() {

    }

    private final static Singleton INSTANCE = new Singleton();

    public static Singleton getInstance() {
        return INSTANCE;
    }
}
