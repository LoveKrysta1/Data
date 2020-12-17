package designPattern.principle.demeter.negative;

class Computer {
    public void saveData() {
        System.out.println("保存数据");
    }

    public void killProcess() {
        System.out.println("关闭程序");
    }

    public void closeScreen() {
        System.out.println("关闭屏幕");
    }

    public void powerOff() {
        System.out.println("断电");
    }
}

class Person {
    private Computer c = new Computer();

    //此时，这个Person对于computer的细节就知道太多了。
    //对于Person而言，只需要早知道，关机按钮在哪就行，不需要知道如何保留数据
    //如何关闭进程，如何断电等等这些细节
    //这样的话，代码的复杂度就提升了！万一用户使用不当，就有可能造成更大的损失。
    public void shutDownComputer() {
        c.saveData();
        ;
        c.killProcess();
        c.closeScreen();
        c.powerOff();
    }
}


public class AppTest {
}
