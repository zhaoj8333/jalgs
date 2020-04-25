package exercises.bak;

import edu.princeton.cs.algs4.StdOut;

public class Ext119 {

    private static int count;

    public static void main(String[] args) {
        int test = 18;
        toBinaryString(test);
        String str = "";

        str = toBinaryString1(test);
        StdOut.println("1 : " + str);
        StdOut.println("count : " + count);
        str = toBinaryString2(test);
        StdOut.println("2 : " + str);
    }

    public static void toBinaryString(int n) {
        StdOut.println(Integer.toBinaryString(n));
    }

    public static String toBinaryString1(int n) {
        count++;
        if (n == 1) {
            return "1";
        }

        return toBinaryString1(n / 2) + "" + (n % 2);
    }

    public static String toBinaryString2(int n) {
        String s = "";
        for (int i = n; i > 0; i /= 2) {
            s = (i % 2) + s;
        }

        return s;
    }


}
