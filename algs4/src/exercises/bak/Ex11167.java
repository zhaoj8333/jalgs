package exercises.bak;

import edu.princeton.cs.algs4.StdOut;

public class Ex11167 {
    public static void main(String[] args) {
        String res = exR1(6);
        StdOut.println(res);
    }

    public static String exR1(int n) {
        if (n <= 0) {
            return "";
        }
        return exR1(n - 3) + n + exR1(n - 2) + n;
    }
}
