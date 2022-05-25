/**
 * 自定義動態字串類Array
 */
public class Array<E> {

    private E[] data;
    private int size;

    public Array(int capacity) {
        data = (E[]) new Object[capacity]; // 強制轉換成範型陣列，因為語法無法直接 new E[n]
        size = 0;
    }

    public Array() {
        this(10);
    }

    public E[] getData() {
        return data;
    }

    public void setData(E[] data) {
        this.data = data;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCapacity() {
        return data.length;
    }

    /**
     * 判斷陣列是否為空
     *
     * @return 若為空返回true，反之返回false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 獲取指定index的元素，若輸入參數不合理則拋出異常
     *
     * @param index 陣列index
     * @return 返回指定元素
     */
    public E get(int index) {
        if (index < 0 || index >= getSize()) {
            throw new IllegalArgumentException("Illegal argument!");
        }

        E getData;
        try {
            getData = data[index];
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Illegal type!");
        }
        return getData;
    }

    /**
     * 設定指定index元素，若輸入參數不合理則拋出異常
     *
     * @param index 陣列index
     * @param value 指定元素值
     */
    public void set(int index, E value) {
        if (index < 0 || index >= getSize()) {
            throw new IllegalArgumentException("Illegal argument!");
        }

        try {
            data[index] = value;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Illegal type!");
        }
    }

    /**
     * 查看陣列是否包含元素value
     *
     * @param value 欲查詢元素
     * @return 若包含value則返回true，反之返回false
     */
    public boolean contains(E value) {
        for (E i : data) {
            if (i.equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查詢value所在index
     *
     * @param value 欲查詢元素
     * @return 返回value的index
     */
    public int find(E value) {
        for (int i = 0; i < getSize(); i++) {
            if (get(i).equals(value)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 移除指定index元素
     *
     * @param index 指定元素index
     * @return 返回被移除的元素
     */
    public E remove(int index) {
        if (index < 0 || index >= getSize()) {
            throw new IllegalArgumentException("Illegal argument!");
        }

        /* 緩處理機制，防止複雜度震盪 */
        if (getSize() == getData().length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }

        E removeData = data[index];

        for (int i = index + 1; i < getSize(); i++) {
            data[i - 1] = data[i];
        }
        setSize(getSize() - 1);
        data[getSize()] = null; // loitering Object
        return removeData;
    }

    /**
     * 移除陣列第一個元素
     *
     * @return 返回被移除的元素值
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 移除陣列的最後一個元素
     *
     * @return 返回被移除的元素值
     */
    public E removeLast() {
        return remove(getSize() - 1);
    }

    /**
     * 移除指定的元素value
     *
     * @param value 欲移除元素值
     */
    public boolean removeElement(E value) {
        if (!contains(value)) {
            return false;
        }

        remove(find(value));
        return true;
    }


    /**
     * 添加指定元素到陣列尾部
     *
     * @param value 欲添加元素
     */
    public void addLast(E value) {
        add(size, value);
    }

    /**
     * 添加指定元素到陣列頭
     *
     * @param value 欲添加元素
     */
    public void addFirst(E value) {
        add(0, value);
    }

    /**
     * 將元素value添加到陣列index位置
     *
     * @param index 陣列中的位置
     * @param value 欲添加元素值
     */
    public void add(int index, E value) {
        if (index < 0 || index > getSize()) {
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size");
        }

        if (getSize() == getData().length) {
            resize(data.length * 2);
        }

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = value;
        setSize(getSize() + 1);
    }

    /**
     * 重新分配data空間，並將原有數據複製到新空間中
     *
     * @param capacity 調整後的data陣列長度
     */
    private void resize(int capacity) {
        E[] newDate = (E[]) new Object[capacity];
        if (getSize() >= 0) System.arraycopy(data, 0, newDate, 0, getSize());
        this.data = newDate;
    }

    /**
     * 重寫的toString()方法，打印Array陣列中的消息
     *
     * @return 返回字串型式消息
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d\n", getSize(), getCapacity()));
        res.append('[');
        for (int i = 0; i < getSize(); i++) {
            res.append(data[i]);
            if (i != getSize() - 1) {
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();
    }
}
