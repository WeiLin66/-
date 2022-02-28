/**
 *
 */

import java.util.Arrays;

public class LoopQueue<E> implements Queue<E> {
    private E[] data;
    private int front, tail;
    private int size;

    public LoopQueue(int capacity) {
        front = 0;
        tail = 0;
        data = (E[]) new Object[capacity + 1];
    }

    public LoopQueue() {
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
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2 + 1);
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
        return data.length - 1;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    /**
     *
     * @param capacity
     */
    private void resize(int capacity) {
        E[] newDate = (E[]) new Object[capacity + 1];
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
