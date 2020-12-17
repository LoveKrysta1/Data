package designPattern.adapter.b;

import java.util.Arrays;

class Processor {
    public String name() {
        return getClass().getSimpleName();
    }

    Object process(Object input) {
        return input; //虚假处理
    }
}

class Upcase extends Processor {
    @Override
    String process(Object input) { //Covariant return 协变 Object->String
        return ((String) input).toUpperCase();//.的优先级比转型的括号高
    }
}

class Downcase extends Processor {
    @Override
    String process(Object input) { //Covariant return 协变 Object->String
        return ((String) input).toLowerCase();//.的优先级比转型的括号高
    }
}

class Splitter extends Processor {
    @Override
    String process(Object input) { //Covariant return 协变 Object->String
        return Arrays.toString(((String) input).split(" "));//.的优先级比转型的括号高
    }
}


public class AppTest {
    public static void main(String[] args) {
        String str = "How are you !";

        Processor p = new Upcase();
        System.out.println("Using Processor:" + p.name());
        System.out.println(p.process(str));

        Processor p2 = new Downcase();
        System.out.println("Using Processor:" + p2.name());
        System.out.println(p2.process(str));

    }

}
