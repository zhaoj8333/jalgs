package phase1.ds.string;

import java.util.Arrays;

public class Alphabet {
    public static final Alphabet BINARY         = new Alphabet("01");
    public static final Alphabet OCTAL          = new Alphabet("01234567");
    public static final Alphabet DECIMAL        = new Alphabet("0123456789");
    public static final Alphabet HEXADECIMAL    = new Alphabet("0123456789ABCDEF");
    public static final Alphabet DNA            = new Alphabet("ACTG");
    public static final Alphabet LOWERCASE      = new Alphabet("abcdefghijklmnopqrstuvwxyz");
    public static final Alphabet UPPERCASE      = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    public static final Alphabet PROTEIN        = new Alphabet("ACDEFGHIKLMNPQRSTVWY");
    public static final Alphabet BASE64         = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
    public static final Alphabet ASCII          = new Alphabet(128);
    public static final Alphabet EXTENDED_ASCII = new Alphabet(256);
    public static final Alphabet UNICODE16      = new Alphabet(65536);

    private char[] alphabet;
    private int[]  inverse;
    private int R;

    public Alphabet(int R) {
        alphabet = new char[R];
        inverse  = new int[R];
        this.R   = R;
        for (int i = 0; i < R; i++) {
            alphabet[i] = (char) i;
        }
        for (int i = 0; i < R; i++) {
            inverse[i] = i;
        }
    }

    public Alphabet(String alpha) {
        boolean[] unicode = new boolean[Character.MAX_VALUE];
//        System.out.println(Arrays.toString(unicode));
//        System.out.println(Character.MAX_VALUE);
//        System.exit(0);
//        System.out.println("len: " + alpha.length());
        for (int i = 0; i < alpha.length(); i++) {
            char c = alpha.charAt(i);
//            System.out.println(c);
            if (unicode[c]) {
//                throw new RuntimeException("Illegal alphabet: Character = '" + c + "'");
            } else {
                unicode[c] = true;
            }
        }
        alphabet = alpha.toCharArray();
//        System.out.println(Arrays.toString(alphabet));
        R = alpha.length();
        inverse = new int[Character.MAX_VALUE];
        for (int i = 0; i < inverse.length; i++) {
            inverse[i] = -1;
        }
//        System.out.println(Arrays.toString(inverse));
        for (int c = 0; c < R; c++) {
            inverse[alphabet[c]] = c;
//            System.out.println(alphabet[c]);
        }
//        System.out.println(Arrays.toString(inverse));
    }

    public Alphabet() {
        this(256);
    }

    public char toChar(int index) {
        if (index < 0 || index >= R) {
            throw new RuntimeException("Alphabet out of bounds");
        }
        return alphabet[index];
    }

    public int toIndex(char c) {
        if (c < 0 || c >= inverse.length || inverse[c] == -1) {
            throw new RuntimeException("Character " + c + " not in alphabet");
        }
        return inverse[c];
    }

    public boolean contains(char c) {
        return inverse[c] != -1;
    }

    public int R() { // 字符数量
        return R;
    }

    public int lgR() { // 表示一个索引所需的比特数
        int lgR = 0;
        for (int t = R; t > 1; t >>= 1) {
            lgR++;
        }
        return lgR;
    }

    public int[] toIndices(String s) { // 将s转换为R进制的整数
        char[] source = s.toCharArray();
        int[]  target = new int[s.length()];
        for (int i = 0; i < source.length; i++) {
            target[i] = toIndex(source[i]);
        }
        return target;
    }

    public String toChars(int[] indices) {
        StringBuilder s = new StringBuilder(indices.length);
        for (int index : indices) {
            s.append(toChar(index));
        }
        return s.toString();
    }

    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test2() {
        String str = "AaCGAACGGTTTACCCCG";
        Alphabet alpha = new Alphabet(str);
        int r = alpha.R();
        int[] count = new int[r];

        for (int i = 0; i < str.length(); i++) {
            if (alpha.contains(str.charAt(i))) {
                count[alpha.toIndex(str.charAt(i))]++;
            }
        }
        System.out.println(Arrays.toString(count));
    }

    private static void test1() {
        int[] encoded1  = Alphabet.BASE64.toIndices("HelloWorld");
        String decoded1 = Alphabet.BASE64.toChars(encoded1);
        System.out.println(decoded1);
        int[] encoded2  = Alphabet.DNA.toIndices("AACGAACGGTTTACCCCG");
        String decoded2 = Alphabet.DNA.toChars(encoded2);
        System.out.println(decoded2);

        int[] encoded3 = Alphabet.DECIMAL.toIndices("01234567890123456789");
        String decoded3 = Alphabet.DECIMAL.toChars(encoded3);
        System.out.println(decoded3);
    }
}
