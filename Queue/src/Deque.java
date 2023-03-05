/**
 * Double-ended-queue 雙端隊列
 */
public class Deque<E> {
    private E[] data;
    private int front;
    private int tail;
    private int size;

    public Deque(int capacity) {
        data = (E[]) new Object[capacity];
        front = 0;
        tail = 0;
    }

    public Deque() {
        this(10);
    }

    public int getFront() {
        return front;
    }

    public void setFront(int front) {
        this.front = front;
    }

    public int getTail() {
        return tail;
    }

    public void setTail(int tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    /**
     * 判斷雙端隊列是否為空
     *
     * @return 若為空則返回true，反之返回false
     */
    public boolean isEmpty() {
        return getSize() == 0;
    }

    /**
     * 獲取隊列空間總長度
     *
     * @return 屬性data長度
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * 向尾部添加元素
     *
     * @param value 指定元素
     */
    public void addLast(E value) {
        data[tail] = value;
        tail = (tail + 1) % getCapacity();
        setSize(getSize() + 1);
        if (getSize() == getCapacity()) {
            resize(getCapacity() * 2);
        }
    }

    /**
     * 像頭部添加元素
     *
     * @param value 指定元素
     */
    public void addFront(E value) {
        if (front != 0) {
            data[front - 1] = value;
            setFront(getFront() - 1);
        } else {
            data[getCapacity() - 1] = value;
            setFront(getCapacity() - 1);
        }
        setSize(getSize() + 1);
        if (getSize() == getCapacity()) {
            resize(getCapacity() * 2);
        }
    }

    /**
     * 刪除頭部元素
     *
     * @return 欲刪除元素
     */
    public E removeFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("deque is empty");
        }
        E reval = data[front];
        front = (front + 1) % getCapacity();
        setSize(getSize() - 1);
        if (getSize() == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return reval;
    }

    /**
     * 刪除尾部元素
     *
     * @return 欲刪除元素
     */
    public E removeLast() {
        if (isEmpty()) {
            throw new IllegalArgumentException("deque is empty");
        }
        E reval;
        if (tail != 0) {
            reval = data[tail - 1];
            setTail(getTail() - 1);
        } else {
            reval = data[getCapacity() - 1];
            setTail(getCapacity() - 1);
        }
        setSize(getSize() - 1);
        if (getSize() == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return reval;
    }

    /**
     * 對原本的data進行擴大以及縮小操作，因為在元素添加過程中，可能會產生容量不足或浪費，因此該方法會將data重新定義成
     * 長度為capacity的陣列
     *
     * @param capacity 指定陣列長度
     */
    public void resize(int capacity) {
        E[] newData = (E[]) new Object[capacity];
        for (int i = 0; i < getSize(); i++) {
            newData[i] = data[(front + i) % getCapacity()];
        }
        data = newData;
        setFront(0);
        setTail(getSize());
    }

    /**
     * 重寫的toString()方法，打印Deque中的消息
     *
     * @return 返回字串型式消息
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Size: %d, Capacity: %d\nFront: %d, Tail: %d\n", getSize(), getCapacity(), getFront(), getTail()));
        res.append("Deque Front [");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            if ((i + 1) % data.length != tail) {
                res.append(", ");
            }
        }
        res.append("] Tail");
        return res.toString();
    }

    public static void main(String[] args) {
        Deque<Integer> test = new Deque<>();
        for (int i = 0; i < 5; i++) {
            test.addLast(i + 1);
            System.out.println(test);
        }

        for (int i = 5; i < 10; i++) {
            test.addFront(i + 1);
            System.out.println(test);
        }

        for (int i = 0; i < 5; i++) {
            test.removeLast();
            System.out.println(test);
        }

        for (int i = 5; i < 10; i++) {
            test.removeFront();
            System.out.println(test);
        }

    }

}
