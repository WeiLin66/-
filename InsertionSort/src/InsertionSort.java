import java.lang.reflect.Array;
import java.util.Arrays;

public class InsertionSort {

    private InsertionSort() {
    }

    ;

    /**
     * @param arr
     * @param <T>
     */
    public static <T extends Comparable<T>> void insertionSort(T[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0 && arr[j - 1].compareTo(arr[j]) > 0; j--) {
                swap(arr, j - 1, j);
            }
        }
    }

    /**
     * @param arr
     * @param <T>
     */
    public static <T extends Comparable<T>> void insertionSort2(T[] arr) {
        for (int i = 0; i < arr.length; i++) {
            T temp = arr[i];
            int j;
            for (j = i; j > 0 && temp.compareTo(arr[j - 1]) < 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = temp;
        }
    }

    /**
     * @param arr
     * @param head
     * @param find
     * @param <T>
     */
    public static <T> void swap(T[] arr, int head, int find) {
        T temp = arr[head];
        arr[head] = arr[find];
        arr[find] = temp;
    }

    public static void main(String[] args) {
        int[] dataSize = {10000, 100000};
        for (int n : dataSize) {
            Integer[] arr1 = ArrayGenerator.intArrayGenerator(n, n);
            Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);

            SortingHelper.sortTest("Insertion Sort1", arr1);
            SortingHelper.sortTest("Insertion Sort2", arr2);
            System.out.println("============================================================================");
        }
    }
}
