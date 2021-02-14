package phase1.ds.sequence;


/**
 * KMP: Knuth - Morris - Pratt
 * KMP相对于蛮力算法的优势:
 *     KMP充分利用了此前比较过的内容, 可以跳过一些不必要的比较位置
 *
 * KMP核心:
 *     KMP一旦发现字符失配,就会在失配字符左边查找其真前后缀和真后缀,一旦发现,就会向右滑动到该前缀与后缀对其的位置, 减少匹配次数
 *     KMP利用了重复数据, 即最大公共子串, 但是如果没有公共字串, 则退化为蛮力算法
 */
public class Kmp {
    public static int indexOf(String text, String pattern) {
        if (text == null || pattern == null) {
            return - 1;
        }
        if (text.length() == 0) {
            return - 1;
        }
        final char[] textChars = text.toCharArray();
        int tlen = textChars.length;
        final char[] pattChars = pattern.toCharArray();
        int plen = pattChars.length;
        if (tlen < plen) {
            return - 1;
        }
        int[] nexts = next(pattern);
        int pi = 0, ti = 0, lenDelta = tlen - plen;
        while (pi < plen && ti - pi <= lenDelta) {
            if (pi < 0 || textChars[ti] == pattChars[pi]) {
                ti++;
                pi++;
            } else {
                pi = nexts[pi];
            }
        }
        return (pi == plen) ? (ti - pi) : -1;
    }

    /**
     * next[0] = -1,使模式串向后挪一个单位
     *
     * 已知next[i] == n,
     *      如果pattern[i] == pattern[n], 则next[i+1] == n + 1
     *      如果pattern[i] != pattern[n], next[n] == k,
     *         如果pattern[i] == pattern[k], next[i+1] == k+1
     */
    private static int[] next(String pattern) {
        char[] chars = pattern.toCharArray();
        int[] nexts = new int[chars.length];

        nexts[0] = -1;
        int i = 0;
        int n = -1;
        int iMax = chars.length - 1;
        while (i < iMax) {
            if (n < 0 || chars[i] == chars[n]) {
                nexts[++i] = ++n;
            } else {
                n = nexts[n];
            }
        }
        return nexts;
    }

}
