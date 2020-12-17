package designPattern.builder.c;


/**
 * 需求：定义一个电脑类，并且实例化出电脑类的对象，以及给该对象的属性赋值
 * <p>
 * 针对于b包中的问题，修改代码如下，
 * 针对于不同需求，我们要创建不同的建造者，来分别生产不同配置产品
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
class AdvanceComputerBuilder {
    private Computer computer = new Computer();

    public Computer build() {
        computer.setCpu("i7 8750u");
        computer.setGpu("rtx2080ti");
        computer.setMemory("32g");
        computer.setHd("500g固态+2T机械");
        return computer;
    }
}

class MidComputerBuilder {
    private Computer computer = new Computer();

    public Computer build() {
        computer.setCpu("i7 7700hq");
        computer.setGpu("gtx1060");
        computer.setMemory("16g");
        computer.setHd("256g固态+1T机械");
        return computer;
    }
}

class LowComputerBuilder {
    private Computer computer = new Computer();

    public Computer build() {
        computer.setCpu("i7 7500u");
        computer.setGpu("gtx940");
        computer.setMemory("8g");
        computer.setHd("1T机械");
        return computer;
    }
}


//=============================================

public class AppTest {
    public static void main(String[] args) {
        //创建一个建造者
        AdvanceComputerBuilder acb = new AdvanceComputerBuilder();
        MidComputerBuilder mcb = new MidComputerBuilder();
        LowComputerBuilder lcb = new LowComputerBuilder();


        //玩游戏
        Computer c1 = acb.build();
        System.out.println(c1);

        //开发
        Computer c2 = mcb.build();
        System.out.println(c2);

        //办公娱乐
        Computer c3 = lcb.build();
        System.out.println(c3);

    }
}

/**
 * 这仍然不是建造者模式
 * <p>
 * 优点：
 * 1、可以根据客户端的不同需求，使用不同的建造者来生产产品
 * <p>
 * 缺点：
 * 1、我们发现多个不同的建造者的代码，在重复！既然代码中出现了重复的代码
 * 这就有了“坏味道”
 * 2、建造的过程不稳定，如果在某个建造者在某个建造者创建产品过程中，漏掉了某一步，
 * 编译器也不会有报错
 * （相当于，KFC某一家分店，制作汉堡包的流程突然少了某一个步骤，出来的汉堡包味道就变了！
 * 因为没有标准）
 */


