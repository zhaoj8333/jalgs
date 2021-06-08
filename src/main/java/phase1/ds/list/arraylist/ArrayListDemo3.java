package phase1.ds.list.arraylist;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListDemo3 {
    public static void main(String[] args) {
        /*
            ConcurrentModificationException:
                Not only in multiple threads environment, ConcurrentModificationException can also happens in single thread environment
                Why? because it has broken the rule of not modifying a Collection during iteration

            Java uses a transient variable modCount, it keeps track of how many times a list is modified structurally

            Structural modifications are changing the size of the list, it may affects the progress of iteration so yield incorrect results
            Structural modifications:
                add();
                remove();

            Cause:
                the real course of ConcurrentModificationException is inconsistent modCount
                When iterating over ArrayList, then Iterator's next() method keep track of modCount, add or remove will change it and will
                not match the expected modCount, so, Iterator throws the ConcurrentModificationException
         */
        exceptionAppear();
    }

    private static void exceptionAppear() {
        ArrayList<String> list = new ArrayList<>();
        list.add("AAAA");
        list.add("BBBB");
        list.add("CCCC");
        list.add("DDDD");
        list.add("EEEE");
        list.add("FFFF");

        Iterator<String> itr = list.iterator();
        while (itr.hasNext()) {
            String next = itr.next();
//            if (next.equals("AAAA")) {
            // 此处无论next是谁，都会有并发修改异常抛出
//            if (next.equals("AAAA")) {
//                list.remove("AAAA");
//                list.remove("AAAA"); // itr的remove不会抛出
//                Iterator<String> itr1 = list.iterator();
//                while (itr1.hasNext()) {
//                    System.out.println(itr1.next());
//                }
//            }
            if (next.equals("EEEE")) {
                list.remove("EEEE"); // 不报错
                list.add("eeee");  // 报错
            }
//            if (next.equals("FFFF")) {
//                list.remove("FFFF");
//                list.add("ffff"); // 报错
//            }
        }

    }
}
