package project.src.main;

import logic.Test2;
import project.src.logic.Test;

public class Main {
    public static void main(String[] args) {
        Test test = new Test();
        test.initAttr3List();
      //  test.initAttr3List();
        //System.out.println(test);
        Test2 test2 = new Test2();
        test2.printSwitchTest();
        test2.initAttr3List();
        test2.initAttr3List();
    }
}