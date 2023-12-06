public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(5);
        bst.insert(3);
        bst.insert(8);

        System.out.println("Содержит 3: " + bst.contains(3)); // true
        System.out.println("Содержит 6: " + bst.contains(6)); // false

        BinarySearchTree<Integer> searchResult = (BinarySearchTree<Integer>) bst.search(3);
        System.out.println("Корень результата поиска: " + searchResult.getRoot().value); // 3

        System.out.println("Бинарное дерево:");
        bst.printTree();
    }
}
