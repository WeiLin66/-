/**
 * 隊列測試類
 */

import java.util.Random;

public class ArrayTest {

    /**
     * @param q
     * @param opCount
     * @return
     */
    public static double queueTest(Queue<Integer> q, int opCount) {
        long startTime = System.nanoTime();
        Random rmd = new Random();
        for (int i = 0; i < opCount; i++) {
            q.enqueue(rmd.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            q.dequeue();
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
    public static double averageQueueTest(Queue<Integer> q, int opCount) {
        double totalTime = 0;
        for (int i = 0; i < 5; i++) {
            totalTime += queueTest(q, opCount);
        }
        return totalTime / 5.0;
    }

    /**
     * @param q
     */
    public static void regularTest(Queue<Integer> q, int times) {
        for (int i = 0; i < times / 2; i++) {
            q.enqueue(i);
            System.out.println(q);
        }
        for (int i = 0; i < times / 2; i++) {
            q.dequeue();
            System.out.println(q);
        }
    }

    public static void main(String[] args) {
        int opCount = 100000;
        LoopQueueOp2<Integer> loopQueueOp2 = new LoopQueueOp2<>();
        System.out.println("LoopQueueOp2: " + averageQueueTest(loopQueueOp2, opCount) + " s, for " + opCount + " datas");
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        System.out.println("ArrayQueue: " + averageQueueTest(arrayQueue, opCount) + " s, for " + opCount + " datas");

    }
}
