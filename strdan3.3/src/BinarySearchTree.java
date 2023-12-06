public class BinarySearchTree<E extends Comparable<E>> implements AbstractBinarySearchTree<E> {
    private Node<E> root;

    public static class Node<E> {
        public E value;
        public Node<E> leftChild;
        public Node<E> rightChild;

        public Node(E value) {
            this.value = value;
        }

        public Node(E value, Node<E> leftChild, Node<E> rightChild) {
            this.value = value;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
    }

    public void printTree() {
        printTree(root, 0);
    }

    private void printTree(Node<E> root, int depth) {
        if (root != null) {
            printTree(root.rightChild, depth + 1);
            for (int i = 0; i < depth; i++) {
                System.out.print("   "); // Отступ для отображения уровня
            }
            System.out.println(root.value);
            printTree(root.leftChild, depth + 1);
        }
    }

    @Override
    public void insert(E element) {
        root = insertRec(root, element);
    }

    private Node<E> insertRec(Node<E> root, E element) {
        if (root == null) {
            return new Node<>(element);
        }

        if (element.compareTo(root.value) < 0) {
            root.leftChild = insertRec(root.leftChild, element);
        } else if (element.compareTo(root.value) > 0) {
            root.rightChild = insertRec(root.rightChild, element);
        }

        return root;
    }

    @Override
    public boolean contains(E element) {
        return containsRec(root, element);
    }

    private boolean containsRec(Node<E> root, E element) {
        if (root == null) {
            return false;
        }

        int compareResult = element.compareTo(root.value);
        if (compareResult < 0) {
            return containsRec(root.leftChild, element);
        } else if (compareResult > 0) {
            return containsRec(root.rightChild, element);
        } else {
            return true;
        }
    }

    @Override
    public AbstractBinarySearchTree<E> search(E element) {
        Node<E> result = searchRec(root, element);
        if (result != null) {
            BinarySearchTree<E> resultTree = new BinarySearchTree<>();
            resultTree.root = result;
            return resultTree;
        } else {
            return null;
        }
    }


    private Node<E> searchRec(Node<E> root, E element) {
        if (root == null || root.value.equals(element)) {
            return root;
        }

        int compareResult = element.compareTo(root.value);
        if (compareResult < 0) {
            return searchRec(root.leftChild, element);
        } else {
            return searchRec(root.rightChild, element);
        }
    }

    @Override
    public Node<E> getRoot() {
        return root;
    }

    @Override
    public Node<E> getLeft() {
        return root != null ? root.leftChild : null;
    }

    @Override
    public Node<E> getRight() {
        return root != null ? root.rightChild : null;
    }

    @Override
    public E getValue() {
        return root != null ? root.value : null;
    }
}
