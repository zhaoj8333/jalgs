package algs.bit;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * @author allen
 */
public class Swap {
    private static void swap() {
        int a = 111;
        int b = 222;
        StdOut.println("a:" + a + " " + "b:" + b);
        b = a ^ b;
        a = a ^ b;  // (a ^ a) ^ b -> b
        b = a ^ b;  // a ^ (a ^ b)
        StdOut.println("a:" + a + " " + "b:" + b);
    }

    private static void swap1() {
        String a = "111";
        String b = "222";
        StdOut.println("a:" + a + " " + "b:" + b);
//        b = a ^ b;   // bad operand types for binary algs.operator '^'
//        a = a ^ b;  // (a ^ a) ^ b -> b
//        b = a ^ b;  // a ^ (a ^ b)
        StdOut.println("a:" + a + " " + "b:" + b);
    }

    private static void reverseArray() {
        int[] arr = new int[] {1, 2, 3, 4, 5, 6, 7};
        StdOut.println(Arrays.toString(arr));
        int first, last;
        for (first = 0, last = arr.length - 1; first < last; first++, last--) {
            arr[last]  = arr[first] ^ arr[last];
            arr[first] = arr[first] ^ arr[last];
            arr[last]  = arr[first] ^ arr[last];
        }
        StdOut.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        swap1();
        reverseArray();
    }
}
