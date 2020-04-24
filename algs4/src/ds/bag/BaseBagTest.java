package ds.bag;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BaseBagTest {
    public static void main(String[] args) {
        BaseBag<Double> numbers = new BaseBag<Double>();
        while (! StdIn.isEmpty()) {
            numbers.add(StdIn.readDouble());
        }
        StdOut.println(numbers);
        StdOut.println(numbers.size());

        int n = numbers.size();

        double sum = 0.0;
        for (double x : numbers) {
            sum += x;
        }

        double mean = sum / n;

        sum = 0.0;
        for (double x : numbers) {
            sum += (x - mean) * (x - mean);
        }
        double std = Math.sqrt(sum / (n - 1));
        StdOut.printf("Mean: %.2f\n", mean);
        StdOut.printf("Std dev: %.2f\n", std);
    }
}
