public class LinkedListR<E> {
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
            StringBuilder res = new StringBuilder();
            res.append(value);
            return res.toString();
        }

    }

    private Node dummyHead;
    private int size;

    public LinkedListR() {
        dummyHead = new Node();
        setSize(0);
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Node getHead() {
        return this.dummyHead;
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public Node add(Node head, int index, E value) {
        if (index == 0) {
            Node n = new Node(value, head);
            n.next = head.next;
            head.next = n;
            setSize(getSize() + 1);
            return head;
        }

        head.next = add(head.next, index - 1, value);
        return head;
    }

    public String recursiveDepth(int index) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i <= index; i++) {
            res.append("--");
        }
        return res.toString();
    }

    public Node addFirst(Node head, E value) {
        return add(head, 0, value);
    }

    public Node addLast(Node head, E value) {
        return add(head, getSize(), value);
    }

    public E get(Node head, int index) {
        if (index < 0 || index >= getSize()) {
            throw new IllegalArgumentException("Illegal index");
        }
        if (index == 0) {
            return head.next.value;
        }
        return get(head.next, index - 1);
    }

    public E getFront(Node head) {
        return get(head, 0);
    }

    public E getLast(Node head) {
        return get(head, getSize() - 1);
    }

    public void set(Node head, int index, E value) {
        if (index < 0 || index >= getSize()) {
            throw new IllegalArgumentException("Illegal index");
        }
        if (isEmpty()) {
            throw new IllegalArgumentException("LinkedList is empty");
        }
        if (index == 0) {
            head.next.value = value;
            return;
        }
        set(head.next, index - 1, value);
    }

    public boolean contains(Node head, E value) {
        if (isEmpty()) {
            throw new IllegalArgumentException("LinkedList is empty");
        }

        if (head == null) {
            return false;
        }

        return value.equals(head.value) || contains(head.next, value);
    }

    public E remove(Node head, int index) {
        if (isEmpty()) {
            throw new IllegalArgumentException("LinkedList is empty");
        } else if (index < 0 || index >= getSize()) {
            throw new IllegalArgumentException("Illegal index");
        }

        if (index == 0) {
            Node n = head.next;
            head.next = n.next;
            n.next = null;
            setSize(getSize() - 1);
            return n.value;
        }
        return remove(head.next, index - 1);
    }

    public E removeFront(Node head) {
        return remove(head, 0);
    }

    public E removeLast(Node head) {
        return remove(head, getSize() - 1);
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
        LinkedListR<Integer> linkedListR = new LinkedListR<>();
        for (int i = 0; i < 10; i++) {
            linkedListR.addFirst(linkedListR.getHead(), i);
            System.out.println(linkedListR);
        }
        for (int i = 0; i < 5; i++) {
            linkedListR.removeLast(linkedListR.getHead());
            System.out.println(linkedListR);
        }
        linkedListR.set(linkedListR.getHead(), 3, 666);
        System.out.println(linkedListR);
        System.out.println(666 + ": " + linkedListR.contains(linkedListR.getHead(), 666));
        System.out.println("Get Last: " + linkedListR.getLast(linkedListR.getHead()));
    }
}