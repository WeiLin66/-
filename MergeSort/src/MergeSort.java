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

    // 底層實現
    private static <T extends Comparable<T>> void mergeSort(T[] arr, int l, int r) {
        if (l >= r) {
//            System.out.println("只剩單一元素: " + arr[l]);
            return;
        }

        int mid = l + (r - l) / 2; // 避免大資料量時整數溢出
//        System.out.print(recursiveDepth(r - l));
//        System.out.print("index: " + l + "~" + mid + " 排序前: ");
//        arrayPrint(arr, l, mid);

        mergeSort(arr, l, mid);

//        System.out.print(recursiveDepth(r - l));
//        System.out.print("index: " + l + "~" + mid + " 排序後: ");
//        arrayPrint(arr, l, mid);

//        System.out.print(recursiveDepth(r - l));
//        System.out.print("index: " + (mid + 1) + "~" + r + " 排序前: ");
//        arrayPrint(arr, (mid + 1), r);

        mergeSort(arr, mid + 1, r);

//        System.out.print(recursiveDepth(r - l));
//        System.out.print("index: " + (mid + 1) + "~" + r + " 排序後: ");
//        arrayPrint(arr, (mid + 1), r);

//        System.out.print(recursiveDepth(r - l));
//        System.out.print("index: " + l + "~" + r + " Merge前: ");
//        arrayPrint(arr, l, r);

        merge1(arr, l, mid, r);

//        System.out.print(recursiveDepth(r - l));
//        System.out.print("index: " + l + "~" + r + " Merge後: ");
//        arrayPrint(arr, l, r);
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
     * @param arr
     * @param l
     * @param mid
     * @param r
     * @param temp
     * @param <T>
     */
    private static <T extends Comparable<T>> void merge2(T[] arr, int l, int mid, int r, T[] temp) {
        System.arraycopy(arr, l, temp, l, (r - l));
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

    private static String recursiveDepth(int index) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i <= index; i++) {
            res.append("--");
        }
        return res.toString();
    }

    private static <T extends Comparable<T>> void arrayPrint(T[] arr, int l, int r) {
        for (int i = l; i <= r; i++) {
            if (i == l) {
                System.out.print("[");
            }
            System.out.print(arr[i]);
            if (i != r) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        int n = 10000000;
        Integer[] arr1 = ArrayGenerator.generateOrderedArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr1, n);

        SortingHelper.sortTest("Merge Sort", arr1);
        SortingHelper.sortTest("Merge Sort2", arr2);
    }
}
