package designPattern.composite.b;

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

/**
 * 为了解决a包中的问题：
 * 1、菜单只能添加菜单项，不能再添加其他菜单
 * 2、菜单项不能再添加其他菜单项
 * 不能出现嵌套的菜单形式
 *
 * 重构代码如下，要达到的目的。
 * 1、菜单可以添加菜单和菜单项
 * 2、菜单项不能再添加菜单项
 * 就可以出现嵌套的菜单形式
 */

//菜单组件，用于抽取出菜单和菜单项的共性
abstract class MenuComponent {
    private String name;
    private String description;

    public MenuComponent(String name, String description) {
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

    public abstract void print(String prefix);


    //属于菜单的方法

    //属于菜单项的方法

}

class Menu extends MenuComponent {

    private List<MenuComponent> list = new ArrayList<>();

    public Menu(String name, String description) {
        super(name, description);
    }

    //迭代循环，递归
    public void print(String prefix) {
        System.out.println(prefix + "<<" + getName() + ">>" + getDescription());
        Iterator<MenuComponent> it = list.iterator();
        while (it.hasNext()) {
            MenuComponent mi = it.next();
            mi.print("\t" + prefix);
        }
    }


    public void add(MenuComponent mc) {
        list.add(mc);
    }
}

class MenuItem extends MenuComponent {
    //弄个素食
    private boolean vegetarian;
    private double price;

    public MenuItem(String name, String description, boolean vegetarian, double price) {
        super(name, description);
        this.vegetarian = vegetarian;
        this.price = price;
    }


    public void print(String prefix) {
        String str = vegetarian ? "(素食)" : "";
        System.out.println(prefix + getName() + str + ":" + getDescription() + " " + "价格:" + price);

    }
}
// ==========================================================================

//完全可以用装饰模式来加胡辣汤

public class Test {
    public static void main(String[] args) {
        Menu menu0 = new Menu("蜗牛餐厅菜单", "没有蜗牛，不要带你蜗牛");
        Menu menu1 = new Menu("陕菜", "XXXXXX");
        Menu menu2 = new Menu("川菜", "XXXXXX");
        Menu menu3 = new Menu("鲁菜", "XXXXXX");

        MenuItem mi1 = new MenuItem("胡辣汤1", "aaaaaa", false, 6);
        MenuItem mi2 = new MenuItem("胡辣汤2", "aaaaaa", false, 6);
        MenuItem mi3 = new MenuItem("胡辣汤3", "aaaaaa", false, 6);

        MenuItem mi4 = new MenuItem("剁椒鱼头1", "aaaaaa", false, 16);
        MenuItem mi5 = new MenuItem("剁椒鱼头2", "aaaaaa", false, 16);
        MenuItem mi6 = new MenuItem("剁椒鱼头3", "aaaaaa", false, 16);

        MenuItem mi7 = new MenuItem("热干面1", "aaaaaa", true, 7);
        MenuItem mi8 = new MenuItem("热干面2", "aaaaaa", true, 7);
        MenuItem mi9 = new MenuItem("热干面3", "aaaaaa", true, 7);

        menu1.add(mi1);
        menu1.add(mi2);
        menu1.add(mi3);

        menu2.add(mi4);
        menu2.add(mi5);
        menu2.add(mi6);

        menu3.add(mi7);
        menu3.add(mi8);
        menu3.add(mi9);

        menu0.add(menu1);
        menu0.add(menu2);
        menu0.add(menu3);
        menu0.print("");
    }
}

//现在，蜗牛餐厅，改变了业务，因为有的客户端吃素，不吃肉
//需要，要添加一个额外的功能，菜单中，只打印素食,如上代码
