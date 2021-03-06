package phase1.algs.bit;

import java.util.Arrays;

/**
 * @author allen
 */
public class Swap {
    private static void swap() {
        int a = 111;
        int b = 222;
        System.out.println("a:" + a + " " + "b:" + b);
        b = a ^ b;
        a = a ^ b;  // (a ^ a) ^ b -> b
        b = a ^ b;  // a ^ (a ^ b)
        System.out.println("a:" + a + " " + "b:" + b);
    }

    private static void swap1() {
        String a = "111";
        String b = "222";
        System.out.println("a:" + a + " " + "b:" + b);
//        b = a ^ b;   // bad operand types for binary phase1.algs.operator '^'
//        a = a ^ b;  // (a ^ a) ^ b -> b
//        b = a ^ b;  // a ^ (a ^ b)
        System.out.println("a:" + a + " " + "b:" + b);
    }

    private static void reverseArray() {
        int[] arr = new int[] {1, 2, 3, 4, 5, 6, 7};
        System.out.println(Arrays.toString(arr));
        int first, last;
        for (first = 0, last = arr.length - 1; first < last; first++, last--) {
            arr[last]  = arr[first] ^ arr[last];
            arr[first] = arr[first] ^ arr[last];
            arr[last]  = arr[first] ^ arr[last];
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        swap1();
        reverseArray();
    }
}
