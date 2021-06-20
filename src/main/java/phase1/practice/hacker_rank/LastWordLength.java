package phase1.practice.hacker_rank;

import java.io.IOException;
import java.io.InputStream;

public class LastWordLength {
    public static void main(String[] args) {
//        String str = "hello nowcoder";
//        str = "XSUWHQ";
//        str = "ABSIB T";
//        final LastWordLength lastWordLength = new LastWordLength();
//        int len = lastWordLength.getLength(str);
//        System.out.println(len);
//
//        final String[] s = str.split(" ");
        getLength();
    }

    private static void getLength() {
        int count = 0;
        final InputStream in = System.in;

        try {
            char c = (char) in.read();
            while (c != '\n') {
                System.out.println("---" + c);
                if (c == ' ') {
                    count = 0;
                } else {
                    count++;
                }
                c = (char) in.read();
            }
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getLength(String str) {
        if (str == null) {
            return 0;
        }
        int len = 0;
        for (int i = str.length() - 1; i >= 0; i--) {
            char DELIMITER = ' ';
            if (DELIMITER != str.charAt(i)) {
                len++;
            } else {
                return len;
            }
        }
        return len;
    }
}
