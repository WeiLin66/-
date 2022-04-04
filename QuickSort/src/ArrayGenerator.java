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

        if (!SortingHelper.isSort(integers)) {
            return null;
        }

        return integers;
    }

    /**
     * 產生特殊陣列使得sort4的時間複雜度為O(n^2)
     *
     * @param n 陣列長度
     * @return 整型陣列
     */
    public static Integer[] generateSpecialArray(int n) {
        Integer[] arr = new Integer[n];
        generateSpecialArray(arr, 0, arr.length - 1, 0);
        return arr;
    }

    private static void generateSpecialArray(Integer[] arr, int l, int r, int value) {
        if (l > r) {
            return;
        }
        int mid = (r + l)/2;
        arr[l] = value;
        generateSpecialArray(arr, l + 1, r, ++value);
        swap(arr, mid, l);
    }

    private static <E> void swap(E[] arr, int i, int j) {

        E t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
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
}
