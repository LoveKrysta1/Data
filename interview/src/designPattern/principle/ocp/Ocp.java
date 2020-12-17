package designPattern.principle.ocp;

public class Ocp {
    public static void main(String[] args) {
//使用看看存在的问题
        GraphicEditor graphicEditor = new GraphicEditor();
        graphicEditor.drawShape(new Rectangle());
        graphicEditor.drawShape(new Circle());

    }
}

//这是一个用于绘图的类
class GraphicEditor {
    //接收Shape对象，然后根据type，来绘制不同的图形
    public void drawShape(Shape s) {
        if (s.m_type == 1) drawRectangle(s);
        else if (s.m_type == 2) drawCircle(s);
        else if (s.m_type == 3) drawTriangle(s);
    }

    public void drawRectangle(Shape s) {
        System.out.println("矩形");
    }

    public void drawCircle(Shape s) {
        System.out.println("圆形");
    }

    public void drawTriangle(Shape s) {
        System.out.println("三角形");
    }
}

//shape类。基类
class Shape {
    int m_type;
}

class Rectangle extends Shape {
    Rectangle() {
        super.m_type = 1;
    }
}

class Circle extends Shape {
    Circle() {
        super.m_type = 2;
    }
}

//新增三角形
class Triangle extends Shape {
    Triangle() {
        super.m_type = 3;
    }
}

