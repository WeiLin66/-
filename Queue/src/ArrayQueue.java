/**
 * 隊列實現類
 */
public class ArrayQueue<E> implements Queue<E> {
    private Array<E> array;

    public ArrayQueue(int capacity) {
        array = new Array<>(capacity);
    }

    public ArrayQueue() {
        array = new Array<>();
    }

    /**
     * 元素入對操作
     *
     * @param value 指定元素
     */
    @Override
    public void enqueue(E value) {
        array.addLast(value);
    }

    /**
     * 元素出隊操作
     *
     * @return 刪除並返回隊首元素
     */
    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    /**
     * 獲取隊首元素
     *
     * @return 隊首元素
     */
    @Override
    public E getFront() {
        return array.getFirst();
    }

    /**
     * 獲取當前隊列元素長度
     *
     * @return 當前隊列元素個數
     */
    @Override
    public int getSize() {
        return array.getSize();
    }

    /**
     * 返回當前類中Array陣列總長
     *
     * @return Array陣列總長度
     */
    public int getCapacity() {
        return array.getCapacity();
    }

    /**
     * 判斷隊列是否為空
     *
     * @return 若為空則返回true，否則返回false
     */
    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     * 重寫toString()方法
     *
     * @return 返回ArrayQueue字串信息
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Data length: %d, Capacity: %d\n", array.getSize(), array.getCapacity()));
        res.append("Queue: Front [");
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.getData()[i]);
            if (i != array.getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("] Tail");
        return res.toString();
    }

}
