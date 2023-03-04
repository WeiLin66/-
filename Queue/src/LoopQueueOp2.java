/**
 * 循環隊列實現類優化
 * 優化目標: 不使用屬性size
 */

public class LoopQueueOp2<E> implements Queue<E> {
    private E[] data;
    private int front, tail;

    public LoopQueueOp2(int capacity) {
        front = 0;
        tail = 0;
        data = (E[]) new Object[capacity + 1];
    }

    public LoopQueueOp2() {
        this(10);
    }

    /**
     * 元素入隊操作
     * 使用front, tail判斷是否需要擴容
     * @param value 指定元素
     */
    @Override
    public void enqueue(E value) {
        data[tail] = value;
        tail = (tail + 1) % data.length;
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2 + 1);
        }
    }

    /**
     * 元素出隊操作
     *
     * @return 刪除並返回隊首元素
     */
    @Override
    public E dequeue() {
        if (getSize() == 0) {
            throw new IllegalArgumentException("Size is empty");
        }
        E rmd = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        if (getSize() < getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return rmd;
    }

    /**
     * 獲取隊首元素
     *
     * @return 隊首元素
     */
    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Size is empty");
        }
        return data[front];
    }

    /**
     * 獲取當前隊列元素長度
     * 使用front, tail判斷當前佇列大小，有兩種狀況:
     * 1. tail位置大於等於front: 相減即可
     * 2. tail位置小於front: 涉及循環操作
     *
     * @return 當前隊列元素個數
     */
    @Override
    public int getSize() {
        return (tail - front >= 0) ? (tail - front) : (tail - front + data.length);
    }

    /**
     * 返回當前類中Array陣列總長
     *
     * @return Array陣列總長度
     */
    public int getCapacity() {
        return data.length - 1;
    }

    /**
     * 判斷隊列是否為空
     *
     * @return 若為空則返回true，否則返回false
     */
    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    /**
     * 對原本的data進行擴大以及縮小操作，因為在元素添加過程中，可能會產生容量不足或浪費，因此該方法會將data重新定義成
     * 長度為capacity的陣列
     *
     * @param capacity 指定陣列長度
     */
    private void resize(int capacity) {
        E[] newDate = (E[]) new Object[capacity + 1];
        for (int i = 0; i < getSize(); i++) {
            newDate[i] = data[(front + i) % data.length];
        }
        this.data = newDate;
        tail = getSize();
        front = 0;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Size: %d, Capacity: %d\n", getSize(), getCapacity()));
        res.append("Circular Queue Front [");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            if ((i + 1) % data.length != tail) {
                res.append(", ");
            }
        }
        res.append("] Tail");
        return res.toString();
    }
}
