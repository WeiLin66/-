import java.util.Arrays;
import java.util.Random;

/**
 * 陣列產生類
 */
public class ArrayGenerator {
    /**
     * 整數類型的陣列產生器
     *
     * @param n     陣列長度
     * @param bound 數值上限
     * @return 整數類型陣列對象
     */
    public static Integer[] intArrayGenerator(int n, int bound) {
        Integer[] integers = new Integer[n];
        for (int i = 0; i < n; i++) {
            Random rnd = new Random();
            integers[i] = rnd.nextInt(bound) + 1;
        }
        return integers;
    }

    /**
     * 整數類型的已排序陣列產生器
     *
     * @param n     陣列長度
     * @param bound 數值上限
     * @return 以小到大排列的整數類型陣列對象
     */
    public static Integer[] generateOrderedArray(int n, int bound) {
        Integer[] integers = new Integer[n];
        for (int i = 0; i < n; i++) {
            Random rnd = new Random();
            integers[i] = rnd.nextInt(bound) + 1;
        }
        Arrays.sort(integers);

        if(!SortingHelper.isSort(integers)){
            return null;
        }

        return integers;
    }

    /**
     * 浮點數類型的陣列產生器
     *
     * @param n 陣列長度
     * @return 浮點數類型陣列對象
     */
    public static Double[] doubleArrayGenerator(int n) {
        Double[] doubles = new Double[n];
        for (int i = 0; i < n; i++) {
            doubles[i] = Math.random() * 100 + 1;
        }
        return doubles;
    }

    /**
     * Elements類型的陣列產生器
     *
     * @param n 陣列長度
     * @return Elements類型陣列對象
     */
    public static Elements[] elementsArrayGenerator(int n) {
        Elements[] elements = new Elements[n];

        for (int i = 0; i < n; i++) {
            String id = "s" + i;
            int number = (int) (Math.random() * 100 + 1);
            elements[i] = new Elements(id, number);
        }
        return elements;
    }
}
