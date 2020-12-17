package designPattern.iterator.b;

//该类演示，如何做自己制作ArrayLi，LinkedList，HashSet
//从来到尾自己写代码，不用jdk了

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class MyArrayList {
    private Object[] arr = null;
    int i;

    public MyArrayList() {
        this(10);
//        arr = new Object[10];
    }

    public MyArrayList(int capacity) {
        arr = new Object[capacity];
    }

    public void add(Object o) {
        //每次添加新元素之前，一定要确保底层数组长度是足够的。
        ensureCapacityInternal();
        arr[i++] = o;
    }

    private void ensureCapacityInternal() {
        if (i == arr.length) {
            arr = Arrays.copyOf(arr, arr.length * 3 / 2);
        }
    }

    public Object get(int index) {
        return arr[index];
    }

    public void remove(int index) {
        arr[index] = null;

        for (int i = index; i < arr.length - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[arr.length - 1] = null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] != null) {
                sb.append(arr[j]);
                sb.append(", ");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

    public int size() {
        int size = 0;
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] != null) {
                size++;
            }
        }
        return size;
    }

    //add作业
    //public void add(int index,Object obj){}

}

//内存不连续的 单向链表
class MyLinkedList {
    class Node {
        Object obj;
        Node next;//自关联->自个儿关联自己,用来找下一个对象的
    }

    private Node head; //第一个元素
    private Node tail; //最后一个元素

    public MyLinkedList() {
    }

    public void add(Object obj) {
        Node node = new Node();
        node.obj = obj;
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
    }

    public int size() {
        int size = 0;

        if (head == null) {
            return 0;
        }

        Node first = head;//定个first原因是，不要移动，要保持头不动
        if (first != null) {
            size = 1;
        }
        while (first.next != null) {//是null就跳出循坏了
            size++;
            first = first.next;
        }
        return size;
    }

    public Object get(int index) {//4
        if (index < 0) {
            throw new RuntimeException("你个二货，下标还能是负数");
        }
        if (index > size() - 1) {
            throw new RuntimeException("二货，来条巧克力");
        }
        Node first = head;
        int i = 0;
        while (i < index) {
            first = first.next;
            i++;
        }
        return first.obj;
    }

    //删除
    public void remove(Object obj) {//头是千万不敢移动的
        Node first = head;
        Node prev = null;
        while (!first.obj.equals(obj)) {
            prev = first;
            //delete
            first = first.next;
        }
        //当删除一个不存在元素时，就直接返回
        if (first == null) {
            return;
        }
        //流程走到这里，要被删除的元素，就被first所指向,用图像的思维就可以搞定
        //一定要有先后顺序 首先 把prev下一项指向那个，然后再把first的指向清空；
        if (prev != null) {
            prev.next = first.next;//牛逼
            first.next = null;
        } else {
            head = first.next;
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Node first = head;

        sb.append("[");
        while (first != null) {
            sb.append(first.obj);
            sb.append(", ");
            first = first.next;
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        return sb.toString();
    }


}


public class Test {
    public static void main(String[] args) {
//        List list = new ArrayList();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
//        list.remove(1);
//        System.out.println(list);
        new LinkedList<>();
//
//        MyArrayList mal = new MyArrayList();
//        mal.add(1);
//        mal.add(2);
//        mal.add(3);
//        mal.add(4);
//        mal.remove(1);
//        System.out.println(mal);
//
//        for (int i = 0; i < mal.size(); i++) {
//            Object obj = mal.get(i);
//            System.out.println("("+obj+")");
//        }

        MyLinkedList mll = new MyLinkedList();

        mll.add("aa");
        mll.add("bb");
        mll.add("cc");
        mll.add("dd");
        mll.add("ee");
        mll.add("ff");
        mll.add("gg");
        mll.remove("gg");

//        System.out.println(mll.get(-1));

        for (int i = 0; i < mll.size(); i++) {
            Object o = mll.get(i);
            System.out.println(o);

        }

//        System.out.println(mll.size());
//        System.out.println(mll);


    }
}
