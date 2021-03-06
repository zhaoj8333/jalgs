package phase1.ds.stack.app;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author allen
 */
public class EvaluateBrackets {
    public static void main(String[] args) {
        String s = "{{}[]}{}";
//        s = "()]";
//        s = "[";
//        s = "[[]";
//        s = "()]";
//        s = "[()]";
//        s = "{[])";
//        s = "])}}";
//        s = "0";
//        s = "{(}";
//        s = "{{)}";
//        s = "[()[]";
        performanceCompare(s);
//        System.out.println("s: " + s);
//        boolean valid = byStack(s);
//        System.out.println("byStack:    " + valid);
//        valid = byStringOp(s);
//        System.out.println("byStringOp: " + valid);
//        valid = byHashMap(s);
//        System.out.println("byHashMap : " + valid);
    }

    private static void performanceCompare(String s) {
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 1000000 ; i++) {
            boolean valid = byStack(s);
        }
        System.out.println("byStack    : " + (System.currentTimeMillis() - begin));

        begin = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            boolean valid = byStringOp(s);
        }
        System.out.println("byStringOp : " + (System.currentTimeMillis() - begin));

        begin = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            boolean valid = byHashMap(s);
        }
        System.out.println("byHashMap : " + (System.currentTimeMillis() - begin));
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
     *  ??????????????????????????????
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
     *  ????????????????????????????????????
     *  ??????????????????
     *      ????????????????????????
     *      ????????????????????????????????????????????????????????????????????????????????????
     *      ???????????????????????????????????????????????????????????????????????????????????????
     */
    private static boolean byStack(String str) {
        if ("".equals(str)) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        boolean valid;
        int len = str.length();
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            Character s;
            if (c == '{' || c == '(' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                final Character left = stack.pop();
                if (left == '(' && c != ')') {
                    return false;
                }
                if (left == '{' && c != '}') {
                    return false;
                }
                if (left == '[' && c != ']') {
                    return false;
                }
            }
            /*
            switch (c) {
                // push ???????????????
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
             */
        }
        return stack.isEmpty();
    }
}
