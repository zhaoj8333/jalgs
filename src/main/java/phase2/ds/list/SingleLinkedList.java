package phase2.ds.list;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SingleLinkedList<E> implements List<E> {

    private static class Node<E> {

        public E elem;

        public Node<E> next;

        @Override
        public String toString() {
            if (Objects.isNull(elem)) {
                return null;
            }
            return elem.toString();
        }

        public Node(E elem, Node<E> next) {
            this.elem = elem;
            this.next = next;
        }
    }

    private int size;

    private Node<E> head = new Node<>(null, null);

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 || head.next == null;
    }

    @Override
    public boolean contains(E elem) {
        if (Objects.isNull(elem) || this.isEmpty()) {
            return false;
        }
        Node<E> curr = this.head.next;
        while (!Objects.isNull(curr.next)) {
            if (Objects.equals(curr.elem, elem)) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    @Override
    public void add(E elem) {
        addLast(elem);
    }

    private void nullCheck(E elem) {
        if (Objects.isNull(elem)) {
            throw new IllegalArgumentException("elem can't be null");
        }
    }

    @Override
    public void addFirst(E elem) {
        nullCheck(elem);
        Node<E> newNode = new Node<>(elem, null);
        newNode.next = this.head;
        this.head = newNode;
        size++;
    }

    @Override
    public void addLast(E elem) {
        nullCheck(elem);
        Node<E> node = this.head;
        while (node.next != null) {
            node = node.next;
        }
        node.next = new Node<>(elem, null);
        size++;
    }

    @Override
    public E remove(E elem) {
        if (this.isEmpty()) {
            throw new NoSuchElementException("LinedList is empty");
        }
        this.nullCheck(elem);
        Node<E> prev = this.head;
        Node<E> curr = prev.next;
        while (!Objects.isNull(curr.next)) {
            if (Objects.equals(curr.elem, elem)) {
                break;
            }
            prev = curr;
            curr = curr.next;
        }
        prev.next = curr.next;
        size--;
        return curr.elem;
    }

    public E removeFirst() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("LinkedList is empty");
        }
        Node<E> head = this.head;
        Node<E> first = head.next;
        head.next = first.next;
        size--;
        return first.elem;
    }

    @Override
    public E get(int index) {
        Node<E> node = getNode(index);
        return node.elem;
    }

    private Node<E> getNode(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index can't lower than 0 or bigger than the size");
        }
        Node<E> node = this.head.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public E first() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("LinkedList is empty");
        }
        return this.head.next.elem;
    }

    @Override
    public E last() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("LinedList is empty");
        }
        Node<E> node = this.head.next;
        Node<E> prev = this.head;
        while (!Objects.isNull(node.next)) {
            prev = node;
            node = node.next;
        }
        return prev.elem;
    }

    @Override
    public void set(int index, E newElem) {
        Node<E> node = this.getNode(index);
        node.elem = newElem;
    }

    @Override
    public int indexOf(E elem) {
        if (this.isEmpty()) {
            throw new NoSuchElementException("LinkedList is empty");
        }
        if (Objects.isNull(elem)) {
            throw new NoSuchElementException("No null in this linkedlist");
        }
        int index = 0;
        Node<E> curr = this.head.next;
        while (!Objects.isNull(curr.next)) {
            if (Objects.equals(curr.elem, elem)) {
                break;
            }
            curr = curr.next;
            index++;
        }
        return index;
    }

    @Override
    public E[] toArray() {
        E[] array = (E[]) new Object[this.size];
        Node<E> node = this.head;
        int index = 0;
        while (!Objects.isNull(node.next)) {
            array[index++] = node.elem;
            node = node.next;
        }
        return array;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("SingleLinkedList { \n\tsize: " + size + ", \n\telements: [");
        if (this.isEmpty()) {
            str.append(']');
            return str.toString();
        }
        Node<E> node = this.head.next;
        while (!Objects.isNull(node) && !Objects.isNull(node.elem)) {
            str.append(node.elem.toString());
            if (!Objects.isNull(node.next)) {
                str.append(", ");
            }
            node = node.next;
        }
        str.append("]\n}\n");
        return str.toString();
    }

    public static void main(String[] args) {
        SingleLinkedList<Character> linkedList = new SingleLinkedList<>();
        for (int i = 0; i < 10; i++) {
//            linkedList.add((char) ('A' + i));
            linkedList.addFirst((char) ('A' + i));
        }
        System.out.println(linkedList);

//        linkedList.removeFirst();
//        System.out.println(linkedList);
//        linkedList.remove('C');
//        System.out.println(linkedList);
//        System.out.println(linkedList.contains(null));
//        System.out.println(linkedList.contains('a'));
//        Object[] characters = linkedList.toArray();
//        System.out.println(Arrays.toString(characters));
//        int c = linkedList.indexOf('C');
//        System.out.println("indexof C is : " + c);
//        System.out.println("first: " + linkedList.first());
//        System.out.println("last: " + linkedList.last());
//        System.out.println("i: " + linkedList.get(8));

//        linkedList.set(8, 'a');
//        System.out.println(linkedList);
    }
}
