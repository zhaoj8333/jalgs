package ds.collection.linkedlist;

public class MyLinkedListTest {
    public static void main(String[] args) {
        MyLinkedList<String> link = new MyLinkedList<>();
        link.add("aaaa");
        link.add("bbbb");
        link.add("cccc");
        link.add(1, "A");
        link.add(2, "B");
        link.add(3, "C");
        link.add(4, "D");
        link.add(5, "E");

        System.out.println(link);
//        System.out.println(link.indexOf("B"));
//        System.out.println(link.remove(0));
//        System.out.println(link.size());
//        System.out.println(link.remove(8));
//        System.out.println(link.remove(7));
        System.out.println(link);
    }
}
