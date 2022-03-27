import java.util.Arrays;

public class QuickSort {

    private QuickSort() {
    }

    public static <T extends Comparable<T>> void sort(T[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    public static <T extends Comparable<T>> void sort2(T[] arr) {
        quickSort2(arr, 0, arr.length - 1);
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
        quickSort(arr, l, p - 1);
        quickSort(arr, p + 1, r);
    }

    private static <T extends Comparable<T>> int partition(T[] arr, int l, int r) {
        int i = l;
        T p = arr[l];

        for (int k = l + 1; k <= r; k++) {
            if (p.compareTo(arr[k]) >= 0) { // partition value greater or equal than arr[k]
                i++;
                if(i != k){
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
        int n = 100000;
        Integer[] arr = ArrayGenerator.intArrayGenerator(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, n);
        SortingHelper.sortTest("Quick Sort", arr);
        SortingHelper.sortTest("Quick Sort2", arr2);
    }

}
