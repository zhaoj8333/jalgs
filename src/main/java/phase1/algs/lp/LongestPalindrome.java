package phase1.algs.lp;

/**
 * 最长汇文子串
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        bruteForce("aba");
    }

    public static String bruteForce(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1;
        String res = s.substring(0, 1);
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {

            }
        }
        return null;
    }

}
