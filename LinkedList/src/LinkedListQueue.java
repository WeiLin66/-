/**
 * 使用帶有尾指標鏈表實現的佇列
 */

import java.util.LinkedList;

public class LinkedListQueue<E> implements Queue<E> {

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

    private Node head;
    private Node tail;
    private int size;

    public LinkedListQueue() {
        head = null;
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
     * @return 若為空則返回真
     */
    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }

    /**
     * 入隊操作
     * 將節點插入到尾節點
     *
     * @param e 節點數值
     */
    @Override
    public void enqueue(E e) {
        if (isEmpty()) {
            tail = new Node(e);
            tail.next = null;
            head = tail;
        } else {
            tail.next = new Node(e);
            tail = tail.next;
        }
        setSize(getSize() + 1);
    }

    /**
     * 出隊操作
     * 將頭節點移除
     *
     * @return 返回頭節點數值
     */
    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty");
        }

        Node retNode = head;
        head = head.next;
        retNode.next = null;
        setSize(getSize() - 1);
        if (getSize() == 0) {
            tail = null;
        }
        return retNode.value;
    }

    /**
     * 獲取頭節點數值
     *
     * @return 頭節點數值
     */
    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty");
        }
        return head.value;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("LinkedList front [");
        for (Node n = head; n != null; n = n.next) {
            res.append("(" + n.value + ")");
            if (n.next != null) {
                res.append("-->");
            }
        }
        res.append("] tail");

        return res.toString();
    }

    public static void main(String[] args) {
        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
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
