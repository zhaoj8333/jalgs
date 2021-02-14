package phase1.algs.compress;

import java.util.Scanner;

public class StringCompress {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            final String string = compressString(s);
            System.out.println(string);
        }
    }

    private static String compressString(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        StringBuilder compressedStr = new StringBuilder();
        int low = 0, high, count;
        int length = s.length();
        char c;
        while (low < length) {
            high = low;
            c = s.charAt(low);
            while ((high < length) && s.charAt(high) == c) {
                high ++;
            }
            count = high - low;
            compressedStr.append(c);
            compressedStr.append(count);
            low = high;
        }
        return (compressedStr.toString().length() < length) ? compressedStr.toString() : s;
    }
}
