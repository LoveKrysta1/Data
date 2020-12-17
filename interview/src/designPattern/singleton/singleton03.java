package designPattern.singleton;

public class singleton03 {
    public static void main(String[] args) {

    }
}

//懒汉式 线程不安阿全的
class S {
    private static S instance;

    private S() {
    }

    public static S getInstance() {
        if (instance == null) {
            instance = new S();
        }
        return instance;
    }
}

//线程安全 同步方法
class K {
    private static K instance;

    private K() {
    }

    public static synchronized K getInstance() {
        if (instance == null) {
            instance = new K();
        }
        return instance;
    }
}

//双重检查
class DCL {
    private DCL() {
    }

    private static volatile DCL dcl;

    private static DCL getInstance() {
        if (dcl == null) {
            synchronized (DCL.class) {
                if (dcl == null) {
                    dcl = new DCL();
                }
            }
        }
        return dcl;
    }
}


//静态内部类
class j {
    private j() {
    }

    private static class jk {
        private static final j INSTANCE = new j();
    }

    public static j getInstance() {
        return jk.INSTANCE;
    }
}
