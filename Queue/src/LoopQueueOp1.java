/**
 * 循環隊列實現類優化
 * 優化目標: 循環佇列的front, tail在判斷是否為滿
 * 時不浪費多餘的一個空間
 */

public class LoopQueueOp1<E> implements Queue<E> {
    private E[] data;
    private int front, tail;
    private int size;

    public LoopQueueOp1(int capacity) {
        front = 0;
        tail = 0;
        data = (E[]) new Object[capacity];
    }

    public LoopQueueOp1() {
        this(10);
    }

    /**
     * 元素入隊操作
     * 判斷佇列是否為滿的依據是當前size是否等於data.length
     *
     * @param value 指定元素
     */
    @Override
    public void enqueue(E value) {
        data[tail] = value;
        tail = (tail + 1) % data.length;
        size++;
        if (getSize() == getCapacity()) {
            resize(getCapacity() * 2);
        }
    }

    /**
     * 元素出隊操作
     * 出隊後若當前大小小於capacity的1/4，則進行縮容
     * 縮容需要避免size為1的狀況
     *
     * @return 刪除並返回隊首元素
     */
    @Override
    public E dequeue() {
        if (getSize() == 0) {
            throw new IllegalArgumentException("Size is empty");
        }
        E rmd = data[front];
        size--;
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
     *
     * @return 當前隊列元素個數
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * 返回當前類中Array陣列總長
     *
     * @return Array陣列總長度
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * 判斷隊列是否為空
     * 直接使用size大小判斷佇列是否為空
     *
     * @return 若為空則返回true，否則返回false
     */
    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }

    /**
     * 對原本的data進行擴大以及縮小操作，因為在元素添加過程中，可能會產生容量不足或浪費，因此該方法會將data重新定義成
     * 長度為capacity的陣列
     *
     * @param capacity 指定陣列長度
     */
    private void resize(int capacity) {
        E[] newDate = (E[]) new Object[capacity];
        for (int i = 0; i < getSize(); i++) {
            newDate[i] = data[(front + i) % data.length];
        }
        this.data = newDate;
        front = 0;
        tail = getSize();
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
        for (int i = 0; i < getSize(); i++) {
            res.append(data[(front + i) % data.length]);
            if ((i + 1) != getSize()) {
                res.append(", ");
            }
        }
        res.append("] Tail");
        return res.toString();
    }
}
