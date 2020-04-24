package ds.queue;

import edu.princeton.cs.algs4.StdOut;

import java.util.Scanner;

public class ArrayQueueTest {
    public static void main(String[] args) throws Exception {
        ArrayQueue arrayQueue = new ArrayQueue(4);
        Scanner sc = new Scanner(System.in);
        while (true) {
            String tip = "s:显示" + "  a:添加" + "  e:退出" + "  g:获取" + "  p:查看";
            StdOut.println(tip);
            StdOut.println("first: " + arrayQueue.getFirst());
            StdOut.println("last : " + arrayQueue.getLast());
            char op = sc.next().charAt(0);
            switch (op) {
                case 's':
                    arrayQueue.show();
                    break;
                case 'a':
                    int n = sc.nextInt();
                    arrayQueue.add(n);
                    break;
                case 'e':
                    System.exit(0);
                    break;
                case 'g':
                    int get = arrayQueue.get();
                    StdOut.println("get: " + get);
                    break;
                case 'p':
                    int curr = arrayQueue.peek();
                    StdOut.println("peek: " + curr);
                    break;
                default:
                    break;
            }
        }


    }
}
