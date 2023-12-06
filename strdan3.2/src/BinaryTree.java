import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;

// Определение интерфейса AbstractBinaryTree
interface AbstractBinaryTree<E> {
    E getKey();
    AbstractBinaryTree<E> getLeft();
    AbstractBinaryTree<E> getRight();
    void setLeft(AbstractBinaryTree<E> left);
    void setRight(AbstractBinaryTree<E> right);
    void setKey(E key);
    String asIndentedPreOrder(int indent);
    List<AbstractBinaryTree<E>> preOrder();
    List<AbstractBinaryTree<E>> inOrder();
    List<AbstractBinaryTree<E>> postOrder();
    List<AbstractBinaryTree<E>> depthFirstSearch();
    List<AbstractBinaryTree<E>> breadthFirstSearch();
    void forEachInOrder(Consumer<E> consumer);
}

// Реализация класса BinaryTree, который реализует интерфейс AbstractBinaryTree
public class BinaryTree<E> implements AbstractBinaryTree<E> {
    private E key;
    private AbstractBinaryTree<E> left;
    private AbstractBinaryTree<E> right;

    // Конструктор для создания узла дерева с заданным ключом
    public BinaryTree(E key) {
        this.key = key;
        this.left = null;
        this.right = null;
    }

    // Реализация методов интерфейса AbstractBinaryTree
    @Override
    public E getKey() {
        return key;
    }

    @Override
    public AbstractBinaryTree<E> getLeft() {
        return left;
    }

    @Override
    public AbstractBinaryTree<E> getRight() {
        return right;
    }

    @Override
    public void setKey(E key) {
        this.key = key;
    }

    public void setLeft(AbstractBinaryTree<E> left) {
        this.left = left;
    }

    public void setRight(AbstractBinaryTree<E> right) {
        this.right = right;
    }

    // Обход дерева в PreOrder и возврат строки с отступами
    @Override
    public String asIndentedPreOrder(int indent) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            result.append("  ");
        }
        result.append(getKey()).append("\n");
        if (getLeft() != null) {
            result.append(getLeft().asIndentedPreOrder(indent + 1));
        }
        if (getRight() != null) {
            result.append(getRight().asIndentedPreOrder(indent + 1));
        }
        return result.toString();
    }

    // Обход дерева в PreOrder и возврат списка узлов
    @Override
    public List<AbstractBinaryTree<E>> preOrder() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();
        result.add(this);   // Добавление текущего узла в список
        if (getLeft() != null) {
            result.addAll(getLeft().preOrder());  // Рекурсивный обход левого поддерева
        }
        if (getRight() != null) {
            result.addAll(getRight().preOrder()); // Рекурсивный обход правого поддерева
        }
        return result;
    }

    // Обход дерева в порядке и возврат списка узлов
    @Override
    public List<AbstractBinaryTree<E>> inOrder() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();
        if (getLeft() != null) {
            result.addAll(getLeft().inOrder());  // Рекурсивный обход левого поддерева
        }
        result.add(this); // Добавление текущего узла в список
        if (getRight() != null) {
            result.addAll(getRight().inOrder());  // Рекурсивный обход правого поддерева
        }
        return result;
    }

    // Обход дерева в PostOrder и возврат списка узлов
    @Override
    public List<AbstractBinaryTree<E>> postOrder() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();
        if (getLeft() != null) {
            result.addAll(getLeft().postOrder());  // Рекурсивный обход левого поддерева
        }
        if (getRight() != null) {
            result.addAll(getRight().postOrder());  // Рекурсивный обход правого поддерева
        }
        result.add(this); // Добавление текущего узла в список
        return result;
    }

    @Override
    public List<AbstractBinaryTree<E>> depthFirstSearch() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();
        result.add(this);
        if (getLeft() != null) {
            result.addAll(getLeft().depthFirstSearch());
        }
        if (getRight() != null) {
            result.addAll(getRight().depthFirstSearch());
        }
        return result;
    }

    @Override
    public List<AbstractBinaryTree<E>> breadthFirstSearch() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();
        Queue<AbstractBinaryTree<E>> queue = new ArrayDeque<>();
        queue.add(this);

        while (!queue.isEmpty()) {
            AbstractBinaryTree<E> current = queue.poll();
            result.add(current);

            if (current.getLeft() != null) {
                queue.add(current.getLeft());
            }
            if (current.getRight() != null) {
                queue.add(current.getRight());
            }
        }

        return result;
    }

    // Применение Consumer для каждого узла In Order
    @Override
    public void forEachInOrder(Consumer<E> consumer) {
        if (getLeft() != null) {
            getLeft().forEachInOrder(consumer); // Рекурсивное применение Consumer к левому поддереву
        }
        consumer.accept(getKey());  // Применение Consumer к текущему узлу
        if (getRight() != null) {
            getRight().forEachInOrder(consumer);  // Рекурсивное применение Consumer к правому поддереву
        }
    }

    // Основной метод main для примера использования
    public static void main(String[] args) {
        // Пример создания бинарного дерева
        BinaryTree<Integer> tree = new BinaryTree<>(5);
        tree.setLeft(new BinaryTree<>(3));
        tree.setRight(new BinaryTree<>(8));
        tree.getLeft().setLeft(new BinaryTree<>(1));
        tree.getLeft().setRight(new BinaryTree<>(4));
        tree.getRight().setLeft(new BinaryTree<>(7));
        tree.getRight().setRight(new BinaryTree<>(9));

        // Вывод дерева в preOrder
        System.out.println("PreOrder:");
        tree.preOrder().forEach(node -> System.out.print(node.getKey() + " "));
        System.out.println();

        // Вывод дерева в inOrder
        System.out.println("InOrder:");
        tree.inOrder().forEach(node -> System.out.print(node.getKey() + " "));
        System.out.println();

        // Вывод дерева в postOrder
        System.out.println("PostOrder:");
        tree.postOrder().forEach(node -> System.out.print(node.getKey() + " "));
        System.out.println();

        // Вывод дерева в виде строки с отступами
        System.out.println("Indented PreOrder:");
        System.out.println(tree.asIndentedPreOrder(0));

        // Применение Consumer для печати в отсортированном порядке
        System.out.println("Print In Order:");
        tree.forEachInOrder(element -> System.out.print(element + " "));

        // Вывод дерева в глубину (DFS)
        System.out.println("\nDepth First Search:");
        tree.depthFirstSearch().forEach(node -> System.out.print(node.getKey() + " "));

        // Вывод дерева в ширину (BFS)
        System.out.println("\nBreadth First Search:");
        tree.breadthFirstSearch().forEach(node -> System.out.print(node.getKey() + " "));
    }
}
