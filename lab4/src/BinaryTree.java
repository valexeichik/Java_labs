public class BinaryTree<T extends Comparable<T>> {
    Node root;
    BinaryTree() { root = null; }
    BinaryTree(T root) {
        this.root = new Node(root);
    }
    BinaryTree(T[] elements) {
        for (T element: elements) {
            if (element == null) {
                throw new IllegalArgumentException("Error! Check your array!");
            }
            this.add(element);
        }
    }

    class Node {
        T value;
        Node left;
        Node right;

        Node(T value_) {
            this.value = value_;
            left = right = null;
        }
    }

    public boolean contains(T value) {
        return containsNode(root, value);
    }
    boolean containsNode(Node current, T value) {
        while (current != null) {
            if (value.compareTo(current.value) == 0) {
                return true;
            }
            else if (value.compareTo(current.value) < 0) {
                current = current.left;
            }
            else {
                current = current.right;
            }
        }

        return false;
    }

    public void add(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Error! Please put valid argument into the tree!");
        }

        Node current = root;
        Node parent;

        if (root == null) {
            root = new Node(value);
        }
        else {
            while (true) {
                parent = current;
                if (parent.value.compareTo(value) == 0) {
                    return;
                } else if (parent.value.compareTo(value) > 0) {
                    current = parent.left;
                    if (current == null) {
                        parent.left = new Node(value);
                        return;
                    }
                } else {
                    current = parent.right;
                    if (current == null) {
                        parent.right = new Node(value);
                        return;
                    }
                }
            }
        }
    }
    public void nlrTraversal() {
        nodeLeftRight(root);
        System.out.println();
    }
    public void lrnTraversal() {
        leftRightNode(root);
        System.out.println();
    }
    public void lnrTraversal() {
        leftNodeRight(root);
        System.out.println();
    }
    void nodeLeftRight(Node nlr) {
        if (nlr == null) {
            return;
        }
        System.out.println(nlr.value);
        nodeLeftRight(nlr.left);
        nodeLeftRight(nlr.right);
    }
    void leftRightNode(Node lrn) {
        if (lrn == null) {
            return;
        }
        leftRightNode(lrn.left);
        leftRightNode(lrn.right);
        System.out.println(lrn.value);
    }
    void leftNodeRight(Node lnr) {
        if (lnr == null) {
            return;
        }
        leftNodeRight(lnr.left);
        System.out.println(lnr.value);
        leftNodeRight(lnr.right);
    }

    public void delete(T value)
    {
        if (root == null) {
            throw new IllegalArgumentException("Error! Empty tree!");
        }

        Node current = root;
        Node parent = root;
        boolean isRight = false;

        while (value != current.value) {
            parent = current;
            if (current.value.compareTo(value) > 0) {
                isRight = false;
                current = current.left;
            }
            else {
                isRight = true;
                current = current.right;
            }

            if (current == null) {
                throw new IllegalArgumentException("Error! There is no such element!");
            }
        }

        if (current.left == null && current.right == null)  {
            if (current == root)
                root = null;

            if (isRight)
                parent.right = null;
            else
                parent.left = null;
        }
        else if (current.left == null) {
            if (current == root)
                root = current.right;

            if (isRight)
                parent.right = current.right;
            else
                parent.left = current.right;
        }
        else if (current.right == null) {
            if (current == root)
                root = current.left;

            if (isRight)
                parent.right = current.left;
            else
                parent.left = current.left;
        }
        else {
            Node newParent = current;
            Node newNode = current;
            Node newCurrent = current.right;

            while (newCurrent != null) {
                newParent = newNode;
                newNode = newCurrent;
                newCurrent = newCurrent.left;
            }

            if (newNode != current.right) {
                newParent.left = newNode.right;
                newNode.right = current.right;
            }

            newNode.left = current.left;
            if (current == root)
                root = newNode;

            if (isRight)
                parent.right = newNode;
            else
                parent.left = newNode;
        }
    }
}
