/**
 * leetcode 225. Implement Stack using Queues
 */

class MyStack {
    private Queue<Integer> queue;
    private Queue<Integer> temp;
    private int size;
    private int top;

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

    public MyStack() {
        queue = new ArrayQueue<Integer>();
        temp = new ArrayQueue<Integer>();
        top = 0;
        setSize(getSize());
    }

    /**
     *
     * @param x
     */
    public void push(int x) {
        setTop(x);
        queue.enqueue(x);
        setSize(getSize() + 1);
    }

    /**
     *
     * @return
     */
    public int pop() {
        if (empty()) {
            throw new IllegalArgumentException("Stack is empty");
        }

        for (int i = 0; i < getSize() - 1; i++) {
            temp.enqueue(queue.dequeue());
        }
        setSize(getSize() - 1);
        setTop(queue.dequeue());
        int len = temp.getSize();

        for (int i = 0; i < len; i++) {
            queue.enqueue(temp.dequeue());
        }

        return getTop();
    }

    /**
     *
     * @return
     */
    public int top() {
        if (empty()) {
            throw new IllegalArgumentException("Stack is empty");
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
        res.append(queue);
        return res.toString();
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        for (int i = 0; i < 5; i++) {
            myStack.push(i);
            System.out.println(myStack);
        }

        for (int i = 0; i < 5; i++) {
            myStack.pop();
            System.out.println(myStack);
        }
    }
}