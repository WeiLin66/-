import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    private QuickSort() {
    }

    public static <T extends Comparable<T>> void sort(T[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    public static <T extends Comparable<T>> void sort2(T[] arr) {
        quickSort2(arr, 0, arr.length - 1);
    }

    public static <T extends Comparable<T>> void sort3(T[] arr) {
        Random rd = new Random();
        quickSort3(arr, 0, arr.length - 1, rd);
    }

    public static <T extends Comparable<T>> void sort4(T[] arr) {
        quickSort4(arr, 0, arr.length - 1);
    }

    private static <T extends Comparable<T>> void quickSort(T[] arr, int l, int r) {
        if (l >= r) {
            return;
        }

        int p = partition(arr, l, r);
        quickSort(arr, l, p - 1);
        quickSort(arr, p + 1, r);
    }

    private static <T extends Comparable<T>> void quickSort2(T[] arr, int l, int r) {
        /* 使用插入排序優化快速排序 */
        if (r - l <= 12) {
            InsertionSort.insertionSort(arr, l, r);
            return;
        }

        int p = partition(arr, l, r);
        quickSort2(arr, l, p - 1);
        quickSort2(arr, p + 1, r);
    }

    /**
     * 優化目的是防止Quick Sort對有序陣列的O(n)時間複雜度
     */
    private static <T extends Comparable<T>> void quickSort3(T[] arr, int l, int r, Random rd) {
        /* 使用插入排序優化快速排序 */
        if (r - l <= 12) {
            InsertionSort.insertionSort(arr, l, r);
            return;
        }

        /* 使用隨機化進行優化 */
        int p = partition2(arr, l, r, rd);
        quickSort3(arr, l, p - 1, rd);
        quickSort3(arr, p + 1, r, rd);
    }

    /**
     * 使用中間取值作為partition的版本
     */
    private static <T extends Comparable<T>> void quickSort4(T[] arr, int l, int r) {
        if (l >= r) {
            return;
        }

        int p = partition3(arr, l, r);
        quickSort4(arr, l, p - 1);
        quickSort4(arr, p + 1, r);
    }

    private static <T extends Comparable<T>> int partition(T[] arr, int l, int r) {
        int i = l;
        T p = arr[l];

        for (int k = l + 1; k <= r; k++) {
            if (p.compareTo(arr[k]) >= 0) { // partition value greater or equal than arr[k]
                i++;
                if (i != k) {
                    swap(arr, i, k);
                }
            }
        }
        /* swap partition value to the tail of smaller value sequence */
        if (i != l && arr[i] != arr[l]) {
            swap(arr, i, l);
        }

        return i;
    }

    /**
     * 使用隨機化優化快速排序
     */
    private static <T extends Comparable<T>> int partition2(T[] arr, int l, int r, Random rd) {
        /* 將隨機選取的index與第一個元素交換，剩餘操作與一般partition相同 */
        swap(arr, l, rd.nextInt(r - l + 1) + l);
        int i = l;
        T p = arr[l];

        for (int k = l + 1; k <= r; k++) {
            if (p.compareTo(arr[k]) >= 0) { // partition value greater or equal than arr[k]
                i++;
                if (i != k) {
                    swap(arr, i, k);
                }
            }
        }
        /* swap partition value to the tail of smaller value sequence */
        if (i != l && arr[i] != arr[l]) {
            swap(arr, i, l);
        }

        return i;
    }

    /**
     * 使用中間值當作partition
     */
    private static <T extends Comparable<T>> int partition3(T[] arr, int l, int r) {
        swap(arr, l, (r + l) / 2);
        int i = l;
        T p = arr[l];

        for (int k = l + 1; k <= r; k++) {
            if (p.compareTo(arr[k]) >= 0) { // partition value greater or equal than arr[k]
                i++;
                if (i != k) {
                    swap(arr, i, k);
                }
            }
        }
        /* swap partition value to the tail of smaller value sequence */
        if (i != l && arr[i] != arr[l]) {
            swap(arr, i, l);
        }

        return i;
    }

    private static <T extends Comparable<T>> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int n = 5;
        Integer[] arr = ArrayGenerator.generateSpecialArray(n);
        for (int i : arr) {
            System.out.println(i + " ");
        }
        System.out.println();
//        Integer[] arr2 = Arrays.copyOf(arr, n);
//        SortingHelper.sortTest("Quick Sort4", arr);
//        SortingHelper.sortTest("Quick Sort2", arr2);
    }

}
