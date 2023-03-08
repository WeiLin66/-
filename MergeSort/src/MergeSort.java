import java.util.Arrays;

public class MergeSort {
    private MergeSort() {

    }

    // 用戶接口只需要傳入陣列即可
    public static <T extends Comparable<T>> void sort(T[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    public static <T extends Comparable<T>> void sort2(T[] arr) {
        T[] temp = Arrays.copyOf(arr, arr.length);
        mergeSort2(arr, 0, arr.length - 1, temp);
    }

    public static <T extends Comparable<T>> void sort3(T[] arr) {
        mergeSort3(arr, 0, arr.length - 1);
    }

    private static <T extends Comparable<T>> int len(T[] arr) {
        int i = 1;
        int len = arr.length;
        while (true) {
            if (len <= i) {
                return i;
            }
            i *= 2;
        }
    }

    /**
     * 基礎版本merge sort
     *
     * @param arr 數據陣列
     * @param l 陣列起始位置
     * @param r 陣列終止位置
     * @param <T>
     */
    private static <T extends Comparable<T>> void mergeSort(T[] arr, int l, int r) {
        if (l >= r) {
            return;
        }

        int mid = l + (r - l) / 2; // 避免大資料量時整數溢出
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        merge1(arr, l, mid, r);
    }

    /**
     * 優化版本merge sort
     * 小數據量時使用插入排序法優化
     *
     * @param arr 數據陣列
     * @param l 陣列起始位置
     * @param r 陣列終止位置
     * @param temp
     * @param <T>
     */
    private static <T extends Comparable<T>> void mergeSort2(T[] arr, int l, int r, T[] temp) {
        /* 當數量足夠少時，因為Merge Sort的常數項較大，所以使用Insertion Sort反而比較有利 */
        if (r - l <= 16) {
            InsertionSort.insertionSort(arr, l, r);
            return;
        }

        int mid = l + (r - l) / 2;
        mergeSort2(arr, l, mid, temp);
        mergeSort2(arr, mid + 1, r, temp);
        /* 若arr[mid] >= arr[mid+1]則不需要再比較，因為雙方都是有序且右區塊一定比較大 */
        if (arr[mid].compareTo(arr[mid + 1]) > 0) {
            merge2(arr, l, mid, r, temp);
        }
    }

    /**
     * 自底向上的Merge Sort
     *
     * @param arr 數據陣列
     * @param l 陣列起始位置
     * @param r 陣列終止位置
     * @param <T>
     */
    private static <T extends Comparable<T>> void mergeSort3(T[] arr, int l, int r) {
        T[] temp = Arrays.copyOf(arr, arr.length);
        int n = arr.length;

        /* 使用插入排序法進行優化，參數取值為16 */
        for (int i = 0; i < n; i += 16) {
            InsertionSort.insertionSort(arr, i, Math.min(i + 15, n - 1));
        }

        /* 遍歷合併的區間長度 */
        /* 由於使用插入排序法，所以sz改為從16開始 */
        for (int sz = 16; sz < n; sz += sz) {
            /* 遍歷合併的兩個區間的起始位置 */
            /* merge [i ... i + sz - 1], [i + sz ... Math.main(i + sz + sz - 1, n-1)] */
            for (int i = 0; i + sz < n; i += + sz + sz) {
                if (arr[i + sz - 1].compareTo(arr[i + sz]) > 0) {
                    /* 越界問題，要考慮i+sz+sz-1是否會大於n-1 */
                    merge2(arr, i, i + sz - 1, Math.min(arr.length - 1, i + sz + sz - 1), temp);
                }
            }
        }
    }

    private static <T extends Comparable<T>> void merge1(T[] arr, int l, int mid, int r) {
        T[] temp = Arrays.copyOfRange(arr, l, r + 1);
        int i = l, j = mid + 1;
        for (int k = i; k <= r; k++) {
            if (i > mid) {
                arr[k] = temp[j - l];
                j++;
            } else if (j > r) {
                arr[k] = temp[i - l];
                i++;
            } else if (temp[i - l].compareTo(temp[j - l]) <= 0) {
                arr[k] = temp[i - l];
                i++;
            } else {
                arr[k] = temp[j - l];
                j++;
            }
        }
    }

    /**
     * 優化版本merge，防止在內存中重複創造陣列
     * @param arr 數據陣列
     * @param l 陣列起始位置
     * @param mid 陣列中間index位置
     * @param r 陣列終止位置
     * @param temp 暫存陣列
     * @param <T>
     */
    private static <T extends Comparable<T>> void merge2(T[] arr, int l, int mid, int r, T[] temp) {
        System.arraycopy(arr, l, temp, l, (r - l + 1));
        int i = l, j = mid + 1;
        for (int k = i; k <= r; k++) {
            if (i > mid) {
                arr[k] = temp[j++];
            } else if (j > r) {
                arr[k] = temp[i++];
            } else if (temp[i].compareTo(temp[j]) <= 0) {
                arr[k] = temp[i++];
            } else {
                arr[k] = temp[j++];
            }
        }
    }

    public static void main(String[] args) {
        int n = 100000;
        Integer[] arr = ArrayGenerator.intArrayGenerator(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, n);
        SortingHelper.sortTest("Merge Sort2", arr);
        SortingHelper.sortTest("Merge Sort3", arr2);
    }
}
