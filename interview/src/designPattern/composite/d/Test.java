package designPattern.composite.d;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 为了解决c包中的问题：
 * 1、客户端依赖Menu和MenuItem
 * <p>
 * 重构代码如下
 * 组合模式，将对象组合成树形结构以表示“部分-整体”的层次结构，组合模式使得用户对
 * 单个对象和组合对象的使用具有一致性
 * 掌握组合模式的重点是要理解清楚“部分/整体” 还有“单个对象”与“组合对象”的含义
 */

//为了保证菜单和菜单项具有相同的接口，所以在它们的父类这里，把菜单需要的方法，和菜单
//项需要的方法统统定义出来。
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
    //作为父类，他根本就不知道是菜单还是菜单项
    //add remove  getChild => 等着子类重写方法

    /**
     * 这些方法对于菜单而言，是有意义的，但是对于菜单项而言没有意义，为什么还非要定义在这个父类中呢？
     * 为的就是：组合模式使得用户对单个对象和组合对象的使用具有一致性。
     */
    public void add(MenuComponent mc) {
        throw new UnsupportedOperationException();
    }

    public void remove(MenuComponent mc) {
        throw new UnsupportedOperationException();
    }

    public MenuComponent getChild(int i) {
        throw new UnsupportedOperationException();
    }

    public List<MenuComponent> getList() {
        throw new UnsupportedOperationException();
    }

    //属于菜单项的方法:getPrice() isVegetarian()

    /**
     * 这些方法对于菜单项而言，是有意义的，但是对于菜单而言没有意义，为什么还非要定义在这个父类中呢？
     * 为的就是：组合模式使得用户对单个对象和组合对象的使用具有一致性。
     */
    public double getPrice() {
        throw new UnsupportedOperationException();
    }

    public boolean isVegetarian() {
        throw new UnsupportedOperationException();
    }


}

class Menu extends MenuComponent {

    private List<MenuComponent> list = new ArrayList<>();

    public Menu(String name, String description) {
        super(name, description);
    }


    @Override
    public void add(MenuComponent mc) {
        list.add(mc);
    }

    @Override
    public void remove(MenuComponent mc) {
        list.remove(mc);
    }

    @Override
    public MenuComponent getChild(int i) {
        return list.get(i);
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
    private static void printV(MenuComponent mc) {
        Iterator<MenuComponent> it = mc.getList().iterator();
        while (it.hasNext()) {
            MenuComponent md = it.next();
            //强转 Instanceof  搞他妈的！！！
//            if (mc instanceof MenuItem) {
//                MenuItem mi = (MenuItem) mc;
//                if (mi.getPrice()>=10 && mi.getPrice()<=18) {
//                    mc.print("");
//                }
//            }
//            if (mc instanceof Menu) {
//                printV((Menu)mc);
//            }
            //组合模式来了！！！！
            try {
                if (md.isVegetarian()) {
                    md.print("");
                }
            } catch (Exception e) {
                printV(md);
            }
        }
    }
}

/**
 * 此时，客户端只依赖于MenuComponent，而不再知道Menu和MenuItem的存在了，这样符合最少知道原则
 * <p>
 * 此时，仍然需要客户端自己写递归代码，这样对客户端很不友好，最理想的情况是，hasNext，next。
 */
