package designPattern.principle.singleResponsibility.negative;

//统计一个文本文件中，有多少个字符

import com.sun.tools.internal.ws.wsdl.document.Output;

import java.io.*;
import java.util.Arrays;

/**
 * java 只认unicode
 */
public class AppTest {
    public static void main(String[] args) throws Exception {

        //统计一个文本文件中，有多少个字符。
        //字符流会查码表，字节流不会查码表
        //Reader默认查询的码表是与操作系统一致的码表，我们的操作系统是中文的
        //所以Reader就会使用GBK码表
        //而GBK码表一个汉字占2个字节，且汉字的两个字节，都是以1开头
        //gbk解码有规则，如果是1开头就绑在一起解释
        Reader in = new FileReader("/Users/tanweipeng/Desktop");

        int n;
        int count = 0;
        while ((n = in.read()) != -1) {
            count++;
        }
        System.out.println(count);
        in.close();


//        int n = in.read();
//        System.out.println(n);//哈  看到高位是1  直接一读就读出两个字节 21704
        //ab  就会读两个字节   97  98

        //java只认识unicode码表
        //读取到记事本数字 -- 》gbk --》北 --》 unicode --》21271
//        System.out.println(n + " " + (char)n);

//        in.close();

//
//        String s = "北";
//        //编码 字符-->码表-->数字
//        byte[] bb = s.getBytes("gbk");
//        System.out.println(Arrays.toString(bb));
//
//        //解码:数字-->码表-->字符
//        byte[] bb2 = s.getBytes("unicode");
//        System.out.println(Arrays.toString(bb2)));


        //===========================>
//        OutputStream out  = new FileOutputStream("/Users/tanweipeng/Desktop");
//        out.write(97);//给个128之前的负数 解码就变成汉字
//        out.write(98);
//        out.close();
//
//        byte b = (byte)-129;
//        System.out.println(b);//=>127,这是一个溢出问题

    }
}

//以上代码，违反了单一职责，缺点是
//1.代码的重用性不高，如果有其他需求，必须，需要统计一个文件中的句子数量，则必须把文件加载到字符串中的代码，再写一遍
//2.可读性低，别人一看这个方法，首先会被具体的算法搞晕，看代码的人根本看不出这个代码要干嘛
