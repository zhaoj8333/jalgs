package basic.abs;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class StringTest {
    public static void main(String[] args) {
        boolean isPalindrome = isPalindrome("AFAT");
//        StdOut.println("isPalindrome: " + isPalindrome);
//        StdOut.println("extension: " + getExtension("/home/index.php"));
//        printLineFromStdin(args[0]);

//        readFromStdin();
    }

    public static boolean isSorted(String[] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[i - 1].compareTo(a[i]) > 0) {
                return false;
            }
        }

        return true;
    }

    public static void readFromStdin() {
        String input   = StdIn.readAll();
        String[] words = input.split("\\s+");

        System.out.println(Arrays.toString(words));
    }

    public static boolean isPalindrome(String s) {
        int n = s.length();
        for (int i = 0; i < n / 2; i++) {
//            System.out.println(s.charAt(i));
//            System.out.println(s.charAt(n - 1 - i));
            if (s.charAt(i) != s.charAt(n - 1 - i)) {
                return false;
            }
        }

        return true;
    }

    public static String getExtension(String fpath) {
        int dot = fpath.indexOf(".");
        String base = fpath.substring(0, dot);
        String ext  = fpath.substring(dot + 1, fpath.length());

        return ext;
    }

    public static void printLineFromStdin(String query) {
        while (!StdIn.isEmpty()) {
            String s = StdIn.readLine();
            if (s.contains(query)) {
                StdOut.println(s);
            }
        }
    }
}
