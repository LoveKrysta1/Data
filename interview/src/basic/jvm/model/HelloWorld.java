package basic.jvm.model;

public class HelloWorld {
    private String name;

    public void sayHello() {
        System.out.println("Hello" + name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        int a = 1;
        HelloWorld hw = new HelloWorld();
        hw.setName("test");
        hw.sayHello();
    }
}
