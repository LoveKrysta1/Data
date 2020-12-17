package designPattern.proxy.d;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 针对于C包中的问题，学习一个jdk中的api：动态代理
 */

interface ICalc {
    int add(int a, int b);

    int sub(int a, int b);

    int mul(int a, int b);

    int div(int a, int b);
}

class CalcImpl implements ICalc {

    @Override
    public int add(int a, int b) {
        int r = a + b;
        return r;
    }

    @Override
    public int sub(int a, int b) {
        int r = a - b;
        return r;
    }

    @Override
    public int mul(int a, int b) {
        int r = a * b;
        return r;
    }

    @Override
    public int div(int a, int b) {
        int r = a / b;
        return r;
    }
}


class MyHandler implements InvocationHandler {

    private Object target;

    public MyHandler(Object target) {
        this.target = target;//关联起来了
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName() + "开始，参数是" + Arrays.toString(args));
        Object result = method.invoke(target, args);//这时候调用真正的方法
        System.out.println(method.getName() + "结束，结果是" + result);
        return 1;
    }
}

public class AppTest {
    public static void main(String[] args) {
        CalcImpl c = new CalcImpl();
        //创建代理对象

        //第一个参数：ClassLoader
        //我们都知道，要实例化对象，是需要调用类的构造器的，在程序运行期间第一次调用构造器时
        //就会引起类的加载，加载类的时候，就是jvm拿着ClassLoader去加载累的字节码的，只有字节码
        //被加载到了内存中，才能进一步实例化出类的对象
        //简单来说，就是只要涉及实例化类的对象，就一定要加载类的字节码，而加载字节码就必须使用
        //类加载器！！
        //下面我们使用的是动态代理的api来创建一个类的对象，这是一种不常用的实例化类对象的方式，
        //尽管不常用，但毕竟涉及实例化类的对象
        //那就一定也需要加载类的字节码，也就一定需要类加载器，所以我们手动把类加载器传入
        ClassLoader ca = AppTest.class.getClassLoader();


        //第二个参数，Class[]
        //我们已经知道，下面的代码，是要用来实例化一个对象的，实例化对象，就一定是实例化某一个类的对象，问题是，到底是哪一个类呢？
        //类名在哪里？字节码又在哪里？ 这个类，其实并不在硬盘上，而是在内存中！是由动态代理在内存中“动态”生成的！(见本包的图)
        //要知道，这个内存中直接生成的字节码，会去自动实现下面方法中的第2个参数，所指定的接口！！！！
        //所以利用动态代理生成的对象，就能转成ICalc接口类型，那么这个对象就拥有add sub div方法！

        //第三个参数，InvocationHandler
        //我们已经知道，下面的代理对象proxy所属得嘞，实现了ICalc接口，这个代理对象就拥有add sub mul div方法！
        //我们就可以通过代理对象调用这些方法
        //注意，每次对代理对象任何方法的调用，都不会进入真正的实现方法中，而是统统进入第三个参数的invoke方法中！
        ICalc proxy = (ICalc) Proxy.newProxyInstance(ca, new Class[]{ICalc.class}, new MyHandler(c));

        proxy.add(1, 2);
    }
}
