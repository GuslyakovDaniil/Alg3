import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

// Реализация класса BinaryTree, реализующего интерфейс AbstractBinaryTree
public class BinaryTree<E> implements AbstractBinaryTree<E> {
    private E key;
    private AbstractBinaryTree<E> left;
    private AbstractBinaryTree<E> right;

    // Конструктор для создания BinaryTree с заданным ключом
    public BinaryTree(E key) {
        this.key = key;
        this.left = null;
        this.right = null;
    }

    // Получение ключа
    @Override
    public E getKey() {
        return key;
    }

    // Получение левого поддерева
    @Override
    public AbstractBinaryTree<E> getLeft() {
        return left;
    }

    // Получение правого поддерева
    @Override
    public AbstractBinaryTree<E> getRight() {
        return right;
    }

    // Установка ключа
    @Override
    public void setKey(E key) {
        this.key = key;
    }

    // Установка левого поддерева
    public void setLeft(AbstractBinaryTree<E> left) {
        this.left = left;
    }

    // Установка правого поддерева
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
        result.add(this);  // Добавление текущего узла в список
        if (getLeft() != null) {
            result.addAll(getLeft().preOrder());  // Рекурсивный обход левого поддерева
        }
        if (getRight() != null) {
            result.addAll(getRight().preOrder());  // Рекурсивный обход правого поддерева
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
        result.add(this);  // Добавление текущего узла в список
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
        result.add(this);  // Добавление текущего узла в список
        return result;
    }

    // Применение Consumer для каждого узла In Order
    @Override
    public void forEachInOrder(Consumer<E> consumer) {
        if (getLeft() != null) {
            getLeft().forEachInOrder(consumer);  // Рекурсивное применение Consumer к левому поддереву
        }
        consumer.accept(getKey());  // Применение Consumer к текущему узлу
        if (getRight() != null) {
            getRight().forEachInOrder(consumer);  // Рекурсивное применение Consumer к правому поддереву
        }
    }

    // Основной метод для тестирования BinaryTree
    public static void main(String[] args) {
        // Пример использования
        BinaryTree<Integer> tree = new BinaryTree<>(5);
        tree.setLeft(new BinaryTree<>(3));
        tree.setRight(new BinaryTree<>(8));
        tree.getLeft().setLeft(new BinaryTree<>(1));
        tree.getLeft().setRight(new BinaryTree<>(4));
        tree.getRight().setLeft(new BinaryTree<>(7));
        tree.getRight().setRight(new BinaryTree<>(9));

        // Вывод дерева в PreOrder
        System.out.println("PreOrder:");
        tree.preOrder().forEach(node -> System.out.print(node.getKey() + " "));
        System.out.println();

        // Вывод дерева в InOrder
        System.out.println("InOrder:");
        tree.inOrder().forEach(node -> System.out.print(node.getKey() + " "));
        System.out.println();

        // Вывод дерева в PostOrder
        System.out.println("PostOrder:");
        tree.postOrder().forEach(node -> System.out.print(node.getKey() + " "));
        System.out.println();

        // Вывод дерева в виде строки с отступами
        System.out.println("Indented PreOrder:");
        System.out.println(tree.asIndentedPreOrder(0));

        // Применение Consumer для Print In Order:
        System.out.println("Print In Order:");
        tree.forEachInOrder(element -> System.out.print(element + " "));
    }
}

