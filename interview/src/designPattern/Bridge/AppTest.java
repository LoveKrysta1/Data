package designPattern.Bridge;

interface Brand {
    void sale();
}

class Lenovo implements Brand {

    @Override
    public void sale() {
        System.out.println("销售联想电脑");
    }
}

class Dell implements Brand {

    @Override
    public void sale() {
        System.out.println("销售戴尔电脑");
    }
}


class Computer {
    protected Brand brand;

    public Computer(Brand brand) {
        this.brand = brand;
    }

    public void sale() {
        brand.sale();
    }
}

class Desktop extends Computer {

    public Desktop(Brand brand) {
        super(brand);
    }

    @Override
    public void sale() {
        super.sale();
        System.out.println("销售台式机");
    }
}

class Lapton extends Computer {

    public Lapton(Brand brand) {
        super(brand);
    }

    @Override
    public void sale() {
        super.sale();
        System.out.println("销售手提");
    }
}

public class AppTest {
}
