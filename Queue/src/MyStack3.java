/**
 * leetcode 232. Implement Stack using Queues
 * solution 2
 */

import java.util.*;

class MyStack3 {

    private LinkedList<Integer> queue;

    /**
     *
     */
    public MyStack3() {
        queue = new LinkedList<>();
    }

    /**
     *
     * @param x
     */
    public void push(int x) {
        queue.add(x);
        for(int i=0; i<queue.size()-1; i++){
            int tail = queue.remove();
            queue.add(tail);
        }
    }

    /**
     *
     * @return
     */
    public int pop() {
        if(empty()){
            throw new IllegalArgumentException("Stack is empty");
        }
        return queue.remove();
    }

    /**
     *
     * @return
     */
    public int top() {
        if(empty()){
            throw new IllegalArgumentException("Stack is empty");
        }
        return queue.peek();
    }

    /**
     *
     * @return
     */
    public boolean empty() {
        return queue.size() == 0;
    }

    public static void main(String[] args) {
        MyStack3 mystack = new MyStack3();
        for(int i=0; i<10; i++){
            mystack.push(i);
            System.out.println("top: " + mystack.top());
        }

        System.out.println();

        for(int i=0; i<10; i++){
            System.out.println("top: " + mystack.top());
            mystack.pop();
        }
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */