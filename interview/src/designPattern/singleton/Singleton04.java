package designPattern.singleton;

public class Singleton04 {
    public static void main(String[] args) {
        Singleton1111 instance = Singleton1111.INSTANCE;
        Singleton1111 instance2 = Singleton1111.INSTANCE;
        System.out.println(instance == instance2);

        instance.sayOK();

    }
}

//使用枚举，可以实现单例，推荐
enum Singleton1111 {
    INSTANCE;

    public void sayOK() {
        System.out.println("OK...");
    }
}
