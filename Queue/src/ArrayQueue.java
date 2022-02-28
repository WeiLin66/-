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
     *
     * @param value
     */
    @Override
    public void enqueue(E value) {
        array.addLast(value);
    }

    /**
     *
     * @return
     */
    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    /**
     *
     * @return
     */
    @Override
    public E getFront() {
        return array.getFirst();
    }

    /**
     *
     * @return
     */
    @Override
    public int getSize() {
        return array.getSize();
    }

    /**
     *
     * @return
     */
    public int getCapacity(){
        return array.getCapacity();
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Data length: %d, Capacity: %d\n", array.getData().length, array.getCapacity()));
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
