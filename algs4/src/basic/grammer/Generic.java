package basic.grammer;

import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class Generic {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        ArrayList<String> stringList = new ArrayList<String>();
        ArrayList<Integer> intList   = new ArrayList<Integer>();

        Class classStringArr  = stringList.getClass();
//        Class calssIntegerArr = new intList.getClass();

        StdOut.println(classStringArr);
//        StdOut.println(calssIntegerArr);
    }
}
