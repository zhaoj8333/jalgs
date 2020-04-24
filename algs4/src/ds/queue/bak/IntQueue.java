package ds.queue.bak;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

import java.util.Arrays;

public class IntQueue {
    public static void main(String[] args) {
//        File file = new File("~/jalgs/data/tinyG.txt");
//        System.out.println(file);

        readFromFile("file:////home/allen/jalgs/data/tinyG.txt");
    }

    public static void readFromFile(String name) {
        In in = new In(name);
        Queue<Integer> q = new Queue<Integer>();

        while (!in.isEmpty()) {
            q.enqueue(in.readInt());
        }

        int n = q.size();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = q.dequeue();
        }
        System.out.println(q);
        System.out.println(Arrays.toString(a));
    }
}
