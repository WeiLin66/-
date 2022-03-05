import java.util.Random;

public class LinkedListTest {

    /**
     *
     * @param q
     * @param opCount
     * @return
     */
    public static double stackTest(Stack<Integer> q, int opCount) {
        long startTime = System.nanoTime();
        Random rmd = new Random();
        for (int i = 0; i < opCount; i++) {
            q.push(rmd.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            q.pop();
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    /**
     *
     * @param q
     * @param opCount
     * @return
     */
    public static double averageStackTest(Stack<Integer> q, int opCount) {
        double totalTime = 0;
        for (int i = 0; i < 10; i++) {
            totalTime += stackTest(q, opCount);
        }
        return totalTime / 10.0;
    }

    /**
     *
     * @param q
     * @param times
     */
    public static void regularTest(Stack<Integer> q, int times) {
        for (int i = 0; i < times / 2; i++) {
            q.push(i);
            System.out.println(q);
        }
        for (int i = 0; i < times / 2; i++) {
            q.pop();
            System.out.println(q);
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        int opCount = 100000;
        int regularCount = 20;
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
//        regularTest(linkedListStack, regularCount);
//        regularTest(arrayStack, regularCount);
        System.out.println("Array total time consume: " + averageStackTest(arrayStack, opCount) + "s");
        System.out.println("LinkedList total time consume: " + averageStackTest(linkedListStack, opCount) + "s");
    }
}
