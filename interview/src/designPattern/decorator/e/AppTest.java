package designPattern.decorator.e;

import java.io.*;

/**
 * 至此，我们已经学习完了“装配器模式”
 * <p>
 * 其实，我们以前学习的流，就是一种装配式模式的体现
 */
class MyBufferedReader extends Reader {
    private Reader in;

    public MyBufferedReader(Reader in) {
        this.in = in;
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        //不实现，空方法体
        return 0;
    }

    @Override
    public void close() throws IOException {
        in.close();
    }

    public String readLine() throws IOException {
        StringBuilder sb = new StringBuilder();
        int n;
        while (true) {
            n = in.read();
            if (n == '\r') {
                continue;
            }
            if (n == '\n' || n == -1) {
                break;
            }
            sb.append((char) n);
        }
        if (sb.toString().length() == 0) {
            if (n == '\n')
                return "";
            else {
                return null;
            }
        } else {
            return sb.toString();
        }
    }
}

class MyLineNumberReader extends MyBufferedReader {

    private int lineNumber;

    public MyLineNumberReader(Reader in) {
        super(in);
    }

    @Override
    public String readLine() throws IOException {
        String line = super.readLine();
        if (line != null) {
            lineNumber++;
        }
        return line;
    }

    public int getLineNumber() {
        return lineNumber;
    }
}


public class AppTest {
    public static void main(String[] args) throws IOException {
        Reader in = new FileReader("/Users/tanweipeng/Desktop/properties");
//        MyBufferedReader mbr = new MyBufferedReader(in);
        MyLineNumberReader mlnr = new MyLineNumberReader(in);

        String line;
        while ((line = mlnr.readLine()) != null) {
            System.out.println(mlnr.getLineNumber() + "、" + line);
        }

        mlnr.close();


//        InputStream in = new FileInputStream("/Users/tanweipeng/Desktop/properties");
//        BufferedInputStream ris = new BufferedInputStream(in);//烟缸就是缓冲区，读一堆再搞，减少来回次数，提高读写效率
//        //字节变字符  就是差了码表
//        InputStreamReader isr = new InputStreamReader(ris,"gbk");//默认gbk

//        Reader in = new FileReader("/Users/tanweipeng/Desktop/properties");
//        BufferedReader br = new BufferedReader(in);//in 是为了读字符，只有这样才能当行
//
//        String line =null;
//
//        while((line=br.readLine())!=null){
//            System.out.println(line);
//        }
//        br.close();
    }
}
