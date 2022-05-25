public class MaxHeap<T extends Comparable<T>> {
    private Array<T> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
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
     * @param index
     * @return
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * 返回該節點的右子節點
     * @param index
     * @return
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }

}
