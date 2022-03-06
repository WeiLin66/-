import java.util.LinkedList;

public class LinkedListQueueDummy<E> implements Queue<E> {

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

    private Node dummy;
    private Node tail;
    private int size;

    public LinkedListQueueDummy() {
        dummy = new Node();
        tail = null;
        setSize(0);
    }

    @Override
    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }

    @Override
    public void enqueue(E e) {
        if (isEmpty()) {
            tail = new Node(e);
            tail.next = null;
            dummy.next = tail;
        } else {
            tail.next = new Node(e);
            tail = tail.next;
        }
        setSize(getSize() + 1);
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty");
        }

        Node retNode = dummy.next;
        dummy.next = retNode.next;
        retNode.next = null;
        setSize(getSize() - 1);
        if (getSize() == 0) {
            tail = null;
        }
        return retNode.value;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty");
        }
        return dummy.next.value;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("LinkedList front [(dummy)-->");
        for (Node n = dummy.next; n != null; n = n.next) {
            res.append("(" + n.value + ")");
            if (n.next != null) {
                res.append("-->");
            }
        }
        res.append("] tail");

        return res.toString();
    }

    public static void main(String[] args) {
        LinkedListQueueDummy<Integer> linkedListQueue = new LinkedListQueueDummy<>();
        for (int i = 0; i < 20; i++) {
            linkedListQueue.enqueue(i);
            System.out.println(linkedListQueue);
            if (i % 3 == 2) {
                linkedListQueue.dequeue();
                System.out.println(linkedListQueue);
            }
        }
    }
}
