package designPattern.principle.demeter;

import java.util.ArrayList;
import java.util.List;

public class Demeter1 {
    public static void main(String[] args) {
        //创建了一个SchoolManager 对象
        SchoolManger schoolManger = new SchoolManger();
        //输出学院的员工id 和 学校总部的员工信息
        schoolManger.printAllEmployee(new CollegeManager());

    }
}

//学校总部员工
class Employee {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

//学院的员工类
class CollegeEmployee {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

//管理学院员工的管理类
class CollegeManager {
    public List<CollegeEmployee> getAllEmployee() {
        //返回学院的所有员工
        List<CollegeEmployee> list = new ArrayList<CollegeEmployee>();
        for (int i = 0; i < 10; i++) {//增加了10个员工到list集合里面
            CollegeEmployee emp = new CollegeEmployee();
            emp.setId("学院员工id=" + i);
            list.add(emp);
        }
        return list;
    }
}
//学校管理类

//分析schoolManager 类的直接朋友类有哪些 Employee、CollegeManager
//CollegeEmployee 不是直接朋友 而是一个陌生类，这样违背了迪米特法则
class SchoolManger {
    //返回学校总部的员工
    public List<Employee> getAllEmployee() {
        List<Employee> list = new ArrayList<Employee>();

        for (int i = 0; i < 5; i++) {//这里我们增加了5个员工到list
            Employee emp = new Employee();
            emp.setId("学校总部员工id=" + i);
            list.add(emp);
        }
        return list;
    }

    //该方法完成输出学校总部和学院员工信息(id)
    void printAllEmployee(CollegeManager sub) {

        //分析问题
        //1.这里的CollegeEmployee不是schoolManager的直接朋友
        //2.CollegeEmployee 是以局部变量的方式出现在SchoolManager
        //3.违反了 迪米特法则

        //获取到学院员工
        List<CollegeEmployee> list1 = sub.getAllEmployee();
        System.out.println("--------分公司员工---------");
        for (CollegeEmployee e : list1) {
            System.out.println(e.getId());
        }
        //获取到学校总部员工
        List<Employee> list2 = this.getAllEmployee();
        System.out.println("---------学校总部员工----------");
        for (Employee e : list2) {
            System.out.println(e.getId());
        }

    }
}




