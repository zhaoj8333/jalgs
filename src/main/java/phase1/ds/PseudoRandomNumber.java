package phase1.ds;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * {@link Random}, this class is thread safe, but not cryptographically secure
 */
public class PseudoRandomNumber {
    public static void main(String[] args) {
        Random random = new Random(System.currentTimeMillis());
//        random.setSeed(1000);
//        int i = random.nextInt();
//        System.out.println(i);

//        s(random);

//        next(random);

//        seed();

//        math();

//        randomForMt();

//        rules();
        my();
    }

    private static int my() {
        int n = 101;
        if (n <= 0) {
            throw new IllegalArgumentException("N must be bigger than 0");
        }
        // 2 ^ n
        if ((n & -n) == n) {
            return (int) ((n * (long) next(31) >> 31));
        }
        int bits, val;
        do {
            bits = next(31);
            val = bits % n;
        } while (bits - val + (n - 1) < 0);
        return val;
    }

    private static int next(int i) {
        return 0;
    }

    /**
     * two Random objects are created with the same seed will generate identical sequences of numbers
     */
    private static void rules() {
        int range = 100;
        Random random = new Random(197);
        ArrayList<Integer> radoms = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            radoms.add(random.nextInt(range));
        }
        System.out.println(radoms);
        Random random1 = new Random(197);
        ArrayList<Integer> radoms1 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            radoms1.add(random1.nextInt(range));
        }
        System.out.println(radoms1);
    }

    private static void randomForMt() {
        ThreadLocalRandom current = ThreadLocalRandom.current();
        int i = current.nextInt();
        System.out.println(i);
    }

    private static void math() {
        double random = Math.random();
        System.out.println(random);
    }

    private static void seed() {
        Random random = new Random(System.currentTimeMillis());
        System.out.println(random.nextInt());
        Random random1 = new Random(System.currentTimeMillis());
        System.out.println(random.nextInt());

    }

    private static void nextR(Random random) {
        int next = random.nextInt(10);
        System.out.println(next);
        boolean b = random.nextBoolean();
        System.out.println(b);

        byte[] bytes = new byte[10];
        random.nextBytes(bytes);
        System.out.println(bytes);

        double v = random.nextDouble();
        System.out.println(v);

        float v1 = random.nextFloat();
        System.out.println(v1);

        double v2 = random.nextGaussian();
        System.out.println(v2);

        long l = random.nextLong();
        System.out.println(l);
    }


    private static void s(Random random) {
        /**
         * returns an effectively unlimited stream of pseudo random double values, each between 0 and 1
         */
        DoubleStream doubles = random.doubles();
        System.out.println(doubles);

        IntStream ints = random.ints();
        System.out.println(ints);

        LongStream longs = random.longs();
        System.out.println(longs);
    }
}
