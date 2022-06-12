public class BubbleSort {

    private BubbleSort() {
    }

    private static <T extends Comparable<T>> void sort(T[] arr) {
        for (int i = 0; i + 1 < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    /**
     * 若剩餘的數據都沒有交換，那直接退出(判斷是否有序)
     *
     * @param arr
     * @param <T>
     */
    private static <T extends Comparable<T>> void sort2(T[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean isSwapped = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    swap(arr, j, j + 1);
                    isSwapped = true;
                }
            }
            if (!isSwapped) {
                break;
            }
        }
    }

    /**
     * 依據最後交換位置調整循環次數
     *
     * @param arr
     * @param <T>
     */
    private static <T extends Comparable<T>> void sort3(T[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int lastSwapped = 0;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    swap(arr, j, j + 1);
                    lastSwapped = j + 1;
                }
            }
            i = arr.length - 1 - lastSwapped;
        }
    }

    /**
     * @param arr
     * @param <T>
     */
    private static <T extends Comparable<T>> void reverseSort(T[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            int lastSwapped = arr.length - 1;
            for (int j = arr.length - 1; j >= arr.length - i; j--) {
                if (arr[j].compareTo(arr[j - 1]) < 0) {
                    swap(arr, j, j - 1);
                    lastSwapped = j;
                }
            }
            i = arr.length - lastSwapped;
        }
    }

    public static <T extends Comparable<T>> void reverseSort2(T[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int lastSwappedIndex = arr.length - 1;
            for (int j = arr.length - 1; j > i; j--)
                if (arr[j - 1].compareTo(arr[j]) > 0) {
                    swap(arr, j - 1, j);
                    lastSwappedIndex = j - 1;
                }

            i = lastSwappedIndex;
        }
    }

    private static <T extends Comparable<T>> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Integer[] res = {10, 5, 1, 2, 9, 7, 4, 3};
        BubbleSort.sort3(res);

        for (int i : res) {
            System.out.println(i);
        }

    }
}
