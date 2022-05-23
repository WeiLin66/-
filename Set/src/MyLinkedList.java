/**
 * 優化版本遞迴鏈表
 */

import javafx.util.Pair;

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
            StringBuilder res = new StringBuilder();
            res.append(value);
            res.append("-->");
            Node n = next;
            while (n != null) {
                res.append(n.value);
                res.append("-->");
                n = n.next;
            }
            res.append("null");
            return res.toString();
        }

    }

    private Node headNode;
    private int size;

    public MyLinkedList() {
        headNode = null;
        setSize(0);
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Node getHead() {
        return this.headNode;
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public void add(int index, E value) {
        if (isEmpty()) {
            headNode = new Node(value);
            setSize(1);
        }else {
            headNode = add(headNode, index, value);
        }
    }

    private Node add(Node head, int index, E value) {
        String depthString = recursiveDepth(index);
        System.out.print(depthString);
        System.out.println("Call: attempt to add  " + value + " in index: " + index);

        if (index == 0) {
            System.out.print(depthString);
            System.out.println("Return: Node " + value);

            setSize(getSize() + 1);
            return new Node(value, head);
        }

        Node res = add(head.next, index - 1, value);
        System.out.print(depthString);
        System.out.println("After: add " + value);

        head.next = res;

        System.out.print(depthString);
        System.out.println("Return: head " + res);

        return head;
    }


    public String recursiveDepth(int index) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i <= index; i++) {
            res.append("--");
        }
        return res.toString();
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
        return get(headNode, index);
    }

    private E get(Node head, int index) {
        if (index == 0) {
            return head.value;
        }
        return get(head.next, index - 1);
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
        if (isEmpty()) {
            throw new IllegalArgumentException("LinkedList is empty");
        }
        set(headNode, index, value);
    }

    private void set(Node head, int index, E value) {
        if (index == 0) {
            head.value = value;
            return;
        }
        set(head.next, index - 1, value);
    }

    public boolean contains(E value) {
        if (isEmpty()) {
            throw new IllegalArgumentException("LinkedList is empty");
        }
        return contains(headNode, value);
    }

    private boolean contains(Node head, E value) {
        if (head == null) {
            return false;
        }
        if (head.value.equals(value)) {
            return true;
        }
        return contains(head.next, value);
    }

    public E remove(int index) {
        if (isEmpty()) {
            throw new IllegalArgumentException("LinkedList is empty");
        } else if (index < 0 || index >= getSize()) {
            throw new IllegalArgumentException("Illegal index");
        }
        Pair<Node, E> temp = remove(headNode, index);
        headNode = temp.getKey();
        setSize(getSize() - 1);
        return temp.getValue();
    }

    private Pair<Node, E> remove(Node head, int index) {
        if (index == 0) {
            return new Pair<>(head.next, head.value);
        }
        Pair<Node, E> pres = remove(head.next, index - 1);
        head = pres.getKey();
        return new Pair<>(head, pres.getValue());
    }

    public E removeFront() {
        return remove(0);
    }

    public E removeLast() {
        return remove(getSize() - 1);
    }

    public void removeElement(E value) {
        if (!contains(value)) {
            return;
        }
        setSize(getSize() - 1);
        headNode = removeElement(headNode, value);
    }

    private Node removeElement(Node head, E value) {
        if (head.value.equals(value)) {
            return head.next;
        }
        head.next = removeElement(head.next, value);
        return head;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("LinkedList [");
        Node n = headNode;
        for (int i = 0; i < getSize(); i++) {
            res.append("(" + n.value + ")");
            res.append("--> ");
            n = n.next;
        }
        res.append("NULL]");

        return res.toString();
    }
}