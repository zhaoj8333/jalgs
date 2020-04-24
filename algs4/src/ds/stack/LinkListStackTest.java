package ds.stack;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LinkListStackTest {
    public static void main(String[] args) {
        // test1();
        // test2();
        test3();
    }

    public static void test3() {
        LinkListStack<String> lls = new LinkListStack<>();
        lls.push("aaaa");
        lls.push("bbbb");
        lls.push("cccc");
        lls.push("dddd");
        lls.display();
        StdOut.println();
        lls.displayByIterator();
        StdOut.println();
        lls.displayByIterator();
    }

    public static void test2() {
        LinkListStack<String> lls = new LinkListStack<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                lls.push(item);
            } else if (!lls.isEmpty()) {
                StdOut.println(lls.pop() + " ");
            }
            lls.display();
        }
        StdOut.println("( " + lls.size() + " left on stack )" );
    }

    public static void test1() {
        LinkListStack<String> lls = new LinkListStack<>();
        lls.push("aaaa");
        lls.push("bbbb");
        lls.push("cccc");
        lls.push("dddd");

        StdOut.println("isEmpty: " + lls.isEmpty());
        StdOut.println("size:    " + lls.size());
        lls.display();

        StdOut.println("=============================");
        lls.pop();
        StdOut.println("after pop isEmpty: " + lls.isEmpty());
        StdOut.println("after pop size:    " + lls.size());

        lls.display();
    }
}
