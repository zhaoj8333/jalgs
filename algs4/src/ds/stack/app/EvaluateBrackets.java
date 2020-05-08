package ds.stack.app;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author allen
 */
public class EvaluateBrackets {
    public static void main(String[] args) {
        String s = "{{}}{}";
//        s = "()]";
//        s = "[";
//        s = "[[]";
        s = "()]";
//        s = "[()]";
//        s = "{[])";
//        s = "])}}";
//        s = "0";
//        s = "{(}";
//        s = "{{)}";
        s = "[()[]";
//        performanceCompare(s);
        StdOut.println("s: " + s);
        boolean valid = byStack(s);
        StdOut.println("byStack:    " + valid);
        valid = byStringOp(s);
        StdOut.println("byStringOp: " + valid);
        valid = byHashMap(s);
        StdOut.println("byHashMap : " + valid);
    }

    private static void performanceCompare(String s) {
        Stopwatch sw = new Stopwatch();
        for (int i = 0; i < 1000000 ; i++) {
            boolean valid = byStack(s);
        }
        StdOut.println("byStack    : " + sw.elapsedTime());

        sw = new Stopwatch();
        for (int i = 0; i < 1000000; i++) {
            boolean valid = byStringOp(s);
        }
        StdOut.println("byStringOp : " + sw.elapsedTime());
    }

    private static boolean byHashMap(String s) {
        if ("".equals(s)) {
            return true;
        }
        HashMap<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');

        Stack<Character> stack = new Stack<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            Character c = s.charAt(i);
            Character left;
            if (map.containsKey(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                left = stack.pop();
                if (!c.equals(map.get(left))) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     *  字符串操作很耗费时间
     */
    private static boolean byStringOp(String s) {
        while (s.contains("{}") || s.contains("[]") || s.contains("()")) {
            s = s.replace("{}", "");
            s = s.replace("()", "");
            s = s.replace("[]", "");
        }
        return s.isEmpty();
    }

    /**
     *  遇见左字符，将左字符入栈
     *  遇见右字符：
     *      栈为空，括号无效
     *      不为空，将栈顶字符出栈，与右字符匹配，不匹配说明括号无效
     *      所有字符扫描完了以后，如果给栈为空，则合法，不为空括号无效
     */
    private static boolean byStack(String str) {
        Stack<Character> stack = new Stack<>();
        if ("".equals(str)) {
            return true;
        }
        boolean valid = false;

        int len = str.length();
        for (int i = 0; i < len; i++) {
            Character c = str.charAt(i);
            Character s;
            switch (c) {
                // push
                case '{':
                case '(':
                case '[':
                    stack.push(c);
                    break;
                // pop
                case ')':
                    if (stack.isEmpty()) {
                        return false;
                    }
                    s = stack.pop();
                    valid = '(' == s;
                    if (!valid) {
                        return false;
                    }
                    break;
                case ']':
                    if (stack.isEmpty()) {
                        return false;
                    }
                    s = stack.pop();
                    valid = '[' == s;
                    if (!valid) {
                        return false;
                    }
                    break;
                case '}':
                    if (stack.isEmpty()) {
                        return false;
                    }
                    s = stack.pop();
                    valid = '{' == s;
                    if (!valid) {
                        return false;
                    }
                    break;
                default:
                    return false;
            }
        }
        if (!stack.isEmpty()) {
            valid = false;
        }
        return valid;
    }
}
