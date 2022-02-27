/**
 * Stack測試類
 */
public class StackTest {
    public static void main(String[] args) {
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        for (int i = 0; i < 10; i++) {
            arrayStack.push(i);
        }
        System.out.println(arrayStack);
        arrayStack.pop();
        arrayStack.pop();
        arrayStack.pop();
        System.out.println(arrayStack);
        System.out.println("Top :　" + arrayStack.peek());

    }
}
