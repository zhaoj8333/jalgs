package ds.tree.bst;

import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

/**
 * @author allen
 */
public class BstIteratorByRecursion<E> {

    private Node<E> root;

    public BstIteratorByRecursion(E root) {
        this.root = (Node<E>) root;
    }

    public void traversal(int type, BinaryVisitor<E> visitor) {
        // 前序: 根 - 左 - 右
        if (type == 1) {
            prevOrder(root, visitor);
            // 中序: 左 - 根 - 右
        } else if (type == 2) {
            midOrder(root, visitor);
            // 后序： 左 - 右 - 根
        } else if (type == 3) {
            postOrder(root, visitor);
            // 层序：
        } else if (type == 4) {
            levelOrder(root, visitor);
        }
    }

    private void prevOrder(Node<E> node, BinaryVisitor<E> visitor) {
        if (node == null) {
            return;
        }
        visitor.visit(node);
        prevOrder(node.left, visitor);
        prevOrder(node.right, visitor);
    }

    public void midOrder(Node<E> node, BinaryVisitor<E> visitor) {
        if (node == null) {
            return;
        }
        midOrder(node.left, visitor);
        visitor.visit(node);
        midOrder(node.right, visitor);
    }

    public void postOrder(Node<E> node, BinaryVisitor<E> visitor) {
        if (node == null) {
            return;
        }
        postOrder(node.left, visitor);
        postOrder(node.right, visitor);
        visitor.visit(node);
    }

    public int depth(Node<E> node) {
        if (node == null) {
            return 0;
        }
        int left  = depth(node.left);
        int right = depth(node.right);
        return Math.max(left, right) + 1;
    }

    /**
     * 层序遍历 的递归方式 复杂度很高
     */
    public void levelOrder(Node<E> node, BinaryVisitor<E> visitor) {
        if (node == null) {
            return;
        }
        int depth = depth(node);
        for (int i = 1; i <= depth; i++) {
            levelOrder(node, visitor, i);
        }
    }

    private void levelOrder(Node<E> node, BinaryVisitor<E> visitor, int level) {
        if (node == null || level < 1) {
            return;
        }
        if (level == 1) {
            visitor.visit(node);
            return;
        }
        levelOrder(node.left, visitor, level - 1);
        levelOrder(node.right, visitor, level - 1);
    }

    public ArrayList<E> traversal(int type) {
        // 前序: 根 - 左 - 右
        if (type == 1) {
            return prevOrder(root);
            // 中序: 左 - 根 - 右
        } else if (type == 2) {
            return midOrder(root);
            // 后序： 左 - 右 - 根
        } else if (type == 3) {
            return postOrder(root);
            // 层序：
        } else if (type == 4) {
            return levelOrder(root);
        }

        return null;
    }

    private ArrayList<E> list = new ArrayList<>();

    private ArrayList<E> prevOrder(Node<E> node) {
        if (node == null) {
            return list;
        }
        list.add(node.element);
        prevOrder(node.left);
        prevOrder(node.right);

        return list;
    }

    public ArrayList<E> midOrder(Node<E> node) {
        if (node == null) {
            return list;
        }
        midOrder(node.left);
        list.add(node.element);
        midOrder(node.right);

        return list;
    }

    public ArrayList<E> postOrder(Node<E> node) {
        if (node == null) {
            return list;
        }
        postOrder(node.left);
        postOrder(node.right);
        list.add(node.element);
        return list;
    }

    public ArrayList<E> levelOrder(Node<E> node) {
        return list;
    }
}
