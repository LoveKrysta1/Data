package designPattern.decorator.b;

/**
 * 1.为了满足a包中的需求变化：加入调料，尝试这样解决问题：
 * 为加牛奶的Decaf咖啡创建一个类
 * class DecafWithMilk{}
 * <p>
 * 为加牛奶的Espresso咖啡创建一个类
 * class EspressoWithMilk
 * <p>
 * 为加牛奶且加豆浆的Decaf创建一个类。
 * class DecafWithMilkAndSoy{}
 * <p>
 * ......
 * <p>
 * 这会引起，类爆炸！这样开发，简直让人想死！尤其是，后来又加入了一个新调料，枸杞。
 * <p>
 * 不能这样设计类
 */


public class AppTest {
}
