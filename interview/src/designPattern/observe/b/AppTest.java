package designPattern.observe.b;

//针对b包的问题，重构代码如下

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 观察者模式，一个对象只要他的状态发生变化，赶紧给别人说一下
 */

//发布者接口(主题接口)
interface Subject {
    //这是注册观察者
    public void registerObserver(Observer observer);

    //删除观察者
    public void removeObserver(Observer observer);

    //给所有观察者发送消息
    public void update();
}

//观察者接口
interface Observer {
    //接受发布者所发来的消息
    public void update(String str);

    //订阅某个报纸
    public void add(Subject subject);

    //取消订阅
    public void cancel(Subject subject);

}

//报社
class Office {
    protected String newsPaper;

    //新报纸出炉
    public void setNewsPaper(String newsPaper) {
        this.newsPaper = newsPaper;
        //发送报纸
        dataChange();
    }

    // 数据变化
    public void dataChange() {
    }


}

//用户
class User implements Observer {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void update(String str) {
        System.out.println(name + "收到报纸，头条" + str);
    }

    @Override
    public void add(Subject subject) {
        subject.registerObserver(this);
    }

    @Override
    public void cancel(Subject subject) {
        subject.removeObserver(this);
    }
}

class Sender extends Office implements Subject {
    private List<Observer> list = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        list.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        list.remove(observer);
    }

    @Override
    public void update() {
        for (Observer observer : list) {
            observer.update(newsPaper);
        }
    }

    @Override
    public void dataChange() {
        update();
    }
}

class PublicData implements Subject {
    private List<Observer> list = new ArrayList<>();

    private String data;

    public List<Observer> getList() {
        return list;
    }

    public void setList(List<Observer> list) {
        this.list = list;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
        update();
    }

    @Override
    public void registerObserver(Observer observer) {
        list.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        list.remove(observer);
    }

    @Override
    public void update() {
        for (Observer observer : list) {
            observer.update(data);
        }
    }


}

//==========================================>

class Wechat implements Observer {
    private String id;

    public Wechat(String id) {
        this.id = id;
    }

    @Override
    public void update(String str) {
        System.out.println("通过网络发送给微信的这个朋友" + "," + str);
    }

    @Override
    public void add(Subject subject) {
        subject.registerObserver(this);

    }

    @Override
    public void cancel(Subject subject) {
        subject.removeObserver(this);
    }
}

public class AppTest {
    public static void main(String[] args) {
        Sender sender = new Sender();
        Sender joy = new Sender();

        PublicData data = new PublicData();


        User user = new User("123");
        User user1 = new User("12w3");
        User user2 = new User("12w3");
        User user3 = new User("12w3");
        Wechat wc = new Wechat("ssssss");


//        user.add(sender);
//        user1.add(sender);
//
//        sender.setNewsPaper("fuck");

        user.add(data);
        wc.add(sender);
        sender.setNewsPaper("asdasd");
        data.setData("kskdjkas");
    }
}

/**
 * 此时，多加一个订阅者，或者再删除已有的订阅者，都不用违反开闭原则
 */
