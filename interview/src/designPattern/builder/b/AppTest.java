package designPattern.builder.b;

/**
 * 需求：定义一个电脑类，并且实例化出电脑类的对象，以及给该对象的属性赋值
 * <p>
 * 针对于a包中的问题，修改代码如下，
 * 作者，专门创建一个"ComputerBuilder"类，这个类专门负责封装组装电脑的过程！
 */
class Computer {
    private String cpu;
    private String gpu;
    private String memory;
    private String hd;

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getHd() {
        return hd;
    }

    public void setHd(String hd) {
        this.hd = hd;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", gpu='" + gpu + '\'' +
                ", memory='" + memory + '\'' +
                ", hd='" + hd + '\'' +
                '}';
    }
}

//电脑建造者类，建造者类必须关联电脑产品
class ComputerBuilder {
    private Computer computer = new Computer();

    public Computer build() {
        computer.setCpu("i7 8750u");
        computer.setGpu("rtx2080ti");
        computer.setMemory("32g");
        computer.setHd("500g固态+2T机械");
        return computer;
    }
}


//=============================================

public class AppTest {
    public static void main(String[] args) {
        //创建一个建造者
        ComputerBuilder cb = new ComputerBuilder();
        Computer c = cb.build();
        System.out.println(c);

        //玩游戏
        Computer c1 = cb.build();
        System.out.println(c1);

        //开发
        Computer c2 = cb.build();
        System.out.println(c2);

        //办公娱乐
        Computer c3 = cb.build();
        System.out.println(c3);

    }
}

/**
 * 目前这种写法还是建造者模式，目前的优点，
 * 1、客户端程序员需要一个产品时，直接向建造者要即可，建造者封装了创建电脑的"复杂"过程
 * <p>
 * 目前的缺点：
 * 1、封装得也太狠了！无论客户的需求是什么，都是采用最高的配置，这相当于你去配电脑无论你什么
 * 需求，商家都会给你配置最贵的电脑。
 */


