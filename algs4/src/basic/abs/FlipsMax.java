package basic.abs;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class FlipsMax {

    public static Counter max(Counter x, Counter y) {
        if (x.tally() > y.tally()) {
            return x;
        }

        return y;
    }

    public static void main(String[] args) {
        int t = Integer.parseInt(args[0]);

        Counter heads = new Counter("heads");
        Counter tails = new Counter("tails");

        for (int i = 0; i < t; i++) {
            if (StdRandom.bernoulli(0.5)) {
                heads.increment();
            } else {
                tails.increment();
            }
        }

        if (heads.tally() == tails.tally()) {
            StdOut.println("Tie");
        } else {
            StdOut.println(heads);
            StdOut.println(tails);
            StdOut.println(max(heads, tails) + "  wins");
        }
    }
}
