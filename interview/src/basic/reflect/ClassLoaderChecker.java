package basic.reflect;

public class ClassLoaderChecker {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader m = new MyClassLoader("/Users/tanweipeng/Desktop/", "MyClassLoader");
        Class c = m.loadClass("Wali");
        System.out.println(c.getClassLoader());
        System.out.println(c.getClassLoader().getParent());//App
        System.out.println(c.getClassLoader().getParent().getParent());//Ext
        System.out.println(c.getClassLoader().getParent().getParent().getParent());//null
        c.newInstance();//loadclass 不会执行静态代码块，要new出来
    }

}
