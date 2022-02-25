public class Array {

    private int[] data;
    private int size;

    public Array(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    public Array() {
        this(10);
    }

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
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

    public boolean isEmpty() {
        return size == 0;
    }

    public void addLast(int n) {
        add(size, n);
    }

    public void addFirst(int n){
        add(0, n);
    }

    public void add(int index, int n) {
        if (getSize() == getData().length) {
            throw new IllegalArgumentException("Add failed. Array is full.");
        }

        if (index < 0 || index > getSize()) {
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size");
        }

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = n;
        setSize(getSize() + 1);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
