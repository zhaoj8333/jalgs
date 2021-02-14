package phase1.ds.collection.linkedlist.single;

import java.util.LinkedList;

public class SingleLinkedListTest {
    public static void main(String[] args) {
        LinkedList<String> sll = new LinkedList<>();
        sll.add("A");
        sll.add("B");
        sll.add("C");
        sll.add("D");
        sll.add("E");

        String node = sll.getFirst();
    }
}
