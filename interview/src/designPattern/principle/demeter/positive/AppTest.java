package designPattern.principle.demeter.positive;

class Computer {
    private void saveData() {
        System.out.println("保存数据");
    }

    private void killProcess() {
        System.out.println("关闭程序");
    }

    private void closeScreen() {
        System.out.println("关闭屏幕");
    }

    private void powerOff() {
        System.out.println("断电");
    }

    public void shutDown() {
        saveData();
        ;
        killProcess();
        closeScreen();
        powerOff();
    }
}

class Person {
    private Computer c = new Computer();

    public void shutDownComputer() {
        c.shutDown();
    }
}


public class AppTest {
}
