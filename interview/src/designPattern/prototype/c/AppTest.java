package designPattern.prototype.c;


import java.io.*;
import java.util.Date;

/**
 * 使用“原型模式”来解决这个问题
 * 1、必须让目标类实现Cloneable接口，该接口中没有任何抽象方法。这样的接口仅仅是一个“标记接口”，作用是，告诉jvm，任何实现了该
 * Cloneable接口的类的对象，可以被克隆！
 * <p>
 * 2.必须重写java.lang.Object的方法，一定要把该方法的访问修饰符，重写为public！！不然无法调用clone方法！
 */

public class AppTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        //第一周周报
        WeekReport wr = new WeekReport();
        wr.setEmp("张珊珊");
        wr.setSummary("设计七大原则");
        wr.setPlain("讲解完设计模式");
        wr.setSuggestion("无");
        wr.setTime(new Date());

        //第二周周报，问题是，尽管第二周周报的大部分内容与第一周周报的内容一致，但是仍然
        //要重复设置！（等同于在表单中重复添加和上周一样的内容）
//        WeekReport wr2 = new WeekReport();
//        wr.setEmp("张珊珊");
//        wr.setSummary("讲解HTML");
//        wr.setPlain("讲解CSS");
//        wr.setSuggestion("无");
//        wr.setTime(new Date());
        //克隆wr对象，得到一个新对象

        //思考1，clone方法，会不会引起构造器的调用 =》答案是不会的，那么这个克隆方法是如何实现克隆对象的效果呢？
        //clone方法是直接复制内存中的二进制，效率更高，因为底层用的是本地方法。
        //思考2，既然clone方法没有引起构造器的调用，那么克隆出来的对象，地址是否一致？？不一致！！
        //最终是两个不同空间中的对象。

        WeekReport wr2 = (WeekReport) wr.clone();
        wr2.setSummary("abc");
        wr2.setPlain("xyz");
        System.out.println(wr2);

        //问题是：修改了其中一个对象的属性，另一个对象的属性是否会变化？
        //Date类的setTime是用来设置毫秒数的，这个毫秒数是自    1970.1.1 00:00:00以来的毫秒数
        //下面修改了wr2的time1字段，而wr的time字段也一起被改变了！因为，目前这种克隆方式是“浅拷贝”
        //所谓的浅拷贝，就是把原来的对象的2进制，原样复制
        wr2.getTime().setTime(0);
        System.out.println(wr);
        System.out.println(wr2);

        //我们希望克隆出来的副本对象，无论怎么修改它，都不会影响原来的对象！！！

        //目前已经达到深拷贝的目的，也就是修改副本对象的任何属性，都对原有对象没有任何影响的！！
        //问题是，如果对象深度比较深，则深拷贝实现起来很麻烦。

        //此时，利用序列化+反序列化，已经简化了深拷贝的写法。

        //问题是
        //序列化的目标位置是写死的，位置不应该写死！！linux下没有盘符！！=》写到内存里
        //用ByteArrayOutputStream()

        //这才是我们推荐的原型模式
    }
}

class WeekReport implements Cloneable, Serializable {
    private int id;
    private String emp;
    private String summary;
    private String plain;
    private String suggestion;
    private Date time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmp() {
        return emp;
    }

    public void setEmp(String emp) {
        this.emp = emp;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPlain() {
        return plain;
    }

    public void setPlain(String plain) {
        this.plain = plain;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
//            OutputStream out = new FileOutputStream("/Users/tanweipeng/Desktop/a.txt");
//            ObjectOutputStream oos = new ObjectOutputStream(out);
//            oos.writeObject(this); //序列化时，对象的所有属性层级关系会被序列化自动处理！！
//            oos.close();
//
//            InputStream in = new FileInputStream("/Users/tanweipeng/Desktop/a.txt");
//            ObjectInputStream ios = new ObjectInputStream(in);
//            Object clone = ios.readObject();
//            ios.close();

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(this);//序列化
            oos.close();

            byte[] bytes = out.toByteArray();

            InputStream in = new ByteArrayInputStream(bytes);
            ObjectInputStream ios = new ObjectInputStream(in);
            Object clone = ios.readObject();

            System.out.println(this);
            System.out.println(clone);
            System.out.println((this == clone) + "!!!!");

            return clone;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "WeekReport{" +
                "id=" + id +
                ", emp='" + emp + '\'' +
                ", summary='" + summary + '\'' +
                ", plain='" + plain + '\'' +
                ", suggestion='" + suggestion + '\'' +
                ", time=" + time +
                '}';


    }
}
