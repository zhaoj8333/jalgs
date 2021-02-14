package phase1.algs.analyze;

public class DoublingRatio {
    public static double timeTrial(int N) {
        int MAX = 1000000;
        int[] arr = new int [N];
        for (int i = 0; i < N; i++) {
        }
        int cnt = ThreeSum.count(arr);

        return 0.1;
    }

    public static void main(String[] args) {
        double prev = timeTrial(123);
        for (int N = 250; true; N <<= 1) {
            double time = timeTrial(N);

            prev = time;
        }
    }
}
