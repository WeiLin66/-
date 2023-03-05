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

    /**
     * 判斷佇列是否為空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }

    /**
     * 入隊操作
     * 插入尾節點
     *
     * @param e
     */
    @Override
    public void enqueue(E e) {
        if (isEmpty()) {
            tail = new Node(e);
            tail.next = null;
            dummy.next = tail; // 更新dummy head
        } else {
            tail.next = new Node(e);
            tail = tail.next;
        }
        setSize(getSize() + 1);
    }

    /**
     * 出隊操作
     * 移除首節點
     *
     * @return
     */
    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty");
        }

        Node retNode = dummy.next;
        dummy.next = retNode.next; // 更新dummy head
        retNode.next = null;
        setSize(getSize() - 1);
        if (getSize() == 0) {
            tail = null;
        }
        return retNode.value;
    }

    /**
     * 獲取首節點數值
     *
     * @return 首節點數值
     */
    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty");
        }
        return dummy.next.value; // dummy.next即為首節點
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
