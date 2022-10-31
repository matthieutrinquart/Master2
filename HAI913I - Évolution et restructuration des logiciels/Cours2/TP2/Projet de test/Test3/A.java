

import java.util.ArrayList;
import java.util.List;
import B;

public class A  {
    private String attr2;
    private int attr1;
    private List<Integer> attr3; // Not count
    private B test = new B();

    public A() {
        this.attr1 = 15;
        this.attr2 = "B+";
        this.attr3 = new ArrayList<>();
        test.initAttr3List();
    }

    private class D {

    }

    public void printSwitchTest() {
        switch (this.attr2) {
            case "B+":
                System.out.println(this.attr2);
                break;
            default:
                System.out.println("BAD");
                break;
        }
    }

    public void initAttr3List() {
        for (int i = 0; i < attr1; i++) {
            attr3.add(i);
        }
        if (attr3.size() != 0) {
            while(attr3.size() < 20) {
                attr3.add(0);
            }
//            do { } while (attr3.size() > 5); // do while represent 1 statement
            do {
                attr3.remove(0);
            } while (attr3.size() > 5); // do while represent 1 statement
        }
    }

//    @Override public String toString()
    @Override
    public String toString() {
        return super.toString();
    }
}