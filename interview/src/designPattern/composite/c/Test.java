package designPattern.composite.c;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 为了解决b包中的问题：
 * 1、客户端需要素菜菜单
 * 2、客户需要平价菜单
 * 3、客户需要土豪菜单
 * 4、客户需要辣菜菜单
 * <p>
 * 重构代码如下
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

    public List<MenuComponent> getList() {
        return list;
    }

    public void setList(List<MenuComponent> list) {
        this.list = list;
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

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
        MenuItem mi2 = new MenuItem("凉皮", "aaaaaa", true, 6);
        MenuItem mi3 = new MenuItem("胡辣汤3", "aaaaaa", false, 6);

        MenuItem mi4 = new MenuItem("剁椒鱼头1", "aaaaaa", false, 16);
        MenuItem mi5 = new MenuItem("干煸豆角", "aaaaaa", true, 16);
        MenuItem mi6 = new MenuItem("剁椒鱼头3", "aaaaaa", false, 16);

        MenuItem mi7 = new MenuItem("牛肉拉面", "aaaaaa", false, 12);
        MenuItem mi8 = new MenuItem("老干妈土豆丝", "aaaaaa", true, 7);
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

        printV(menu0);
    }

    //客户端自己写
    private static void printV(Menu menu) {
        Iterator<MenuComponent> it = menu.getList().iterator();
        while (it.hasNext()) {
            MenuComponent mc = it.next();
            //强转 Instanceof  搞他妈的！！！
            if (mc instanceof MenuItem) {
                MenuItem mi = (MenuItem) mc;
                if (mi.getPrice() >= 10 && mi.getPrice() <= 18) {
                    mc.print("");
                }
            }
            if (mc instanceof Menu) {
                printV((Menu) mc);
            }
        }
    }
}


/**
 * 现在，很灵活，但是，客户端必须知道底层的数据结构。（迭代器模式）
 * 另外一个缺点是，客户端必须知道MenuItem和Menu的存在。客户端应该只需要知道MenuComponent的存在就够了（组合模式来解决）
 * <p>
 * <p>
 * //现在，很灵活，但是，客户端必须知道底层的数据结构是List，如果替换为set或者Map，客户端还要修改代码。（迭代器模式来解决）
 * 另外一个缺点是，客户端必须知道MenuItem和Menu的存在。客户端只需要知道MenuComponent的存在就够了(组合模式来解决)
 */
