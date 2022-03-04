/**
 * leetcode 232. Implement Queue using Stacks
 */
public class MyQueue {
    private Deque<Integer> stack;
    private Deque<Integer> temp;
    private int size;
    private int top;

    public MyQueue() {
        stack = new Deque<>();
        temp = new Deque<>();
        setSize(0);
        setTop(0);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    /**
     *
     * @param x
     */
    public void push(int x) {
        stack.addFront(x);
        setTop(x);
        setSize(getSize() + 1);
    }

    /**
     *
     * @return
     */
    public int pop() {
        if (empty()) {
            throw new IllegalArgumentException("Queue is empty");
        }
        int len = getSize();
        for (int i = 0; i < len - 1; i++) {
            temp.addFront(stack.removeFront());
        }
        setTop(stack.removeFront());

        for (int i = 0; i < len - 1; i++) {
            stack.addFront(temp.removeFront());
        }
        setSize(len - 1);
        return getTop();
    }

    /**
     *
     * @return
     */
    public int peek() {
        if (empty()) {
            throw new IllegalArgumentException("Queue is empty");
        }
        return getTop();
    }

    public boolean empty() {
        return getSize() == 0;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(stack);
        return res.toString();
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        for (int i = 0; i < 10; i++) {
            myQueue.push(i);
            System.out.println(myQueue);
        }

        for (int i = 0; i < 10; i++) {
            myQueue.pop();
            System.out.println(myQueue);
        }
    }
}
