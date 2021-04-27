package phase1.ds.tree.re.bst;

import phase1.ds.tree.re.Node;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.function.Function;

public class TreeTraverser<E> {

    private final Function<E, E> processor;

    public TreeTraverser(Function<E, E> processor) {
        this.processor = processor;
    }

    // root -> left -> right
    public void prevOrder(Node<E> root) {
        prevOrder0(root);
    }

    public void prevOrder0(Node<E> root) {
        if (Objects.isNull(root)) {
            return;
        }
        this.processor.apply(root.element);
        prevOrder0(root.left);
        prevOrder0(root.right);
    }

    // left -> root -> root
    public void midOrder(Node<E> root) {
        midOrder0(root);
    }

    public void midOrder0(Node<E> root) {
        if (Objects.isNull(root)) {
            return;
        }
        midOrder0(root.left);
        this.processor.apply(root.element);
        midOrder0(root.right);
    }

    // right -> root -> left
    public void postOrder(Node<E> root) {
        postOrder0(root);
    }

    public void postOrder0(Node<E> root) {
        if (Objects.isNull(root)) {
            return;
        }
        postOrder0(root.right);
        this.processor.apply(root.element);
        postOrder0(root.left);
    }

    public void levelOrder(Node<E> root) {
        Node<E> node = root;
        Queue<Node<E>> q = new LinkedList<>();
        q.offer(node);
        while (node != null && !q.isEmpty()) {
            node = q.poll();
            this.processor.apply(node.element);
            if (Objects.nonNull(node.left)) {
                q.add(node.left);
            }
            if (Objects.nonNull(node.right)) {
                q.add(node.right);
            }
        }
    }
}
