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

    // 底層實現
    private static <T extends Comparable<T>> void mergeSort(T[] arr, int l, int r) {
        if (l >= r) {
//            System.out.println("只剩單一元素: " + arr[l]);
            return;
        }

        int mid = l + (r - l) / 2; // 避免大資料量時整數溢出
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        merge1(arr, l, mid, r);
    }

    private static <T extends Comparable<T>> void mergeSort2(T[] arr, int l, int r, T[] temp) {
//        if (l >= r) {
//            return;
//        }
        /* 當數量足夠少時，引為Merge Sort的常數項較大，所以使用Insertion Sort反而比較有利 */
        if (r - l <= 15) {
            InsertionSort.insertionSort(arr, l, r);
            return;
        }

        int mid = l + (r - l) / 2;
        mergeSort2(arr, l, mid, temp);
        mergeSort2(arr, mid + 1, r, temp);
        /* 若arr[mid] >= arr[mid+1]則不需要再比較，因為雙方都是有序且右區塊一定比較大 */
        if (arr[mid].compareTo(arr[mid + 1]) >= 0) {
            merge2(arr, l, mid, r, temp);
        }
    }

    /**
     * 自底向上的Merge Sort
     */
    private static <T extends Comparable<T>> void mergeSort3(T[] arr, int l, int r) {
        T[] temp = Arrays.copyOf(arr, arr.length);
        int n = arr.length;

        /* 使用插入排序法進行優化，參數取值為10 */
        for (int i = 0; i < n; i += 10) {
            InsertionSort.insertionSort(arr, i, Math.min(i + 15, n - 1));
        }

        /* 由於使用插入排序法，所以sz改為從20開始 */
        for (int sz = 10; sz < n; sz += sz) {
            /* merge [i ... i + sz - 1], [i + sz ... i + sz + sz - 1] */
            for (int i = 0; i + sz < n; i = i + sz + sz) {
                if (arr[i + sz - 1].compareTo(arr[i + sz]) > 0) {
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
     */
    private static <T extends Comparable<T>> void merge2(T[] arr, int l, int mid, int r, T[] temp) {
        System.arraycopy(arr, l, temp, l, (r - l + 1));
        int i = l, j = mid + 1;
        for (int k = i; k <= r; k++) {
            if (i > mid) {
                arr[k] = temp[j++];
            } else if (j > r) {
                arr[k] = temp[i++];
            } else if (temp[i - l].compareTo(temp[j - l]) <= 0) {
                arr[k] = temp[i++];
            } else {
                arr[k] = temp[j++];
            }
        }
    }

    public static void main(String[] args) {
        int n = 100000;
        Integer[] arr = ArrayGenerator.intArrayGenerator(n, n);
        SortingHelper.sortTest("Merge Sort3", arr);
    }
}
