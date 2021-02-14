package phase1.ds.tree.bst;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class Bst4Iterator<E> {
    private Node<E> root;

    public Bst4Iterator(Node<E> root) {
        this.root = root;
    }

    public ArrayList<Node<E>> traversal(int type, boolean loop) {
        ArrayList<Node<E>> list = null;;
        if (type == 1) {
            if (loop) {
                list = prevOrder(root);
            } else {
                list = prevOrder0(root);
            }
        } else if (type == 2) {
            if (loop) {
                list = midOrder(root);
            } else {
                list = midOrder0(root);
            }
        } else if (type == 3) {
            if (loop) {
                list = postOrder(root);
            } else {
                list = postOrder0(root);
            }
        } else if (type == 4) {
            if (loop) {
                list = levelOrder(root);
            } else {
                System.out.println("levelorder1");
                ArrayList<ArrayList<Node<E>>> list1 = levelOrder1(root);
                for (ArrayList<Node<E>> nodes : list1) {
                    System.out.println(nodes);
                }
                System.out.println();
                System.out.println("levelorder0");
                ArrayList<ArrayList<Node<E>>> list0 = levelOrder0(root);
                for (ArrayList<Node<E>> nodes : list0) {
                    System.out.println(nodes);
                }
            }
        }
        return list;
    }

    public ArrayList<Node<E>> prevOrder0(Node<E> node) {
        ArrayList<Node<E>> list = new ArrayList<>();
        return prevOrder0(node, list);
    }

    private ArrayList<Node<E>> prevOrder0(Node<E> node, ArrayList<Node<E>> list) {
        if (node == null) {
            return list;
        }
        list.add(node);
        prevOrder0(node.left, list);
        prevOrder0(node.right, list);
        return list;
    }

    // 根 前 后
    public ArrayList<Node<E>> prevOrder(Node<E> node) {
        ArrayList<Node<E>> list = new ArrayList<>();
        if (node == null) {
            return list;
        }
        Stack<Node<E>> stack = new Stack<>();
//        LinkedList<Node<E>> stack = new LinkedList<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            Node<E> x = stack.pop();
            list.add(x);
            if (x.right != null) {
                stack.push(x.right);
            }
            if (x.left != null) {
                stack.push(x.left);
            }
        }
        return list;
    }

    // 前 根 后
    public ArrayList<Node<E>> midOrder(Node<E> node) {
        ArrayList<Node<E>> list = new ArrayList<>();
        if (node == null) {
            return list;
        }
        LinkedList<Node<E>> stack = new LinkedList<>();
        Node<E> x = node;
        while (x != null || !stack.isEmpty()) {
            while (x != null) {
                stack.push(x);
                x = x.left;
            }
            if (!stack.isEmpty()) {
                Node<E> tmp = stack.pop();
                list.add(tmp);
                x = tmp.right;
            }
        }
        return list;
    }

    public ArrayList<Node<E>> midOrder0(Node<E> node) {
        ArrayList<Node<E>> list = new ArrayList<>();
        list = midOrder0(node, list);
        return list;
    }

    private ArrayList<Node<E>> midOrder0(Node<E> node, ArrayList<Node<E>> list) {
        if (node == null) {
            return list;
        }
        list = midOrder0(node.left, list);
        list.add(node);
        list = midOrder0(node.right, list);
        return list;
    }

    // 左 右 根
    public ArrayList<Node<E>> postOrder(Node<E> node) {
        ArrayList<Node<E>> list = new ArrayList<>();
        if (node == null) {
            return list;
        }
        LinkedList<Node<E>> stack = new LinkedList<>();
        Node<E> curr, prev = null;
        stack.push(node);
        while (!stack.isEmpty()) {
            curr = stack.peek();
            // 子节点被访问
            boolean childrenAccessed = prev != null && (prev == curr.left || prev == curr.right);
            if (curr.isLeaf() || childrenAccessed) {
                Node<E> x = stack.pop();
                list.add(x);
                prev = x;
            } else {
                if (curr.right != null) {
                    stack.push(curr.right);
                }
                if (curr.left != null) {
                    stack.push(curr.left);
                }
            }
        }
        return list;
    }

    public ArrayList<Node<E>> postOrder0(Node<E> node) {
        ArrayList<Node<E>> list = new ArrayList<>();
        list =  postOrder0(node, list);
        return list;
    }

    private ArrayList<Node<E>> postOrder0(Node<E> node, ArrayList<Node<E>> list) {
        if (node == null) {
            return list;
        }
        list = postOrder0(node.left, list);
        list = postOrder0(node.right, list);
        list.add(node);
        return list;
    }

    public ArrayList<Node<E>> levelOrder(Node<E> node) {
        ArrayList<Node<E>> list = new ArrayList<>();
        if (node == null) {
            return list;
        }
        LinkedList<Node<E>> q = new LinkedList<>();
        q.offer(node);
        while (!q.isEmpty()) {
            Node<E> x = q.poll();
            list.add(x);
            if (x.left != null) {
                q.offer(x.left);
            }
            if (x.right != null) {
                q.offer(x.right);
            }
        }
        return list;
    }

    public ArrayList<ArrayList<Node<E>>> levelOrder0(Node<E> node) {
        ArrayList<ArrayList<Node<E>>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        list = levelOrder0(1, node, list);
        return list;
    }

    public ArrayList<ArrayList<Node<E>>> levelOrder0(int level, Node<E> node, ArrayList<ArrayList<Node<E>>> list) {
        if (list.size() < level) {
            list.add(new ArrayList<>());
        }
        list.get(level - 1).add(node);
        if (node.left != null) {
            levelOrder0(level + 1, node.left, list);
        }
        if (node.right != null) {
            levelOrder0(level + 1, node.right, list);
        }
        return list;
    }

    public ArrayList<ArrayList<Node<E>>> levelOrder1(Node<E> node) {
        ArrayList<ArrayList<Node<E>>> list = new ArrayList<>();
        if (node == null) {
            return list;
        }
        int levelSize = 1;
        LinkedList<Node<E>> q = new LinkedList<>();
        q.offer(node);
        ArrayList<Node<E>> levelList = new ArrayList<>();
        while (!q.isEmpty()) {
            Node<E> x = q.poll();
            levelSize--;
            levelList.add(x);
            if (x.left != null) {
                q.offer(x.left);
            }
            if (x.right != null) {
                q.offer(x.right);
            }

            // new Level
            if (levelSize == 0) {
                levelSize = q.size();
                list.add(levelList);
                levelList = new ArrayList<>();
            }
        }
        return list;
    }

}
