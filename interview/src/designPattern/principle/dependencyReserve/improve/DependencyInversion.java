package designPattern.principle.dependencyReserve.improve;

public class DependencyInversion {
    public static void main(String[] args) {
        Person person = new Person();
        person.receive(new Email());
        person.receive(new WeiXin());
    }
}

//定义接口
interface IReceiver {
    public String getInfo();
}

//email
class Email implements IReceiver {
    @Override
    public String getInfo() {
        return "电子邮件信息:hello,world";
    }
}

//weixin
class WeiXin implements IReceiver {
    @Override
    public String getInfo() {
        return "WeiXin:hello,world";
    }
}

class Person {
    public void receive(IReceiver i) {
        System.out.println(i.getInfo());
    }
}
