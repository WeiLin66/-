public class SegmentTree<T> {

    private T[] tree;
    private T[] data;
    private Merger<T> merger;

    public SegmentTree(T[] arr, Merger<T> merger) {
        this.merger = merger;
        data = (T[]) new Object[arr.length];

        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }

        tree = (T[]) new Object[arr.length * 4];
        buildSegmentTree(0, 0, data.length - 1);
    }

    /**
     * 在treeIndex位置創建表示區間[l...r]的線段樹
     *
     * @param treeIndex
     * @param l
     * @param r
     */
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        int mid = l + (r - l) / 2;

        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public int getSize() {
        return data.length;
    }

    public T get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal!");
        }
        return data[index];
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    /**
     * 搜尋操作
     *
     * @param queryL
     * @param queryR
     * @return
     */
    public T query(int queryL, int queryR) {
        if (queryL < 0 || queryR >= data.length) {
            throw new IllegalArgumentException("No such Interval!");
        }
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    private T query(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (queryL > mid) { // 區間在mid右側，不能等於，因為mid屬於左子節點範疇
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) { // 區間在mid左側，可以等於，因為mid屬於左子節點範疇
            return query(leftTreeIndex, l, mid, queryL, queryR);
        }

        /* 區間被mid切分 */
        T leftRes = query(leftTreeIndex, l, mid, queryL, mid);
        T rightRes = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);

        return merger.merge(leftRes, rightRes);
    }

    /**
     * 更新操作
     * @param index
     * @param val
     */
    public void set(int index, T val) {
        if (index < 0 || index >= tree.length) {
            throw new IllegalArgumentException("Wrong index!");
        }

        set(0, 0, data.length - 1, index, val);
    }

    private void set(int treeIndex, int l, int r, int index, T val) {
        if (l == r) {
            data[index] = val;
            tree[treeIndex] = val;
            return;
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (index >= mid + 1) {
            set(rightTreeIndex, mid + 1, r, index, val);
        } else {
            set(leftTreeIndex, l, mid, index, val);
        }

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('[');

        for (int i = 0; i < tree.length; i++) {
            if (tree[i] == null) {
                res.append("null");
            } else {
                res.append(tree[i]);
            }

            if (i != tree.length - 1) {
                res.append(", ");
            }
        }

        res.append(']');
        return res.toString();
    }
}
