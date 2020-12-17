package designPattern.principle.demeter.supplment;

import java.util.Date;
import java.util.List;

public class AppTest {

    private List list = null;

    //迪米特法则中返回值s是朋友，自己在方法里面new出来的对象也是朋友，参数也是朋友
    //类中字段是朋友
    public String f1(Integer i) {
        Date d = new Date();
        String s = new String();
        //我想搞什么都可以
        return s;
    }
}
