package phase1.ds.collection.linkedlist.single;

public class MyLinkedList<E> {
    private int size;
    private Node first;
    private static class Node<E> {
        E elem;
        Node<E> next;

        public Node(Node next, E elem) {
            this.elem = elem;
            this.next = next;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public E get(int index) {
        checkIndex(index);
        return node(index).elem;
    }

    public E set(int index, E newVal) {
        checkIndex(index);

        Node<E> node = node(index);
        E old = node.elem;
        node.elem = newVal;

        return old;
    }

    public void add(E elem) {
        if (size == 0) {
            first = new Node<>(first, elem);
            size++;
        } else {
            add(size, elem);
        }
    }

    public void add(int index, E elem) {
        checkIndex(index);
        if (index == 0) {
            // first为null时表示整个链表为空，也是用该情况`
            first = new Node<>(first, elem);
        } else {
            Node prev = node(index - 1);
            Node next = prev.next;
            /**
             * 链接
             * 如果是最后一个时，也试用 next = null
             */
            prev.next = new Node<>(next, elem);
        }
        size++;
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
            /*
            for (Node node = x; node != null; node = node.next) {
                if (node.elem == null) {
                    return i;
                }
                i++;
            }*/
        } else {
            while (x != null) {
                if (elem.equals(x.elem)) {
                    return i;
                }
                x = x.next;
                i++;
            }
            /*
            for (Node node = x; node != null; node = node.next) {
                if (elem.equals(node.next)) {
                    return i;
                }
                i++;
            }
             */
        }

        return -1;
    }

    public E remove(int index) {
        checkIndex(index);
        checkLast(index);
        Node<E> curr = first;
        if (index == 0) {
            first = first.next;
            return curr.elem;
        }

        Node<E> prev = node(index - 1);
        curr = prev.next;
        prev.next = curr.next;
        if (index == size - 1) {
            prev.next = null;
        }
        return curr.elem;
    }

    public void clear() {
        first = null;

        // jdk1.2以后，gc收集器在
        // 可达性算法： 判断对象是否是垃圾的标准
        //     选取一个节点，作为gc roots顶点，其他对象或者引用去指向gc roots定点
        //     如果这些对象能够到达gc roots定点，这些对象则不是垃圾，反之就是
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<E> node = first;
        while (node != null) {
            sb.append(node.elem.toString()).append(", ");
            node = node.next;
        }
        sb.append("]");

        return sb.toString();
    }

    private Node<E> node(int index) {
        Node x = first;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    }

    private void checkLast(int index) throws IndexOutOfBoundsException {
        if (index >= size) {
            throw new IndexOutOfBoundsException("index超出范围");
        }
    }

    private void checkIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index超出范围: index " + index + ", size: " + size );
        }
    }

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
