package phase1.basic.bit;

public class SignOfInteger {
    public static void main(String[] args) {
//        int sign = getSign1(-3);
//        System.out.println("sign: " + sign);

        int sign = getSign2(-5);

        System.out.println("sign: " + sign);
    }

    private static int getSign2(int v) {
//        int sign = (v >> 31) |
        return 0;
    }

    /**
     *  get the sign of v by comparison and branching
     *   char phase1.basic.phase1.algs.analyze.bit
     *
     */
    private static int getSign1(int v) {
        if (v < 0) {
            return -1;
        } else {
            return 0;
        }
        /*
            integer comparison is very cheap, but branching can be expensive, due to branch mis-prediction
         */
    }

}

