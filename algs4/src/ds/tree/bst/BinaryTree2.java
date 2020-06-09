package ds.tree.bst;

import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;
import java.util.Queue;

/**
 * properties:
 *     1. maximum number of nodes at level l is 2^l
 *     2. maximum nubmer of nodes of height of h is 2^h - 1
 *     3. a binary tree with N nodes, minimum possible level is Log2(N + 1)
 *     4. a binary tree with N leaves has at least  levels
 */
public class BinaryTree2 {

    private Node root;
    private Node temp = root;

    private static class Node {
        int key;
        Node left, right;
        public Node(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }
    }

    public static void inorder(Node temp) {
        if (temp == null) return;
        inorder(temp.left);
        StdOut.print(temp.key + "  ");
        inorder(temp.right);
    }

    public static void insert(Node temp, int key) {
        Queue<Node> q = new LinkedList<>();
        q.offer(temp);
        // do level order traversal until we find an empty place
        while (!q.isEmpty()) {
            temp = q.poll();
            StdOut.println();
            StdOut.print("key: " + temp.key);
            if (temp.left == null) {
                temp.left = new Node(key);
                break;
            } else {
                q.offer(temp.left);
            }
            if (temp.right == null) {
                temp.right = new Node(key);
                break;
            } else {
                q.offer(temp.right);
            }
        }
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(11);
        root.left.left = new Node(7);
        root.right = new Node(9);
        root.right.left = new Node(15);
        root.right.right = new Node(8);
        inorder(root);

        insert(root, 12);
        insert(root, 13);
        insert(root, 14);
        StdOut.println();
        inorder(root);
    }
}
