package designPattern.principle.singleResponsibility.postive;

//统计一个文本文件中，有多少个字符

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

/**
 * java只认unicode
 */


public class AppTest2 {


    //这个方法，他只干了一件事，就是加载文件
    public static String loadFile(String path) throws Exception {
        Reader in = new FileReader(path);
        BufferedReader br = new BufferedReader(in);

        String line = null;

        StringBuilder sb = new StringBuilder("");

        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append("");
        }
        br.close();

        return sb.toString();
    }

    public static String[] getWords(String str) {
        String[] strs = str.split("[^a-zA-Z]+");
        return strs;
    }


    public static void main(String[] args) throws Exception {
        String str = loadFile("/Users/tanweipeng/Desktop/1.txt");

        String[] words = getWords(str);
    }
}

//现在的代码就符合单一职责了，优点如下，
//1.代码重用性提高了
//2.代码可读性提高了，此时的代码，就像一个大纲一样