/**
 * leetcode 225. Implement Stack using Queues
 * solution 2
 */

import java.util.Stack;

class MyQueue2 {

    private Stack<Integer> stack;
    private Stack<Integer> temp;
    private int top;

    public MyQueue2() {
        stack = new Stack<>();
        temp = new Stack<>();
    }

    public void push(int x) {
        if (empty()) {
            top = x;
        }
        stack.push(x);
    }

    public int pop() {
        if (empty()) {
            throw new IllegalArgumentException("Queue is empty");
        }
        while (stack.size() - 1 > 0) {
            top = stack.pop();
            temp.push(top);
        }

        int data = stack.pop();

        while (!temp.empty()) {
            stack.push(temp.pop());
        }

        return data;
    }

    public int peek() {
        if (empty()) {
            throw new IllegalArgumentException("Queue is empty");
        }
        return top;
    }

    public boolean empty() {
        return stack.empty();
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Size: %d\n", stack.size()));
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

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
