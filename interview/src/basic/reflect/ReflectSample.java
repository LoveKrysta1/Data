package basic.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectSample {

    int jj(int a) {
        return 1;
    }

    int jj(int a, int b) {
        return a;
    }

    boolean jj(int a, int b, int c) {
        System.out.println(1);
        return false;
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Class rc = Class.forName("basic.reflect.Robot");
        Robot r = (Robot) rc.newInstance();
        System.out.println("Class name is " + rc.getName());

        //继承的方法获取不了
        Method getHello = rc.getDeclaredMethod("throwHello", String.class);
        getHello.setAccessible(true);//暴力
        Object str = getHello.invoke(r, "Bob");
        System.out.println("getHello result is " + str);
        Method sayHi = rc.getMethod("sayHi", String.class);
        sayHi.invoke(r, "Welcome");

        Field name = rc.getDeclaredField("name");
        name.setAccessible(true);
        name.set(r, "Alice");
        sayHi.invoke(r, "Welcome");

        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));

    }
}
