package designPattern.composite.e;

import java.util.*;

/**
 * 为了解决d包中的问题：
 * 1、客户需要自己写递归代码
 * <p>
 * 重构代码如下：
 * 让客户端只需要，调用hasNext和next即可
 * 重构代码如下
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

    public CompositeIterator iterator() {
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

    public CompositeIterator iterator() {
        return new CompositeIterator(list.iterator());
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

//不可小觑的递归代码
class CompositeIterator implements Iterator<MenuComponent> {

    private Stack<Iterator<MenuComponent>> s = new Stack<>();

    public CompositeIterator(Iterator<MenuComponent> it) {
        s.push(it);
    }

    @Override
    public boolean hasNext() {
        if (s.empty()) {
            return false;
        } else {
            Iterator<MenuComponent> it = s.peek();
            if (!it.hasNext()) {
                s.pop();
                return hasNext();
            } else {
                return true;
            }
        }

    }

    @Override
    public MenuComponent next() {

        Iterator<MenuComponent> it = s.peek();
        MenuComponent mc = it.next();

        if (mc instanceof Menu) {
            s.push(((Menu) mc).getList().iterator());
        }
        return mc;
    }
}

// ==========================================================================

//完全可以用装饰模式来加胡辣汤

public class Test {
    public static void main(String[] args) {
        MenuComponent menu0 = new Menu("蜗牛餐厅菜单", "没有蜗牛，不要带你蜗牛");
        MenuComponent menu1 = new Menu("陕菜", "XXXXXX");
        MenuComponent menu2 = new Menu("川菜", "XXXXXX");
        MenuComponent menu3 = new Menu("鲁菜", "XXXXXX");

        MenuComponent mi1 = new MenuItem("胡辣汤1", "aaaaaa", false, 6);
        MenuComponent mi2 = new MenuItem("凉皮", "aaaaaa", true, 6);
        MenuComponent mi3 = new MenuItem("胡辣汤3", "aaaaaa", false, 6);

        MenuComponent mi4 = new MenuItem("剁椒鱼头1", "aaaaaa", false, 16);
        MenuComponent mi5 = new MenuItem("干煸豆角", "aaaaaa", true, 16);
        MenuComponent mi6 = new MenuItem("剁椒鱼头3", "aaaaaa", false, 16);

        MenuComponent mi7 = new MenuItem("牛肉拉面", "aaaaaa", false, 12);
        MenuComponent mi8 = new MenuItem("老干妈土豆丝", "aaaaaa", true, 7);
        MenuComponent mi9 = new MenuItem("热干面3", "aaaaaa", true, 7);

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

        CompositeIterator iterator = menu0.iterator();
        while (iterator.hasNext()) {//方法里面判断有没有欧迭代器，有迭代器返回true之后就放在这里拿元素
            MenuComponent mc = iterator.next();

            try {
                if (mc.isVegetarian()) {
                    System.out.println(mc.getName() + " " + mc.getDescription());
                }
            } catch (Exception e) {
                //吞异常
            }

        }

//        Stack s = new Stack();
//        s.push("a");
//        s.push("b");
//
//        System.out.println(s.peek());//偷看一眼，不取出来
//        System.out.println(s.peek());//b
//        System.out.println(s.peek());//b
    }
}


/**
 * 此时，客户端只依赖于MenuComponent，而不再知道Menu和MenuItem的存在了，这样符合最少知道原则
 * <p>
 * 此时，仍然需要客户端自己写递归代码，这样对客户端很不友好，最理想的情况是，hasNext，next。
 */
