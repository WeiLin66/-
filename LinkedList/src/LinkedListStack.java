public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> linkedList;

    public LinkedListStack() {
        linkedList = new LinkedList<>();
    }

    @Override
    public void push(E value) {
        linkedList.addFirst(value);
    }

    @Override
    public E pop() {
        return linkedList.removeFront();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public E peek() {
        return linkedList.getFront();
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack front: ");
        res.append(linkedList);
        return res.toString();
    }

    public static void main(String[] args) {
        LinkedListStack<Integer> linkedList = new LinkedListStack<>();
        for (int i = 0; i < 5; i++) {
            linkedList.push(i);
            System.out.println(linkedList);
        }

        for (int i = 0; i < 5; i++) {
            linkedList.pop();
            System.out.println(linkedList);
        }
    }
}
