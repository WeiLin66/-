import java.util.Arrays;

public class InsertionSort {

    private InsertionSort() {}

    /**
     * 雙重迴圈版本插入排序法
     *
     * @param arr 泛型對象陣列
     */
    public static <T extends Comparable<T>> void insertionSort(T[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0 && arr[j - 1].compareTo(arr[j]) > 0; j--) {
                swap(arr, j - 1, j);
            }
        }
    }

    /**
     * 優化版本插入排序。包括 1. 使用暫存記憶空間替代交換幫法減少語句 2. 將判斷式寫入迴圈判斷減少語句
     *
     * @param arr 泛型對象陣列
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
     * 逆向優化版本插入排序法
     *
     * @param arr 泛型對象陣列
     */
    public static <T extends Comparable<T>> void reverseInsertionSort(T[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            T temp = arr[i];
            int j;
            for (j = i; j + 1 < arr.length && temp.compareTo(arr[j + 1]) > 0; j++) {
                arr[j] = arr[j + 1];
            }
            arr[j] = temp;
        }
    }


    /**
     * 交換方法
     *
     * @param arr  泛型對象陣列
     * @param head 欲交換元素A
     * @param find 欲交換元素B
     */
    public static <T> void swap(T[] arr, int head, int find) {
        T temp = arr[head];
        arr[head] = arr[find];
        arr[find] = temp;
    }

    public static void main(String[] args) {
        int[] dataSize = {10000, 100000};
        for (int n : dataSize) {
            System.out.println("==================================Random Sort=====================================");

            Integer[] arr1 = ArrayGenerator.intArrayGenerator(n, n);
            Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);

            SortingHelper.sortTest("Reverse Insertion Sort", arr1);
            SortingHelper.sortTest("Selection Sort", arr2);

            System.out.println("==================================Ordered Sort=====================================");

            arr1 = ArrayGenerator.generateOrderedArray(n, n);
            arr2 = Arrays.copyOf(arr1, arr1.length);

            SortingHelper.sortTest("Reverse Insertion Sort", arr1);
            SortingHelper.sortTest("Selection Sort", arr2);
        }
    }
}
