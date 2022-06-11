public class MinHeap<T extends Comparable<T>> {
    Array<T> data;

    public MinHeap() {
        data = new Array<>();
    }

    /**
     * heapify功能
     *
     * @param arr
     */
    public MinHeap(T[] arr) {
        data = new Array<>(arr);
        if(arr.length != 1) {
            for (int i = parent(arr.length - 1); i >= 0; i--) {
                siftDown(i);
            }
        }
    }

    public MinHeap(int capacity) {
        data = new Array<>(capacity);
    }

    /**
     * @param value
     */
    public void add(T value) {
        data.addLast(value);
        siftUp(size() - 1);
    }

    /**
     * @return
     */
    public T findMin() {
        if(size() == 0){
            throw new IllegalArgumentException("heap is empty");
        }
        return data.get(0);
    }

    /**
     * @return
     */
    public T extractMin() {
        T ret = findMin();
        data.swap(0, size() - 1);
        data.removeLast();
        siftDown(0);

        return ret;
    }

    /**
     * @return
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * @return
     */
    public int size() {
        return data.getSize();
    }

    /**
     * @param index
     * @return
     */
    public int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("root doesn't have parent!");
        }

        return (index - 1) / 2;
    }

    /**
     * @param index
     * @return
     */
    public int leftChild(int index) {
        return (index * 2) + 1;
    }

    /**
     * @param index
     * @return
     */
    public int rightChild(int index) {
        return (index * 2) + 2;
    }

    /**
     * 取出最小值，然後將最小值替換成value
     *
     * @param value
     */
    public T replace(T value) {
        T ret = findMin();
        data.set(0, value);
        return ret;
    }


    /**
     * @param index
     */
    public void siftUp(int index) {
        while (index > 0 && data.get(index).compareTo(data.get(parent(index))) < 0) {
            data.swap(index, parent(index));
            index = parent(index);
        }
    }

    /**
     * @param index
     */
    public void siftDown(int index) {
        while (leftChild(index) < size()) {
            int child = leftChild(index);

            if (child + 1 < size() && data.get(child).compareTo(data.get(child + 1)) > 0) {
                child += 1;
            }

            if (data.get(index).compareTo(data.get(child)) <= 0) {
                break;
            }

            data.swap(index, child);
            index = child;
        }
    }

}
