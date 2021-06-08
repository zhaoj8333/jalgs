package phase1.ds.list.linkedlist.doubly;

/**
 * @author allen
 */
public class DoublyLinkedList<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;
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
        curr.prev = node;
        if (prev == null) {
            first = node;
        } else {
            prev.next = node;
        }
    }

    public void linkLast(E item) {
        Node<E> prev = last;
        Node<E> node = new Node<>(item, prev, null);
        last = node;
        if (prev == null) {
            first = node;
        } else {
            prev.next = node;
        }
    }

    public E remove(int index) {
        checkIndex(index);
        if (index == size) {
            throw new IndexOutOfBoundsException("越界");
        }
        Node<E> curr = node(index);
        Node<E> prev = curr.prev;
        Node<E> next = curr.next;
        if (prev == null) {
            first = next;
            next.prev = null;
        } else {
            prev.next = next;
        }
        if (next == null) {
            last = prev;
            prev.next = null;
        } else {
            next.prev = prev;
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
            return "[ ]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        Node<E> curr = first;
        while (curr != null) {
            sb.append(curr.item.toString()).append(", ");
            curr = curr.next;
        }
        sb.append(" ]");
        return sb.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public static void main(String[] args) {
        DoublyLinkedList<String> dll = new DoublyLinkedList<>();
        dll.add("AAA");
        dll.add("BBB");
        dll.add("CCC");
        dll.add("DDD");
        dll.add("EEE");
        dll.add("FFF");
//        dll.remove(0);
//        dll.remove(5);
//        dll.add(3, "a");
        dll.remove(3);
        dll.remove(0);
//        dll.remove(0);
//        dll.remove(4);
        dll = null;
    }
}


