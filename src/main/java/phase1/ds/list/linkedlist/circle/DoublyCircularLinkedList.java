package phase1.ds.list.linkedlist.circle;

/**
 * @author allen
 */
public class DoublyCircularLinkedList<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;

    public int getSize() {
        return size;
    }

    private static class Node<E> {
        E item;
        Node<E> prev;
        Node<E> next;

        public Node(E item, Node<E> prev, Node<E> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "item=" + item +
                    ", prev=" + prev +
                    ", next=" + next +
                    '}';
        }
    }

    public Node<E> getFirst() {
        return first;
    }

    public Node<E> getLast() {
        return last;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E item) {
        add(size, item);
    }

    public void add(int index, E item) {
        // 追加元素,包括size == 0 时的场景
        if (index == size) {
            linkLast(item);
        } else {
            linkBefore(index, item);
        }
        size++;
    }

    public void linkBefore(int index, E item) {
        Node<E> curr = node(index);
        Node<E> prev = curr.prev;
        Node<E> node = new Node<>(item, prev, curr);
        prev.next = node;
        curr.prev = node;
        if (index == 0) { // curr == first
            Node<E> last = node(size - 1);
            last.prev = node;
            first = node;
        }
    }

    public void linkLast(E item) {
        Node<E> prev = last;
        last = new Node<>(item, prev, first);
        // 首次添加时 prev == null
        if (prev == null) {
            first = last;
        } else {
            prev.next = last;
        }
        first.prev = last;
    }

    public E remove(int index) {
        checkIndex(index);
        if (index == size) {
            throw new IndexOutOfBoundsException("越界");
        }
        Node<E> curr = node(index);
        Node<E> prev = curr.prev;
        Node<E> next = curr.next;
        prev.next = next;
        next.prev = prev;

        if (index == 0) { // curr == first
            first = next;
        }
        if (index == size - 1) { // curr == last
            last = prev;
        }
        if (size == 1) {
            first = null;
            last  = null;
        }
        size--;
        return curr.item;
    }

    private void checkIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("越界");
        }
    }

    public int indexOf(E elem) {
        Node<E> x = first;
        int index = 0;
        if (elem == null) {
            Node<E> node = x;
            while (node != null) {
                if (node.item == null) {
                    return index;
                }
                node = node.next;
                index++;
            }
        } else {
            Node<E> node = x;
            while (node != null) {
                if (elem.equals(node.item)) {
                    return index;
                }
                node = node.next;
                index++;
            }
        }
        return -1;
    }

    private Node<E> node(int index) {
        Node<E> node;
        int half = size >> 1;
        if (index <= half) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }

        return node;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[  ]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        Node<E> curr = first;
        do {
            sb.append(curr.item.toString());
            if (curr.next != first) {
                sb.append(", ");
            }
            curr = curr.next;
        } while (curr != first && size > 0);
        sb.append(" ]");

        return sb.toString();
    }

    public static void main(String[] args) {
        DoublyCircularLinkedList<String> dll = new DoublyCircularLinkedList<>();
        dll.add("AAA");
        dll.add("BBB");
        dll.add("CCC");
        dll.add("DDD");
        dll.add("EEE");
        dll.add("FFF");
//        dll.add(3, "III");
//        dll.add(0, "JJJ");

        dll.remove(0);
        dll.remove(0);
        dll.remove(0);
        dll.remove(0);
        dll.remove(0);
        System.out.println(dll.remove(0));
//        dll.remove(dll.getSize() - 1);

//        dll.remove(0);
        System.out.println(dll);
        System.out.println(dll.getFirst());
        System.out.println(dll.getLast());
    }
}


