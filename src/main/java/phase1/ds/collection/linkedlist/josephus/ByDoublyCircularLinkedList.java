package phase1.ds.collection.linkedlist.josephus;

/**
 * @author allen
 */
public class ByDoublyCircularLinkedList<E> {
    private Node<E> first;
    private Node<E> last;
    public Node<E> current;
    private int size;

    public ByDoublyCircularLinkedList() {
    }

    public int getSize() {
        return size;
    }

    public static class Node<E> {
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
            return  " (" + prev + " <- " + item + " -> " + next + ") ";
        }
    }

    public E getFirst() {
        return first.item;
    }

    public E getLast() {
        return last.item;
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

    public E remove(Node<E> node) {
        int index = indexOf(node.item);
        return remove(index);
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

    public E next() {
        if (current == null) {
            return null;
        }
        current = current.next;
        return current.item;
    }

    public void reset() {
        current = first;
    }

    public E remove() {
        if (current == null) {
            return null;
        }
        Node<E> next = current.next;
        E res = remove(current);
        if (size == 0) {
            current = null;
        } else {
            current = next;
        }
        return res;
    }

    public static void main(String[] args) {
        ByDoublyCircularLinkedList<String> dll = new ByDoublyCircularLinkedList<>();
        dll.add("A");
        dll.add("B");
        dll.add("C");
        dll.add("D");
        dll.add("E");
        dll.add("F");
        dll.add("G");
        System.out.println(dll);

        dll.reset();

        while (!dll.isEmpty()) {
            int steps = 3;
            for (int i = 0; i < steps - 1; i++) {
                dll.next();
                dll.next();
            }
            System.out.println(dll.remove());
        }

        System.out.println(dll);
    }
}


