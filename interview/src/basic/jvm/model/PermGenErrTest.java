package basic.jvm.model;

import java.util.Random;

/**
 * JDK1.6
 */
public class PermGenErrTest {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            getRandomString(100000).intern();
            System.out.println("Mission Complete");
        }
    }


    //返回指定长度的随机字符串长两次中
    //-XX:MaxPermSize=6M -XX:permSize=6M
    private static String getRandomString(int length) {
        //字符串源
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
