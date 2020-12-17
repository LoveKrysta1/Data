package designPattern.principle.composite.supplment;

import java.util.Stack;

/**
 * jdk中栈的特例  垃圾标杆
 */

public class AppTest {
    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        //但是因为它继承vector  ，搞到栈不像栈
        System.out.println(stack.get(1));

        //先进后出
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());


    }

}
