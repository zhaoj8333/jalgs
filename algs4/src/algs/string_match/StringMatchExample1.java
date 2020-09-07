package algs.string_match;

import edu.princeton.cs.algs4.StdOut;

public class StringMatchExample1 {
    public static void main(String[] args) {
        String str = "Java,Java, hello,world!";
        String newStr = str.replaceAll("Java", "哈哈哈");
        StdOut.println("newStr: " + newStr);
    }
}
