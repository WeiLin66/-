public class MaxHeap<T extends Comparable<T>> {
    private Array<T> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap(T[] arr) {
        data = new Array<>(arr);
        for (int i = parent(arr.length - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    public MaxHeap() {
        data = new Array<>();
    }

    public int size() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * 添加元素
     *
     * @param value
     */
    public void add(T value) {
        data.addLast(value);
        siftUP(data.getSize() - 1);
    }

    /**
     * 取出最大值
     *
     * @return
     */
    public T findMax() {
        return data.get(0);
    }

    /**
     * 取出最大值並刪除元素
     *
     * @return
     */
    public T extractMax() {
        T ret = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);

        return ret;
    }

    /**
     * 取出最大值，並替會成元素value
     *
     * @param value
     * @return
     */
    public T replace(T value) {
        T ret = findMax();
        data.set(0, value);
        siftDown(0);
        return ret;
    }

    /**
     * 返回節點的父親節點
     *
     * @param index
     * @return
     */
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("root doesn't have parent!");
        }
        return (index - 1) / 2;
    }

    /**
     * 返回該節點的左子節點
     *
     * @param index
     * @return
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * 返回該節點的右子節點
     *
     * @param index
     * @return
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    /**
     * 向上調整節點(適用於add操作)
     * 每次和父節點比較大小，若大於父節點則交換
     *
     * @param index
     */
    private void siftUP(int index) {
        while (index > 0 && data.get(index).compareTo(data.get(parent(index))) > 0) {
            data.swap(index, parent(index));
            index = parent(index);
        }
    }

    /**
     * 向下調整(適用於extractMax)
     * 每次向左右子節點中的最大值比較，若小於則與其交換
     *
     * @param index
     */
    private void siftDown(int index) {
        while (leftChild(index) < data.getSize()) {
            int i = leftChild(index);
            if (i + 1 < data.getSize() && data.get(i + 1).compareTo(data.get(i)) > 0) {
                i = rightChild(index);
            }

            if (data.get(index).compareTo(data.get(i)) >= 0) {
                break;
            }

            data.swap(index, i);
            index = i;
        }
    }

}
