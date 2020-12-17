package designPattern.principle.dependencyReserve.improve;

public class DependencyPass {
    public static void main(String[] args) {
        OpenAndClose openAndClose = new OpenAndClose(new ChangHong());
        openAndClose.open();

    }
}


class ChangHong implements ITV {

    @Override
    public void play() {
        System.out.println("长虹电视机打开");
    }
}


//方式1：通过接口传递实现依赖
//开关的接口
//interface IOpenAndClose{
//    public void open(ITV tv);//抽象方法，接收接口
//}
//
//interface ITV{//抽象方法，接收接口
//    public void play();
//}
//
////实现接口
//class OpenAndClose implements IOpenAndClose{
//
//    @Override
//    public void open(ITV tv) {
//        tv.play();
//    }
//}

//方式2:通过构造方法依赖传递
interface IOpenAndClose {
    public void open();//抽象方法
}

interface ITV {//ITV接口

    public void play();
}

class OpenAndClose implements IOpenAndClose {
    private ITV itv;

    public OpenAndClose(ITV itv) {
        this.itv = itv;
    }

    @Override
    public void open() {
        this.itv.play();
    }
}

//方式3，通过setter方法传递
//interface IOpenAndClose{
//    public void open();//抽象方法
//    public void setTv(ITV tv);
//}
//
//interface ITV{//ITV接口
//    public void play();
//}
//
//class OpenAndClose implements IOpenAndClose{
//
//    private  ITV itv;
//
//    @Override
//    public void open() {
//        this.itv.play();
//    }
//
//    @Override
//    public void setTv(ITV tv) {
//        this.itv = tv;
//
//    }
//}