/**
 * leetcode 225. Implement Stack using Queues
 * solution 3
 */

import java.util.Stack;

class MyQueue3 {

    private Stack<Integer> stack;
    private Stack<Integer> temp;
    private int top;

    public MyQueue3() {
        stack = new Stack<>();
        temp = new Stack<>();
    }

    public void push(int x) {
        if(stack.empty()){
            top = x;
        }
        stack.push(x);
    }

    public int pop() {
        if(!temp.empty()){
            return temp.pop();
        }

        while(stack.size() > 1){
            temp.push(stack.pop());
        }

        return stack.pop();
    }

    public int peek() {
        if(!temp.empty()){
            return temp.peek();
        }
        return top;
    }

    public boolean empty() {
        return stack.empty() && temp.empty();
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
