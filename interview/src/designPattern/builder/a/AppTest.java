package designPattern.builder.a;


/**
 * 需求：定义一个电脑类，并且实例化出电脑类的对象，以及给该对象的属性赋值
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

//=============================================

public class AppTest {
    public static void main(String[] args) {
        Computer c = new Computer();
        c.setCpu("i7 7500u");
        c.setGpu("gt940mx");
        c.setMemory("16g");
        c.setHd("1T");
        System.out.println(c);
    }
}

/**
 * 这样做的缺点是：
 * 1、客户端程序员，在实例话好产品的对象之后，必须为该对象的每一个属性赋值，这样对
 * 于客户端程序员来说，太麻烦了
 * 2、违反了迪米特法则
 * <p>
 * 相当于你去赛格去配电脑，商家把零件全给你，你自己组装电脑！
 * <p>
 * 建造者模式与工厂模式的区别
 * 工厂模式，都是直接实例化出一个类的对象即可
 * 建造者模式，是在实例化出类的对象之后，还要给该对象的属性赋值！
 */


