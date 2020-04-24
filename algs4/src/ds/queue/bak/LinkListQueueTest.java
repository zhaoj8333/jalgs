package ds.queue.bak;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LinkListQueueTest {
    public static void main(String[] args) {
        LinkListQueue<String> q = new LinkListQueue<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (! "-".equals(item)) {
                q.enqueue(item);
            } else if (!q.isEmpty()) {
                StdOut.println(q.dequeue());
            }
            StdOut.println("size: " + q.size());
            q.display();
        }
    }
}
