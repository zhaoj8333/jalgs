package phase1.practice.hacker_rank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RandomNumbers {
    public static void main(String[] args) {
//        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String str;
//        try {
//            while ((str = br.readLine()) != null) {
//                final boolean[] stu = new boolean[1001];
//                final StringBuilder sb = new StringBuilder();
//                final int n = Integer.parseInt(str);
//                for (int i = 0; i < n; i++) {
//                    stu[Integer.parseInt(br.readLine())] = true;
//                }
//                for (int i = 0; i < 1001; i++) {
//                    if (stu[i]) {
//                        sb.append(i).append("\n");
//                    }
//                }
//                sb.deleteCharAt(sb.length() - 1);
//                System.out.println(sb.toString());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        boolean[] arr = new boolean[100];
        int[] count = new int[1001];
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        try {
            while ((str = br.readLine()) != null) {
                final int n = Integer.parseInt(str);
                for (int i = 0; i < n; i++) {
                    arr[Integer.parseInt(br.readLine())] = true;
                }
                for (int i = 0; i < n; i++) {
                    
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
