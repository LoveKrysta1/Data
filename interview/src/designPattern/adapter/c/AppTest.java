package designPattern.adapter.c;

import java.util.Arrays;

class Waveform {
    private static long counter;
    private final long id = counter++;

    public String toString() {
        return "Waveform" + id;
    }
}

//但是，你经常碰到的情况是你无法修改你想要使用的类。例如，在电子滤波器的例子中，类库是被发现而非被创建的。在这些情况下
//,可以使用适配器设计模式。适配器中的代码将接受你所拥有的接口，并产生你所需要的接口，如下
class FilterAdapter implements Processor {
    private Filter filter;//关联-》聚合

    public FilterAdapter(Filter filter) {
        this.filter = filter;
    }

    @Override
    public String name() {
        return filter.name();
    }

    @Override
    public Waveform process(Object input) {
        return filter.process((Waveform) input);
    }
}

class Filter {
    public String name() {
        return getClass().getSimpleName();
    }

    public Waveform process(Waveform input) {
        return input;
    }
}

class LowPass extends Filter {
    double cutoff;

    public LowPass(double cutoff) {
        this.cutoff = cutoff;
    }

    public Waveform process(Waveform input) {
        return input;
    } //Dummy processing
}

class HighPass extends Filter {
    double cutoff;

    public HighPass(double cutoff) {
        this.cutoff = cutoff;
    }

    public Waveform process(Waveform input) {
        return input;
    } //Dummy processing
}

class BandPass extends Filter {
    double lowCutoff, highCutoff;

    public BandPass(double lowCutoff, double highCutoff) {
        this.lowCutoff = lowCutoff;
        this.highCutoff = highCutoff;
    }

    public Waveform process(Waveform input) {
        return input;
    } //Dummy processing
}

/**
 * Filter与Processor具有相同的接口元素，processor是接口就解决了
 */

//=============================================================

//变成接口就是牛逼
interface Processor {
    public String name();

    Object process(Object input);
}

//重用就有了坏味道
abstract class StringProcessor implements Processor {
    @Override
    public String name() {
        return getClass().getSimpleName();
    }
}

class Upcase extends StringProcessor {
    @Override
    public String process(Object input) { //Covariant return 协变 Object->String
        return ((String) input).toUpperCase();//.的优先级比转型的括号高
    }
}

class Downcase extends StringProcessor {
    @Override
    public String process(Object input) { //Covariant return 协变 Object->String
        return ((String) input).toLowerCase();//.的优先级比转型的括号高
    }
}

class Splitter extends StringProcessor {
    @Override
    public String process(Object input) { //Covariant return 协变 Object->String
        return Arrays.toString(((String) input).split(" "));//.的优先级比转型的括号高
    }
}


class Apply {
    public static void process(Processor p, Object s) {
        System.out.println("Using Processor " + p.name());
        System.out.println(p.process(s));
    }

    public static String s = "Disagreement with beliefs is by definition incorrect";

    public static void main(String[] args) {
        process(new Upcase(), s);
        process(new Downcase(), s);
        process(new Splitter(), s);

        Waveform wf = new Waveform();
        Apply.process(new FilterAdapter(new LowPass(1)), wf);
    }
}

public class AppTest {

}
