package basic.reflect;

public class Robot {
    private String name;

    static {
        System.out.println("initializing");
    }

    public void sayHi(String helloSentence) {
        System.out.println(helloSentence + " " + name);
    }

    private String throwHello(String tag) {
        return "Hello " + tag;
    }


}