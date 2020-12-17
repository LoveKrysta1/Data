package designPattern.principle.ocp.negative;

public class AppTest {
    public static void main(String[] args) {
        Car car = new Car();
        car.setBrand("奔驰");
        car.setColor("黑色");
        car.setLouyou(true);
        car.setPrice(66666);

        System.out.println(car);
    }

}

//变化来了，现在所有汽车，需要打8折！！
//违反关闭原则的做法就是，直接打开Car的源代码。在getPrice上修改
