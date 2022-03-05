/**
 * leetcode 232. Implement Stack using Queues
 * solution 2
 */

import java.util.*;

class MyStack2 {

    private LinkedList<Integer> queue;
    private LinkedList<Integer> temp;
    private int top;

    public MyStack2() {
        queue = new LinkedList<>();
        temp = new LinkedList<>();
    }

    /**
     *
     * @param x
     */
    public void push(int x) {
        top = x;
        queue.add(x);
    }

    /**
     *
     * @return
     */
    public int pop() {
        while(queue.size() -1 > 0){
            temp.add(queue.remove());
        }

        int res = queue.remove();

        while(temp.size() != 0){
            top = temp.remove();
            queue.add(top);
        }

        return res;
    }

    /**
     *
     * @return
     */
    public int top() {
        if(empty()){
            throw new IllegalArgumentException("Stack is empty");
        }
        return top;
    }

    /**
     *
     * @return
     */
    public boolean empty() {
        return queue.size() == 0;
    }

    public static void main(String[] args) {
        MyStack2 mystack = new MyStack2();
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