package basic.collection.set;

import java.util.Comparator;

public class customerComparator implements Comparator<customer> {
    @Override
    public int compare(customer o1, customer o2) {
//        if (o1.getName().compareTo(o2.getName())>0) return -1;
//        if (o1.getName().compareTo(o2.getName())<0) return 1;
        if (o1.getAge() > o2.getAge()) return 1;
        if (o1.getAge() < o2.getAge()) return -1;


        return 0;
    }
}
