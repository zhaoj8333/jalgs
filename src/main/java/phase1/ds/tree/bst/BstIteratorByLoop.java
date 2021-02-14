package phase1.ds.tree.bst;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author allen
 */
public class BstIteratorByLoop<E> {

    private Node<E> root;

    public BstIteratorByLoop(E root) {
        this.root = (Node<E>)root;
    }

    public void traversal(int type, BinaryVisitor<E> visitor) {
        if (type == 1) {
            prevOrder(root, visitor);
        } else if (type == 2) {
            midOrder(root, visitor);
        } else if (type == 3) {
            postOrder(root, visitor);
        } else if (type == 4) {
            levelOrderByQueue(root, visitor);
        }
    }

    /**
     * 前序: 根 - 左 - 右
     * 使用栈进行前序遍历: 后进先出，右边后访问，先压入右边元素
     */
    private void prevOrder(Node<E> node, BinaryVisitor<E> visitor) {
        if (node == null) {
            return;
        }
        // java中栈的方式有两种实现： LinkedList, Vector
        LinkedList<Node<E>> stack = new LinkedList<>();
        // Stack<Node<E>> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            Node<E> x = stack.pop();
            visitor.visit(x);
            if (x.right != null) {
                stack.push(x.right);
            }
            if (x.left != null) {
                stack.push(x.left);
            }
        }
    }

    // 中序: 左 - 根 - 右
    private void midOrder(Node<E> node, BinaryVisitor<E> visitor) {
        if (node == null) {
            return;
        }
        Node<E> x = node;
        Stack<Node<E>> stack = new Stack<>();
        while (x != null || !stack.isEmpty()) {
            while (x != null) {
                stack.push(x);
                x = x.left;
            }
            if (!stack.isEmpty()) {
                Node<E> tmp = stack.pop();
                visitor.visit(tmp);
                x = tmp.right;
            }
        }
    }

    // 后序： 左 - 右 - 根
    private void postOrder(Node<E> node, BinaryVisitor<E> visitor) {
        if (node == null) {
            return;
        }
        LinkedList<Node<E>> stack = new LinkedList<>();
        Node<E> curr, prev = null;
        stack.push(node);
        while (!stack.isEmpty()) {
            curr = stack.peek();
            boolean childrenAccessed = prev != null && (prev == curr.left || prev == curr.right);
            if (curr.isLeaf() || childrenAccessed)  {
                curr = stack.pop();
                visitor.visit(curr);
                prev = curr;
            } else {
                if (curr.right != null) {
                    stack.push(curr.right);
                }
                if (curr.left != null) {
                    stack.push(curr.left);
                }
            }
        }
    }

    // 层序：
    private void levelOrderByQueue(Node<E> node, BinaryVisitor<E> visitor) {
        if (node == null) {
            return;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> x = queue.poll();
            visitor.visit(x);
            if (x.left != null) {
                queue.offer(x.left);
            }
            if (x.right != null) {
                queue.offer(x.right);
            }
        }
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
        if (node == null) {
            return list;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> x = queue.poll();
            list.add(x.element);
            if (x.left != null) {
                queue.offer(x.left);
            }
            if (x.right != null) {
                queue.offer(x.right);
            }
        }
        return list;
    }

}
