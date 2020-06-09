package ds.tree.bst;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author allen
 */
public class BinarySearchTreeAlgs<Key extends Comparable<Key>, Value> {
    private Node root;
    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int N;

        public  Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N   = N;
        }

        @Override
        public String toString() {
            return "[" + key + "-" + val + "-" + N + "]";
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        } else {
            return x.N;
        }
    }

    // =============================
    public Node put(Key key, Value val) {
        root = put(root, key, val);
        return root;
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) {
            return new Node(key, val, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, val);
        } else if (cmp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
        }
        x.N = size(x.left) + size(x.right) + 1;

        return x;
    }

    // =============================
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x.val;
        }
    }

    public void inOrder(Node x) {
        if (x == null) {
            return;
        }
        inOrder(x.left);
        StdOut.print(x.val + "  ");
        inOrder(x.right);
    }

    public static void main(String[] args) {
        BinarySearchTreeAlgs<Integer, Integer> bst = new BinarySearchTreeAlgs<>();
        Object x = bst.put(3, 33);
        System.out.println("x: " + x);
        bst.put(1,11);
        bst.put(2,22);
        bst.put(4,44);

//        StdOut.println("root: " + bst.root);
//        StdOut.println("root.left : " + bst.root.left);
//        StdOut.println("root.left.right: " + bst.root.left.right);
//        StdOut.println("root.right: " + bst.root.right);
        bst.inOrder(bst.root);
        StdOut.println();

        StdOut.println("get: " + bst.get(2));
    }
}
