import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

// Определение интерфейса AbstractBinaryTree
interface AbstractBinaryTree<E> {
    E getKey();  // Получение ключа узла
    AbstractBinaryTree<E> getLeft();  // Получение левого поддерева
    AbstractBinaryTree<E> getRight();  // Получение правого поддерева
    void setLeft(AbstractBinaryTree<E> left);  // Установка левого поддерева
    void setRight(AbstractBinaryTree<E> right);  // Установка правого поддерева
    void setKey(E key);  // Установка ключа
    String asIndentedPreOrder(int indent);  // Вывод в виде строки с отступами в пре-порядке
    List<AbstractBinaryTree<E>> preOrder();  // Обход в пре-порядке
    List<AbstractBinaryTree<E>> inOrder();  // Обход в порядке
    List<AbstractBinaryTree<E>> postOrder();  // Обход в пост-порядке
    void forEachInOrder(Consumer<E> consumer);  // Применение Consumer для узлов в порядке
}