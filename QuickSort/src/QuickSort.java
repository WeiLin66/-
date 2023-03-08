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

    public static <T extends Comparable<T>> void sort5(T[] arr) {
        Random rd = new Random();
        quickSort2ways1(arr, 0, arr.length - 1, rd);
    }

    public static <T extends Comparable<T>> void sort6(T[] arr) {
        Random rd = new Random();
        quickSort2ways2(arr, 0, arr.length - 1, rd);
    }

    public static <T extends Comparable<T>> void sort7(T[] arr) {
        Random rd = new Random();
        quickSort3ways(arr, 0, arr.length - 1, rd);
    }

    /**
     * 基礎版快速排序
     *
     * @param arr 數據陣列
     * @param l 陣列起始位置
     * @param r 陣列終止位置
     */
    private static <T extends Comparable<T>> void quickSort(T[] arr, int l, int r) {
        if (l >= r) {
            return;
        }

        int p = partition(arr, l, r);
        quickSort(arr, l, p - 1);
        quickSort(arr, p + 1, r);
    }

    /**
     * 使用插入排序優化的快速排序
     *
     * @param arr 數據陣列
     * @param l 陣列起始位置
     * @param r 陣列終止位置
     */
    private static <T extends Comparable<T>> void quickSort2(T[] arr, int l, int r) {
        /* 使用插入排序 */
        if (r - l <= 15) {
            InsertionSort.insertionSort(arr, l, r);
            return;
        }

        int p = partition(arr, l, r);
        quickSort2(arr, l, p - 1);
        quickSort2(arr, p + 1, r);
    }

    /**
     * 優化目的是防止Quick Sort對有序陣列產生O(n^2)的時間複雜度
     *
     * @param arr 數據陣列
     * @param l 陣列起始位置
     * @param r 陣列終止位置
     * @param rd 隨機類
     */
    private static <T extends Comparable<T>> void quickSort3(T[] arr, int l, int r, Random rd) {
        /* 使用插入排序優化快速排序 */
        if (r - l <= 15) {
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
     *
     * @param arr 數據陣列
     * @param l 陣列起始位置
     * @param r 陣列中止位置
     */
    private static <T extends Comparable<T>> void quickSort4(T[] arr, int l, int r) {
        if (l >= r) {
            return;
        }

        int p = partition3(arr, l, r);
        quickSort4(arr, l, p - 1);
        quickSort4(arr, p + 1, r);
    }

    /**
     * 雙路快速排序，避免元素全部相同造成複雜度為O(n^2)問題
     * version 1
     *
     * @param arr 數據陣列
     * @param l 陣列起始位置
     * @param r 陣列終止位置
     * @param rd 隨機類
     */
    private static <T extends Comparable<T>> void quickSort2ways1(T[] arr, int l, int r, Random rd) {
        if (l >= r) {
            return;
        }

        int p = partition4(arr, l, r, rd);
        quickSort2ways1(arr, l, p - 1, rd);
        quickSort2ways1(arr, p + 1, r, rd);
    }

    /**
     * 雙路快速排序，避免元素全部相同造成複雜度為O(n^2)問題
     * version 2
     *
     * @param arr 數據陣列
     * @param l 陣列起始位置
     * @param r 陣列中止位置
     * @param rd 隨機類
     */
    private static <T extends Comparable<T>> void quickSort2ways2(T[] arr, int l, int r, Random rd) {
        if (l >= r) {
            return;
        }

        int p = partition5(arr, l, r, rd);
        quickSort2ways2(arr, l, p - 1, rd);
        quickSort2ways2(arr, p + 1, r, rd);
    }

    /**
     * 三路快速排序，專門處理相陣列元素全部相同狀況
     *
     * @param arr 數據陣列
     * @param l 陣列起始位置
     * @param r 陣列終止位置
     * @param rd 隨機類
     */
    private static <T extends Comparable<T>> void quickSort3ways(T[] arr, int l, int r, Random rd) {
        if (l >= r) {
            return;
        }

        swap(arr, l, rd.nextInt(r - l + 1) + l);
        /* arr[l + 1, lt] < v, arr[lt + 1, i - 1] == v, arr[i] current, arr[gt, r] > v */
        int lt = l, gt = r + 1, i = l + 1;
        while (i < gt) {
            if (arr[i].compareTo(arr[l]) < 0) {
                lt++;
                if (i != lt) {
                    swap(arr, i, lt);
                }
                i++;
            } else if (arr[i].compareTo(arr[l]) > 0) {
                swap(arr, i, gt - 1);
                gt--;
            } else {
                i++;
            }
        }
        swap(arr, l, lt);
        /* arr[l, lt - 1] < v, arr[lt, gt - 1] == v, arr[gt, r] > v */
        quickSort3ways(arr, l, lt - 1, rd);
        quickSort3ways(arr, gt, r, rd);
    }

    /**
     * 基礎版partition函式
     *
     * @param arr 數據陣列
     * @param l 陣列起始位置
     * @param r 陣列中止位置
     * @return partition索引
     */
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
     *
     * @param arr 數據陣列
     * @param l 陣列起始位置
     * @param r 陣列中止位置
     * @param rd 隨機類
     * @return partition索引位置
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
     *
     * @param arr 數據陣列
     * @param l 陣列起始位置
     * @param r 陣列中止位置
     * @return partition位置索引
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

    /**
     * 2 ways partition version 1
     *
     * @param arr 數據陣列
     * @param l 陣列起始位置
     * @param r 陣列中止位置
     * @param rd 隨機類
     * @return partition位置索引
     */
    private static <T extends Comparable<T>> int partition4(T[] arr, int l, int r, Random rd) {
        swap(arr, l, rd.nextInt(r - l + 1) + l);
        // arr[l+1...i-1] <= v; arr[j+1...r] >= v
        int i = l + 1;
        int j = r;
        while (i <= j) {
            if (arr[i].compareTo(arr[l]) < 0) {
                i++;
            } else if (arr[j].compareTo(arr[l]) > 0) {
                j--;
            } else {
                swap(arr, i, j);
                i++;
                j--;
            }
        }
        swap(arr, l, j);
        return j;
    }

    /**
     * 2 ways partition version 2
     *
     * @param arr 數據陣列
     * @param l 陣列起始位置
     * @param r 陣列中止位置
     * @param rd 隨機類
     * @return partition位置索引
     */
    public static <T extends Comparable<T>> int partition5(T[] arr, int l, int r, Random rd) {
        int p = l + rd.nextInt(r - l + 1);
        swap(arr, l, p);
        // arr[l+1...i-1] <= v; arr[j+1...r] >= v
        int i = l + 1;
        int j = r;
        while (true) {
            while (i <= j && arr[i].compareTo(arr[l]) < 0) {
                i++;
            }

            while (j >= i && arr[j].compareTo(arr[l]) > 0) {
                j--;
            }

            if (i >= j) {
                break;
            }

            swap(arr, i, j);
            i++;
            j--;
        }
        swap(arr, l, j);
        return j;
    }

    private static <T extends Comparable<T>> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int n = 100000;
        Integer[] arr = ArrayGenerator.generateOrderedArray(n, 1);
        Integer[] arr2 = Arrays.copyOf(arr, n);

        SortingHelper.sortTest("3 Ways Quick Sort1", arr);
        SortingHelper.sortTest("2 Ways Quick Sort2", arr2);
    }

}
