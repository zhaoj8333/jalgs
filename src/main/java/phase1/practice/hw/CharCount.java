package phase1.practice.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CharCount {
    public static void main(String[] args) {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        try {
            while ((line = br.readLine()) != null) {
                final char[] chars = br.readLine().toCharArray();
                if (chars.length > 0) {
                    char s = chars[0];
                    int total = 0;
                    for (int i = 0; i < line.length(); i++) {
                        if (line.charAt(i) == s || line.charAt(i) == s - 32) {
                            total++;
                        }
                    }
                    System.out.println(total);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
