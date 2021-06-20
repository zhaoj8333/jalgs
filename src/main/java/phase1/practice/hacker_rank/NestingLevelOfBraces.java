package phase1.practice.hacker_rank;

import java.util.Scanner;
import java.util.Stack;

public class NestingLevelOfBraces {

    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        final String s = sc.nextLine();
        final int depth = getDepth(s);
        System.out.println(depth);
    }

    // {b()ac{()}}
    private static int getDepth(String str) {
        int depth = 0;
        final Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                stack.push(str.charAt(i));
            } else if (str.charAt(i) == ')') {
                depth = Math.max(depth, stack.size());
                stack.pop();
            }
        }
        return depth;
    }

}
