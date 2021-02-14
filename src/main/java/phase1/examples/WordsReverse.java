package phase1.examples;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WordsReverse {
    public static void main(String[] args) {
        String string = "god is a girl";
//        String res = reverseByDoublePointer(string);
        String res = reverseByArrayList(string);
        System.out.println(res);
    }

    private static String reverseByArrayList(String s) {
        String[] str = s.split("\\s+");
        List<String> strlist = Arrays.asList(str);
//        System.out.println(strlist);
        Collections.reverse(strlist);

        /*
        StringJoiner sj = new StringJoiner(" ", "", "");
        for (int i = 0; i < str.length; i++) {
            sj.add(str[i]);
        }

         */

        return String.join(" ", strlist); // StringJoiner
//        return sj.toString();
    }

    private static String reverseByDoublePointer(String s) {
        String[] str = s.split("\\s+");
        int left = 0;
        int right = str.length - 1;

        while (left < right) {
            String temp = str[left];
            str[left]   = str[right];
            str[right]  = temp;

            left ++;
            right --;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length; i++) {
            sb.append(str[i]);
            if (i != str.length - 1) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }
}
