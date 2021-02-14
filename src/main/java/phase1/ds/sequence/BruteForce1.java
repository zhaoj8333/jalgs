package phase1.ds.sequence;

public class BruteForce1 {

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
        int pi = 0, ti = 0;
//        while (pi < plen && ti < tlen) {
        while (pi < plen && ti - pi <= tlen - plen) {
            // tlen - plen :  text的临界索引值, 该值继续增加的话已经没有比较的必要
            // ti - pi : 每一轮比较中 文本串正在匹配的字串的开始索引
            if (textChars[ti] == pattChars[pi]) {
                ti++;
                pi++;
            } else {
                // ti = ti - pi + 1
                ti -= pi - 1;
                pi = 0;
            }
        }
        return (pi == plen) ? (ti - pi) : -1;
    }
}

