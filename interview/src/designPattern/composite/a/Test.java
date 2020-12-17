package designPattern.composite.a;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 菜单 和 菜单项
 * <p>
 * 菜单
 * |--陕菜
 * |--胡椒汤
 * |--肉丸胡辣汤
 * |--河南胡辣汤
 * |--羊肉泡
 * |--优质
 * |--普通
 * |--双份优质
 * |--三秦套餐
 * |--川菜
 * |--火锅
 * |--辣椒就朝天椒
 * |--伤心凉粉
 * |--粤菜
 * |--鱼丸
 * |--牛丸
 * |--虾丸
 * <p>
 * 但凡需要制作树形结构的地方，就可以使用组合模式
 */

class Menu {
    private String name;
    private String description;

    private List<MenuItem> list = new ArrayList<>();

    public Menu(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void add(MenuItem mi) {
        list.add(mi);
    }

    public void print(String prefix) {
        System.out.println(prefix + "<<" + name + ">>" + description);
        Iterator<MenuItem> it = list.iterator();
        while (it.hasNext()) {
            MenuItem mi = it.next();
            mi.print("\t" + prefix);
        }
    }
}

class MenuItem {
    private String name;
    private String description;

    public MenuItem(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void print(String prefix) {
        System.out.println(prefix + name + ":" + description);
    }
}

//完全可以用装饰模式来加胡辣汤
public class Test {
    public static void main(String[] args) {
        Menu menu = new Menu("陕菜", "老陕爱吃的菜");
        MenuItem mi = new MenuItem("胡辣汤", "各种菜+肉丸，又麻有辣。味道很好");
        MenuItem mi2 = new MenuItem("羊肉泡", "兵贵神速");
        MenuItem mi3 = new MenuItem("蚂蚁上树", "粉条加肉末");

        MenuItem mi2_1 = new MenuItem("优质羊肉泡", "加肉，加价");
        MenuItem mi2_2 = new MenuItem("普通羊肉泡", "不额外加肉");

        menu.add(mi);
        menu.add(mi2);
        menu.add(mi3);
        //can't compile
        //因为菜单项，没有add方法，用于添加其他菜单项
        //所以这样做，不能满足需求
        //mi2.add(mi2_1);
        //mi2.add(mi2_2);
        menu.print("");
    }
}
