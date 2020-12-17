package designPattern.observe.a;

//不使用任何设计模式，先凭感觉写一下代码

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
class User {
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

    public void updateNewsPaper(String newsPaper) {
        System.out.println(name + "收到报纸，头条" + newsPaper);
    }
}

class Sender extends Office {
    private User user1 = new User("张珊珊");
    private User user2 = new User("李思思");
    private User user3 = new User("王微微");

    @Override
    public void dataChange() {
        user1.updateNewsPaper(newsPaper);
        user2.updateNewsPaper(newsPaper);
        user3.updateNewsPaper(newsPaper);
    }
}

public class AppTest {
    public static void main(String[] args) {
        Office office = new Sender();

        office.setNewsPaper("杨幂离婚");
    }
}

//订阅报纸的用户，是写死的，无法添加第4个，也无法删除已有的订阅者
//如果非要额外添加第四个订阅者，或者删除已有的订阅者，势必要违反""
