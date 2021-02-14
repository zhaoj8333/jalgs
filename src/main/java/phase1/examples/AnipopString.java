package phase1.examples;

/**
 * 有序数组去重
 *
 * 消消乐:
 *    替换输入字符串的'b' 和 连续的 'a','c'
 *    'aadbbcc' -> 'aadcc'
 *    'abaca' -> 'aa'
 *    'aaabbccc' ->
 *
 * 要求： 遍历一次，不使用额外空间
 *
 *
 */

public class AnipopString {
    public static void main(String[] args) {
        String s = "abaca";
//        s = "aaabbccc";
        String res = deleteCharsInString(s);
        System.out.println(res);
    }

    private static void showChars(char[] chars) {
        for (int i = 0; i < chars.length; i++) {
            System.out.print(chars[i]);
            System.out.print(", ");
        }
        System.out.println();
    }

    private static String deleteCharsInString(String s) {
        char[] str = s.toCharArray();
        showChars(str);

        int newLen = 0;
        int i = 0;
        String strTest = "abaca";
        while (i != str.length) {
            if (str[i] != 'b') {
                str[newLen] = str[i];
                /*
                防止索引异常
                 */
                if (newLen == 0) {
                    newLen++;
                    i++;
                    continue;
                }
                if (str[newLen] == 'c' && str[newLen - 1] == 'a') {
                    newLen --;
                } else {
                    newLen ++;
                }
            }

            i++;
        }
        System.out.println(newLen);
        return new String(str);
    }


}
