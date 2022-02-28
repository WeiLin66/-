/**
 *
 */

import java.util.Arrays;

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
     *
     * @param value
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
     *
     * @return
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
     *
     * @return
     */
    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Size is empty");
        }
        return data[front];
    }

    /**
     *
     * @return
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     *
     * @return
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }

    /**
     *
     * @param capacity
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
