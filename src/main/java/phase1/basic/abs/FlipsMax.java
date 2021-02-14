package phase1.basic.abs;

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
//            if (StdRandom.bernoulli(0.5)) {
//                heads.increment();
//            } else {
//                tails.increment();
//            }
        }

        if (heads.tally() == tails.tally()) {
            System.out.println("Tie");
        } else {
            System.out.println(heads);
            System.out.println(tails);
            System.out.println(max(heads, tails) + "  wins");
        }
    }
}
