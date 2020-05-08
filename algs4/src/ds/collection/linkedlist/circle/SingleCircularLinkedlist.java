package ds.collection.linkedlist.circle;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * @author allen
 */
public class SingleCircularLinkedlist<E> implements Iterable<E> {

    private Node<E> first;
    private int size;
    public Node<E> getFirst() {
        return first;
    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    public static class Node<E> {
        public E data;
        public Node<E> next;

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{data = '" + data + '}';
        }
    }

    public boolean add(E data) {
        return add(data, size);
    }

    public boolean add(E data, int index) {
        checkIndex(index);
        if (index == 0) {
            Node<E> newFirst = new Node<>(data, first);
            // 已经生成了一个节点，last已经比原来多了一个
            // node之前一定不要修改first
            Node<E> last = (size == 0) ? newFirst : node(size - 1);
            last.next = newFirst;
            first = newFirst;
        } else {
            Node<E> prev = node(index - 1);
            prev.next = new Node<>(data, prev.next);
        }

        size++;
        return true;
    }

    public E remove(int index) {
        checkIndex(index);
        checkLast(index);
        Node<E> curr = first;

        if (size == 1) {
            first = null;
        }
        if (index == 0) {
            if (size == 1) {
//                first = null;
            } else {
                // 以下代码，当只有1个的时候，无法删除
                Node<E> last = node(size - 1);
                first = first.next;
                // 以上两句不可改变顺序 !!!!!!!!!!!!!!!!
                last.next = first;
            }
        } else {
            Node<E> prev = node(index - 1);
            curr = prev.next;
            prev.next = curr.next;
        }
        size--;
        return (E) curr.data;
    }

    public Node<E> node(int index) {
        checkIndex(index);
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    private void checkLast(int index) throws IndexOutOfBoundsException {
        if (index >= size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
    }

    private void checkIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<E> node = first;
        if (node != null) {
            do {
                sb.append(node.data.toString());
                if (node.next != first) {
                    sb.append(", ");
                }
                node = node.next;
            } while (node != first);
        }
        sb.append("]");

        return sb.toString();
    }

    public static void main(String[] args) {
        SingleCircularLinkedlist<String> scll = new SingleCircularLinkedlist<>();
        scll.add("AAA");
        scll.add("BBB");
        scll.add("CCC");
        scll.add("DDD");
        scll.add("EEE");
        scll.add("FFF");
        scll.add("GGG");
        scll.add("HHH");
        scll.add("III");
        scll.add("JJJ", 0);
        scll.add("LLL", 4);
        scll.add("KKK");
        StdOut.println(scll);
        StdOut.println("size: " + scll.size);
        scll.remove(3);
        StdOut.println(scll);
        scll.remove(6);
        StdOut.println(scll);
        StdOut.println();

//        System.out.println(scll);
        SingleCircularLinkedlist<Integer> il = new SingleCircularLinkedlist<>();
        il.add(1);
        il.add(3);
        il.add(5);
        il.add(8);
        il.add(10);
//        StdOut.println(maxByWhile(il.first));
//        max = il.first.data;
//        StdOut.println(maxByRecursion(il.first, il.first));
        il.remove(4);
//        il.remove(3);
//        il.remove(2);
//        il.remove(1);
        il.remove(0);
        il.remove(0);
        il.remove(0);
        il.remove(0);
//        il.remove(0);
        StdOut.println(il);

    }

    private static Integer max;

    public static Integer maxByRecursion(Node<Integer> node, Node<Integer> first) {
        node = node.next;
        if (node == first) {
            return max;
        }
        if (node.data > max) {
            max = node.data;
        }
        return maxByRecursion(node, first);
    }

    public static Integer maxByWhile(Node<Integer> first) {
        Integer max = first.data;
        Node<Integer> curr = first;
        do {
            if (curr.data > max) {
                max = curr.data;
            }
            curr = curr.next;
        } while (curr != first);

        return max;
    }
}