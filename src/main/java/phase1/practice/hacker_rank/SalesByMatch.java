package phase1.practice.hacker_rank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalesByMatch {
    public static void main(String[] args) {
        int[] socks = new int[] {10, 20, 20, 10, 10, 30, 50, 10, 20};
        ArrayList<Integer> integers = new ArrayList<>();
        for (int sock : socks) {
            integers.add(sock);
        }
        int n = sockMerchant(integers.size(), integers);
        System.out.println("pairs: " + n);
    }

    private static int sockMerchant(int n, List<Integer> ar) {
        int result = 0;
        if (ar.isEmpty()) {
            return result;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer integer : ar) {
            if (!map.containsKey(integer)) {
                map.put(integer, 1);
            } else {
                Integer count = map.get(integer);
                map.put(integer, ++count);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer val = entry.getValue();
            if (val > 1) {
                result += (val / 2);
            }
        }
        return result;
    }
}
