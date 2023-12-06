public interface AbstractBinarySearchTree<E extends Comparable<E>> {
    void insert(E element);
    boolean contains(E element);
    AbstractBinarySearchTree<E> search(E element);
    BinarySearchTree.Node<E> getRoot();
    BinarySearchTree.Node<E> getLeft();
    BinarySearchTree.Node<E> getRight();
    E getValue();
}