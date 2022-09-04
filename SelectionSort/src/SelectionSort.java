/**
 * 選擇排序實現類
 */

public class SelectionSort {

    /**
     * 選擇排序，[0...i)為已處理；[i...n)為未處理
     *
     * @param arr 對象陣列
     */
    public static <T extends Comparable<T>> void sort(T[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int find = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j].compareTo(arr[find]) < 0) {
                    find = j;
                }
            }
            swap(arr, i, find);
        }
    }

    /**
     * 選擇排序，[i...n)為已處理；[0...i)為未處理
     *
     * @param arr 對象陣列
     */
    public static <T extends Comparable<T>> void reverseSort(T[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            int find = i;
            for (int j = i; j >= 0; j--) {
                if (arr[find].compareTo(arr[j]) < 0) {
                    find = j;
                }
            }
            swap(arr, i, find);
        }
    }

    /**
     * 將陣列中index為head、find兩元素交換
     *
     * @param arr  對象陣列
     * @param head 元素A index
     * @param find 元素B index
     */
    public static <T> void swap(T[] arr, int head, int find) {
        T temp = arr[find];
        arr[find] = arr[head];
        arr[head] = temp;
    }

    public static void main(String[] args) {
        int[] dataSize = {10000, 100000};
        for (int i : dataSize) {
            SortingHelper.sortTest("Reverse Selection Sort", ArrayGenerator.intArrayGenerator(i, i));
        }
    }
}
