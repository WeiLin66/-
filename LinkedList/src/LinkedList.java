public class LinkedList<E> {

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

    public LinkedList() {
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

    public void add(int index, E value) {
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

    public void addFirst(E value) {
        add(0, value);
    }

    public void addLast(E value) {
        add(getSize(), value);
    }

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

    public E getFront() {
        return get(0);
    }

    public E getLast() {
        return get(getSize() - 1);
    }

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

    public boolean contains(E value) {
        for (Node cur = dummyHead.next; cur != null; cur = cur.next) {
            if (cur.value.equals(value)) {
                return true;
            }
        }
        return false;
    }

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

    public E removeFront() {
        return remove(0);
    }

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
        LinkedList<Integer> linkedList = new LinkedList<>();
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
