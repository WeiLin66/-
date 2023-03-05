public class MyLinkedList<E> {

    private class Node {
        public E value;
        public Node next;

        public Node(E value, Node next) {
            this.value = value;
            this.next = next;
        }

        public Node(E value) {
            this(value, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return value.toString();
        }

    }

    private Node dummyHead;
    private int size;

    public MyLinkedList() {
        dummyHead = new Node();
        setSize(0);
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    /**
     * 添加節點至鏈表
     *
     * @param index 插入位置
     * @param value 節點數值
     */
    public void add(int index, E value) { // index 1, 2, 3
        if (index < 0 || index > getSize()) {
            throw new IllegalArgumentException("wrong index!");
        }
        Node pre = dummyHead;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        pre.next = new Node(value, pre.next);
        setSize(getSize() + 1);
    }

    /**
     * 將節點插入至節點頭部
     *
     * @param value 節點數值
     */
    public void addFirst(E value) {
        add(0, value);
    }

    /**
     * 將節點插入鏈表尾部
     *
     * @param value
     */
    public void addLast(E value) {
        add(getSize(), value);
    }

    /**
     * 取指定節點數值
     *
     * @param index 節點位置
     * @return 節點數值
     */
    public E get(int index) {
        if (index < 0 || index >= getSize()) {
            throw new IllegalArgumentException("Illegal index");
        }
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.value;
    }

    /**
     * 取頭節點數值
     *
     * @return 頭節點數值
     */
    public E getFront() {
        return get(0);
    }

    /**
     * 取尾節點數值
     *
     * @return 尾節點數值
     */
    public E getLast() {
        return get(getSize() - 1);
    }

    /**
     * 設置指定節點數值
     *
     * @param index 節點位置
     * @param value 節點數值
     */
    public void set(int index, E value) {
        if (index < 0 || index >= getSize()) {
            throw new IllegalArgumentException("Illegal index");
        }
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.value = value;
    }

    /**
     * 是否包含數值為value的節點
     *
     * @param value 要搜尋的節點數值
     * @return 若有包含則返回真
     */
    public boolean contains(E value) {
        for (Node cur = dummyHead.next; cur != null; cur = cur.next) {
            if (cur.value.equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 刪除指定節點
     *
     * @param index 節點位置
     * @return 被刪除的節點數值
     */
    public E remove(int index) {
        if(isEmpty()){
            throw new IllegalArgumentException("LinkedList is empty");
        }else if (index < 0 || index >= getSize()) {
            throw new IllegalArgumentException("Illegal index");
        }

        Node pre = dummyHead;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        Node cur = pre.next;
        pre.next = cur.next;
        cur.next = null;
        setSize(getSize() - 1);
        return cur.value;
    }

    /**
     * 刪除頭節點
     *
     * @return 被刪除節點數值
     */
    public E removeFront() {
        return remove(0);
    }

    /**
     * 刪除尾節點
     *
     * @return 被刪除節點數值
     */
    public E removeLast() {
        return remove(getSize() - 1);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("LinkedList [");
        Node n = dummyHead;
        for (int i = 0; i < getSize(); i++) {
            n = n.next;
            res.append("(" + n.value + ")");
            res.append("--> ");
        }
        res.append("NULL]");

        return res.toString();
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
        }
        for (int i = 5; i < 10; i++) {
            linkedList.addLast(i);
        }
        linkedList.set(9, 666);
        System.out.println(linkedList);
        System.out.println(linkedList.contains(666));
        System.out.println(linkedList.remove(9));
        System.out.println(linkedList);
    }
}
