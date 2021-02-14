package phase1.ds.map;

import java.util.*;
import java.util.Map;

public class LinkedHashMapDemo {
    public static void main(String[] args) {
//        mapOrder();
//        linkedOrder();

    }

    private static void linkedOrder() {
        final LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("d", 4);
        map.put("e", 5);
        map.put("r", 6);
        System.out.println(map);
        final Set<Map.Entry<String, Integer>> entries = map.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + " -> " + entry.getValue() + " ");
        }
    }

    private static void mapOrder() {
        final HashMap<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("d", 4);
        map.put("e", 5);
        map.put("r", 6);
        System.out.println(map);
        final Set<Map.Entry<String, Integer>> entries = map.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + " -> " + entry.getValue() + " ");
        }
        System.out.println();
        final Collection<Integer> values = map.values();
        for (Integer value : values) {
            System.out.println(value);
        }
    }
}
