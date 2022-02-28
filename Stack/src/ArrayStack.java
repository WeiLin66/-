/**
 * Stack接口實現類
 */
public class ArrayStack<E> implements Stack<E> {
    private Array<E> array;

    public ArrayStack(int capacity) {
        array = new Array<>(capacity);
    }

    public ArrayStack() {
        this(10);
    }

    /**
     * 將一個元素入棧
     *
     * @param value 入棧元素
     */
    @Override
    public void push(E value) {
        array.addLast(value);
    }

    /**
     * 將一個元素出棧
     *
     * @return 棧頂元素
     */
    @Override
    public E pop() {
        return array.removeLast();
    }

    /**
     * 判斷棧是否為空
     *
     * @return 若為空返回true，反之則返回false
     */
    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     * 查看棧頂元素
     *
     * @return 返回棧頂元素
     */
    @Override
    public E peek() {
        return array.getLast();
    }

    /**
     * 獲取棧的長度
     *
     * @return 棧的長度
     */
    @Override
    public int getSize() {
        return array.getSize();
    }

    /**
     * 重寫toString()方法
     *
     * @return 返回ArrayStack字串信息
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: [");
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.getData()[i]);
            if (i != array.getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("] Top");
        return res.toString();
    }
}
