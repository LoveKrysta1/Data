package basic.reflect;

public class LoadDifference {
    public static void main(String[] args) throws ClassNotFoundException {
//        Class.forName("basic.reflect.Robot");
        ClassLoader classLoader = Robot.class.getClassLoader();
        Class.forName("com.mysql.jdbc.Driver");
        /**
         * loadclass 在springIOC中资源加载器，读写bean的读配置文件，和springIOC的lazyLoading有关
         */
    }
}
