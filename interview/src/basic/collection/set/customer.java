package basic.collection.set;

public class customer {
    private String name;
    private int age;

    public customer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        customer customer = (customer) o;
//        return age == customer.age &&
//                Objects.equals(name, customer.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(name,age);
//    }
}
