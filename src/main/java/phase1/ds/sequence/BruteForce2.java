package phase1.ds.sequence;

public class BruteForce2 {

     /* 蛮力算法: 以字符为单位, 从左到右移动模式串, 直到匹配成功*/
    public static int indexOf(String text, String pattern) {
        if (text == null || pattern == null) {
            return -1;
        }
        if (text.length() == 0) {
            return -1;
        }
        final char[] textChars = text.toCharArray();
        int tlen = textChars.length;
        final char[] pattChars = pattern.toCharArray();
        int plen = pattChars.length;
        if (tlen < plen) {
            return -1;
        }
        /////////////////////////
        // pi [0, plen)
        // ti [0, tlen - plen], ti指每轮比较中Text首个比较字符的位置

        int tiMax = tlen - plen;
        for (int ti = 0; ti < tiMax; ti++) {
            int pi = 0;
            for (; pi < plen; pi++) {
                if (textChars[ti + pi] != pattChars[pi]) {
                    break;
                }
            }
            if (pi == plen) {
                return ti;
            }
        }
        return -1;
    }
}

