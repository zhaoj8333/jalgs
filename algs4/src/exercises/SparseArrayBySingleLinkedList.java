package exercises;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Iterator;

public class SparseArrayBySingleLinkedList {
    public static void main(String[] args) {
        ex1();
    }

    private static void ex1() {
        int[][] array = new int[10][10];
        array[0][1] = 2;
        array[1][9] = 3;
        array[2][0] = 8;
        array[3][3] = 5;
        array[4][2] = 9;
        array[5][2] = 2;
        array[8][1] = 3;
        array[9][4] = 8;
        show(array);
        System.out.println("compress===================");
        SingleLinkedList<Tuple> list = compress1(array);
        System.out.println("decompress=================");
        int[][] arr = decompress1(list);
        show(arr);
    }

    private static class SingleLinkedList<E> implements Iterable<E> {
        private int size;
        private Node<E> first;

        public Node<E> getFirst() {
            return first;
        }

        @Override
        public Iterator<E> iterator() {
            return new ListIterator<E>();
        }

        private class ListIterator<E> implements Iterator<E> {
            private Node<E> curr = (Node<E>) first;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public E next() {
                Node<E> node = curr;
                curr = curr.next;

                return node.elem;
            }
        }

        private static class Node<E> {
            E elem;
            Node<E> next;

            public Node(E elem, Node<E> next) {
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

        public void clear() {
            first = null;
            size  = 0;
        }

        public E get(int index) {
            checkIndex(index);
            return node(index).elem;
        }

        public void add(E elem) {
            add(elem, size);
        }

        public void add(E elem, int index) {
            checkIndex(index);
            if (index == 0) {
                first = new Node<E>(elem, first);
            } else {
                Node<E> prev = node(index - 1);
                Node<E> curr = prev.next;
                prev.next = new Node<>(elem, curr);
            }
            size++;
        }

        public E remove(int index) throws IndexOutOfBoundsException {
            checkIndex(index);
            if (index >= size) {
                throw new IndexOutOfBoundsException("越界");
            }

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

        public void checkIndex(int index) throws IndexOutOfBoundsException {
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException("越界: index " + index + ", size: " + size);
            }
        }

        public Node<E> node(int index) {
            Node<E> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }

            return x;
        }
    }

    private static class Tuple {
        private int lineNumber;
        private int columnNumber;
        private int value;

        public Tuple(int lineNumber, int columnNumber, int value) {
            this.lineNumber = lineNumber;
            this.columnNumber = columnNumber;
            this.value = value;
        }

        public void setLineNumber(int lineNumber) {
            this.lineNumber = lineNumber;
        }

        public void setColumnNumber(int columnNumber) {
            this.columnNumber = columnNumber;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getLineNumber() {
            return lineNumber;
        }

        public int getColumnNumber() {
            return columnNumber;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Tuple {" +
                    "line=" + lineNumber +
                    ", column=" + columnNumber +
                    ", value=" + value +
                    '}' + "\n";
        }
    }

    private static SingleLinkedList<Tuple> compress1(int[][] arr) {
        int sum = 0;
        for (int[] ints : arr) {
            for (int anInt : ints) {
                if (anInt > 0) {
                    sum++;
                }
            }
        }
        SingleLinkedList<Tuple> sll = new SingleLinkedList<>();
        sll.add(new Tuple(arr.length, arr[0].length, sum));
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] > 0) {
                    Tuple tuple = new Tuple(i, j, arr[i][j]);
                    sll.add(tuple);
                }
            }
        }

        return sll;
    }

    private static int[][] decompress1(SingleLinkedList<Tuple> list) {
//        StdOut.println(list);
        int[][] ret = null;
        int index = 0;
        for (Tuple tuple : list) {
            if (index == 0) {
                ret = new int[tuple.getLineNumber()][tuple.getColumnNumber()];
            } else {
                ret[tuple.getLineNumber()][tuple.getColumnNumber()] = tuple.getValue();
            }
            index++;
        }

        return ret;
    }

    private static void show(int[][] array) {
        for (int[] ints : array) {
            StdOut.println(Arrays.toString(ints));
        }
    }

}
