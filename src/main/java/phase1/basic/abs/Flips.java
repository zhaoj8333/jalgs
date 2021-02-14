package phase1.basic.abs;

public class Flips {
    public static void main(String[] args) {
        int t = Integer.parseInt(args[0]);

        System.out.println(t);

        Counter heads = new Counter("heads");
        Counter tails = new Counter("tails");

        for (int i = 0; i < t; i++) {
//            if (StdRandom.bernoulli(0.5)) {
//                heads.increment();
//            } else {
//                tails.increment();
//            }
        }

        System.out.println(heads);
        System.out.println(tails);

        int d = heads.tally() - tails.tally();

        System.out.println("delta: " + Math.abs(d));
    }
}
