import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class RemoveDuplicates {
    // Класс BinarySearchTree реализует бинарное дерево поиска
    public static class BinarySearchTree<E extends Comparable<E>> {
        private Node<E> root;
        private List<E> insertionOrder;

        // Класс Node представляет узел дерева
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

        // Метод для вывода дерева в терминал
        public void printTree() {
            printTreeRec(root, 0);
        }

        // Вспомогательный метод для рекурсивного вывода дерева (in-order traversal)
        private void printTreeRec(Node<E> node, int depth) {
            if (node != null) {
                printTreeRec(node.rightChild, depth + 1);

                // Выводим отступы для каждого уровня глубины
                for (int i = 0; i < depth; i++) {
                    System.out.print("    ");
                }

                // Выводим значение узла
                System.out.println(node.value);

                printTreeRec(node.leftChild, depth + 1);
            }
        }



        // Метод для вставки элемента в бинарное дерево поиска
        public void insert(E element) {
            root = insertRec(root, element);
        }

        private Node<E> insertRec(Node<E> root, E element) {
            if (root == null) {
                return new Node<>(element);
            }

            int compareResult = element.compareTo(root.value);

            if (compareResult < 0) {
                root.leftChild = insertRec(root.leftChild, element);
            } else if (compareResult > 0) {
                root.rightChild = insertRec(root.rightChild, element);
            }

            // После вставки проверяем баланс и перебалансируем при необходимости
            int balance = getBalance(root);

            // Левое поддерево выше, перебалансируем вправо
            if (balance > 1 && element.compareTo(root.leftChild.value) < 0) {
                return rotateRight(root);
            }

            // Правое поддерево выше, перебалансируем влево
            if (balance < -1 && element.compareTo(root.rightChild.value) > 0) {
                return rotateLeft(root);
            }

            // Левое-правое
            if (balance > 1 && element.compareTo(root.leftChild.value) > 0) {
                root.leftChild = rotateLeft(root.leftChild);
                return rotateRight(root);
            }

            // Правое-левое
            if (balance < -1 && element.compareTo(root.rightChild.value) < 0) {
                root.rightChild = rotateRight(root.rightChild);
                return rotateLeft(root);
            }

            return root;
        }

        // Вспомогательный метод для определения баланса узла
        private int getBalance(Node<E> node) {
            if (node == null) {
                return 0;
            }

            return getHeight(node.leftChild) - getHeight(node.rightChild);
        }

        // Вспомогательный метод для получения высоты узла
        private int getHeight(Node<E> node) {
            if (node == null) {
                return 0;
            }

            return Math.max(getHeight(node.leftChild), getHeight(node.rightChild)) + 1;
        }

        // Вспомогательные методы для вращений
        private Node<E> rotateRight(Node<E> y) {
            Node<E> x = y.leftChild;
            Node<E> T2 = x.rightChild;

            x.rightChild = y;
            y.leftChild = T2;

            return x;
        }

        private Node<E> rotateLeft(Node<E> x) {
            Node<E> y = x.rightChild;
            Node<E> T2 = y.leftChild;

            y.leftChild = x;
            x.rightChild = T2;

            return y;
        }
        // Метод для получения уникальных слов из дерева
        public Set<E> getUniqueWords() {
            Set<E> uniqueWords = new HashSet<>();
            collectUniqueWords(root, uniqueWords);
            return uniqueWords;
        }

        // Вспомогательный метод для рекурсивного обхода дерева и сбора уникальных слов
        private void collectUniqueWords(Node<E> node, Set<E> uniqueWords) {
            if (node != null) {
                collectUniqueWords(node.leftChild, uniqueWords);
                uniqueWords.add(node.value);
                collectUniqueWords(node.rightChild, uniqueWords);
            }
        }
    }

    // Главный метод программы
    public static void main(String[] args) {
        // Входной текст
        String inputText = "кот мышь кот кошка медведь";

        // Используем StringTokenizer для разделения текста на слова
        StringTokenizer tokenizer = new StringTokenizer(inputText, " ");

        // Создаем экземпляр BinarySearchTree для хранения слов
        BinarySearchTree<String> bst = new BinarySearchTree<>();

        // Вставляем каждое слово в дерево
        while (tokenizer.hasMoreTokens()) {
            String word = tokenizer.nextToken();
            bst.insert(word);
        }

        // Получаем уникальные слова из дерева
        Set<String> uniqueWords = bst.getUniqueWords();

        // Строим строку с уникальными словами
        StringBuilder resultText = new StringBuilder();
        for (String word : uniqueWords) {
            resultText.append(word).append(" ");
        }
        // Выводим результат на экран
        System.out.println("Текст без дубликатов: " + resultText.toString().trim());

        // Выводим дерево в терминал
        System.out.println("Отсортированное дерево:");
        bst.printTree();
    }
}