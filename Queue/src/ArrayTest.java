/**
 * 隊列測試類
 */

import java.util.Random;

public class ArrayTest {

    /**
     * 大筆資料的隊列時間性能測試
     *
     * @param q       隊列類型對象
     * @param opCount 測試資料總量
     * @return 執行平均時間
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
     * 將queueTest方法執行5次，並取其平均時間
     *
     * @param q       隊列類型對象
     * @param opCount 測試資料總量
     * @return 執行5次平均時間
     */
    public static double averageQueueTest(Queue<Integer> q, int opCount) {
        double totalTime = 0;
        for (int i = 0; i < 5; i++) {
            totalTime += queueTest(q, opCount);
        }
        return totalTime / 5.0;
    }

    /**
     * 一般的入隊與出隊測試方法，兩個操作分進行times/2次，因此最後
     * 隊列元素會被清空。主要觀察過程中的隊列元素變化
     *
     * @param q     隊列類型對象
     * @param times 入隊與出隊總執行次數
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
