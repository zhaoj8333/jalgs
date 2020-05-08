package ds.collection;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.Stack;

public class Loop {
    public static void main(String[] args) {
        demo1();

    }

    private static void demo1() {
        Stack<String> collection = new Stack<>();
        for (int i = 65; i <= 90; i++) {
            char a = (char) i;
            collection.push(Character.toString(a));
        }
        //
        //for (String s : collection) {
        //    StdOut.println(s);
        //}
        //
        Iterator<String> i = collection.iterator();
        StdOut.println(i);
        while (i.hasNext()) {
            String s = i.next();
            StdOut.println(s);
        }
    }

}
