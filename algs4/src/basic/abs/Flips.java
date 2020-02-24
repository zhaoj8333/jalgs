package basic.abs;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Flips {
    public static void main(String[] args) {
        int t = Integer.parseInt(args[0]);

        StdOut.println(t);

        Counter heads = new Counter("heads");
        Counter tails = new Counter("tails");

        for (int i = 0; i < t; i++) {
            if (StdRandom.bernoulli(0.5)) {
                heads.increment();
            } else {
                tails.increment();
            }
        }

        StdOut.println(heads);
        StdOut.println(tails);

        int d = heads.tally() - tails.tally();

        StdOut.println("delta: " + Math.abs(d));
    }
}
