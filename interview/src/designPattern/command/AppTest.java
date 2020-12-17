package designPattern.command;

//接收者：真正执行命令的对象
class Receiver {
    public void action() {
        System.out.println("命令执行了");
    }
}

//抽象命令类：抽象的命令，可以根据不同的类型的命令写出不同的实现
interface Command {
    //调用命令
    void execute();
}

//具体命令类
class ConcreteCommand implements Command {
    private Receiver receiver;

    public ConcreteCommand(Receiver receiver) {
        super();
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.action();
    }
}

//请求者，调用者
class Invoker {
    private Command command;//持有命令对象引用

    public Invoker(Command command) {
        super();
        this.command = command;
    }

    public void call() {
        //请求者调用命令对象执行命令的那个execute方法
        command.execute();
    }

}

public class AppTest {

    public static void main(String[] args) {
        Command command = new ConcreteCommand(new Receiver());
        Invoker invoker = new Invoker(command);
        invoker.call();
    }
}
