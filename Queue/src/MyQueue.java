/**
 * leetcode 225. Implement Stack using Queues
 * solution 1
 */

import java.util.Stack;

class MyQueue {

    private Stack<Integer> stack;
    private Stack<Integer> temp;

    public MyQueue() {
        stack = new Stack<>();
        temp = new Stack<>();
    }

    public void push(int x) {
        while (!stack.empty()) {
            temp.push(stack.pop());
        }
        stack.push(x);
        while (!temp.empty()) {
            stack.push(temp.pop());
        }
    }

    public int pop() {
        if (empty()) {
            throw new IllegalArgumentException("Queue is empty");
        }
        return stack.pop();
    }

    public int peek() {
        return stack.peek();
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