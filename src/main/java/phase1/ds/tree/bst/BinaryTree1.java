package phase1.ds.tree.bst;

/**
 * trees are hierarchical data structures
 *     trees:
 *     1. provide moderate access/phase1.algs.search, quicker than linkedlist but slower than array
 *     2. provide moderate insertion/deletion(quicker than array but slower than linkedlist)
 *     3. do not have an upper limit on number of nodes and nodes are linked using pointers
 *
 * application:
 *     hierarchical data, phase1.algs.search, sorted list, router phase1.algs, decision making
 */

public class BinaryTree1 {
    Node root;
    public BinaryTree1(int key) {
        root = new Node(key);
    }

    public BinaryTree1() {
    }

    private static class Node {
        int key;
        Node left, right;

        public Node(int item) {
            this.key = item;
            left = right = null;
        }
    }

    public static void main(String[] args) {
        BinaryTree1 bt1 = new BinaryTree1(1);
        bt1.root = new Node(0);
        bt1.root.left = new Node(1);
        bt1.root.right = new Node(2);
    }

}
