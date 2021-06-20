package phase1.practice.hacker_rank;

public class CountingValleys {
    public static void main(String[] args) {
        String input = "DDUUDDUDUUUD";
        int valleys = counting(input.length(), input);
        System.out.println(valleys);
    }

    private static int counting(int steps, String path) {
        int count = 0;
        if ("".equals(path)) {
            return count;
        }
        int level = 0;
        for (char c : path.toCharArray()) {
            if (c == 'U') {
                level ++;
            } else if (c == 'D') {
                level --;
            }
            if (level == 0 && c == 'U') {
                count++;
            }
        }
        return count;
    }
}
