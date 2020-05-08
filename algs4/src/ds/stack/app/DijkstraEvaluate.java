package ds.stack.app;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Stack;

public class DijkstraEvaluate {
    public static void main(String[] args) {
//        System.out.println(s);
//        while (!StdIn.isEmpty()) {
//            String s = StdIn.readString();
//            System.out.println(s);
//        }
        eval();
    }

    /**
     * Dijkstra 双栈算术表达式
     * 
     */
    public static void eval() {
        Stack<String> operations = new Stack<>();
        Stack<Double> values     = new Stack<>();
        StdOut.println(StdIn.readString());
        while (!StdIn.isEmpty()) {
            String string = StdIn.readString();
            // 读取运算符
            if ("(".equals(string)) {

            } else if ("+".equals(string))    {
                operations.push(string);
            } else if ("-".equals(string)) {
                operations.push(string);
            } else if ("*".equals(string)) {
                operations.push(string);
            } else if ("/".equals(string)) {
                operations.push(string);
            } else if ("sqrt".equals(string)) {
                operations.push(string);
            } else if (")".equals(string)) {
                String operation = operations.pop();
                double value     = values.pop();
                if ("+".equals(operation)) {
                    value = values.pop() + value;
                } else if ("-".equals(operation)) {
                    value = values.pop() - value;
                } else if ("*".equals(operation)) {
                    value = values.pop() * value;
                } else if ("/".equals(operation)) {
                    value = values.pop() / value;
                } else if ("sqrt".equals(operation)) {
                    value = Math.sqrt(value);
                }

                values.push(value);
            // 读取字符值
            } else {
                values.push(Double.parseDouble(string));
            }
        }
        StdOut.println("result: " + values.pop());
    }
}
