package phase1.ds.collection.linkedlist.doubly;

/*
双向链表 由于 单向链表
 */
public class DoubleLinkedList<E> {
    private int size;
    private Node<E> first;
    private Node<E> last;

    public DoubleLinkedList() {
    }

    private static class Node<E> {
        E elem;
        Node<E> prev;
        Node<E> next;

        public Node(E elem, Node<E> prev, Node<E> next) {
            this.elem = elem;
            this.prev = prev;
            this.next = next;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return this.size;
    }

    public int indexOf(E elem) {
        Node x = first;
        int i = 0;
        if (elem == null) {
            Node node = x;
            while (node != null) {
                if (node.elem == null) {
                    return i;
                }
                node = node.next;
                i++;
            }
        } else {
            while (x != null) {
                if (elem.equals(x.elem)) {
                    return i;
                }
                x = x.next;
                i++;
            }
        }

        return -1;
    }

    private Node<E> node(int index) {
        Node<E> x;
        int mid = size >> 1;
        if (index <= mid) {
            x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
        } else {
            x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
        }
        return x;
    }

    public void add(E elem) {
        if (size == 0) {
            first = new Node<E>(elem, first, last);
            last  = first;
            size++;
        } else {
            add(size, elem);
        }
    }

    public void linkLast(E elem) {
        Node<E> prevLast = last;
        Node<E> node = new Node<E>(elem, prevLast, null);
        last = node;
        if (prevLast == null) {
            first = node;
        } else {
            prevLast.next = node;
        }
    }

    public void linkBefore(int index, E elem) {
        Node<E> curr = node(index);
        Node<E> prev = curr.prev;

        Node<E> newNode = new Node<>(elem, prev, curr);
        curr.prev = newNode;
        if (prev == null) {
            first = newNode;
        } else {
            prev.next = newNode;
        }
    }

    public void add(int index, E elem) {
        checkIndex(index);
        // size == 0  或 添加最后一个
        if (size == index) {
            linkLast(elem);
        } else {
            linkBefore(index, elem);
        }
        size++;
    }

    public E remove(int index) {
        checkLast(index);
        Node<E> node = node(index);
        Node<E> prev = node.prev;
        Node<E> next = node.next;
        if (prev == null) {  // index == 0
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

        return node.elem;
    }

    private void checkIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index越界");
        }
    }

    private void checkLast(int index) throws IndexOutOfBoundsException {
        if (index >= size) {
            throw new IndexOutOfBoundsException("index超出范围");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<E> node = first;
        while (node != null) {
            sb.append(node.elem.toString());
            if (node.next != null) {
                sb.append(", ");
            }
            node = node.next;
        }

        sb.append("]");

        return sb.toString();
    }

    public void clear() {
        first = null;
        last  = null;
    }

    public boolean contains(E elem) {
        return indexOf(elem) == -1;
    }

}

