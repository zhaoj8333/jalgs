package basic.abs;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Rools {
    public static void main(String[] args) {
        int t = Integer.parseInt(args[0]);

        int sides = 6;

        Counter[] rolls = new Counter[sides + 1];
        for (int i = 0; i <= sides; i++) {
            rolls[i] = new Counter(i + "'s");
        }

        for (int i = 0; i < t; i++) {
            int result = StdRandom.uniform(1, sides + 1);
            rolls[result].increment();
        }

        for (int i = 1; i <= sides; i++) {
            StdOut.println(rolls[i]);
        }
    }
}
