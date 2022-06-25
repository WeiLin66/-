import java.util.Arrays;

public class ShellSort {
    private ShellSort() {
    }

    /**
     * 希爾排序法
     *
     * @param data
     * @param <T>
     */
    static <T extends Comparable<T>> void sort(T[] data) {
        int len = data.length;

        for (int i = len / 2; i > 0; i /= 2) {
            for (int j = i; j < len; j++) {
                int k;
                T temp = data[j];
                for (k = j; k >= i; k -= i) {
                    if (temp.compareTo(data[k - i]) < 0) {
                        data[k] = data[k - i];
                    } else {
                        break;
                    }
                }
                data[k] = temp;
            }
        }
    }

    /**
     * 希爾排序法2
     *
     * @param data
     * @param <T>
     */
    static <T extends Comparable<T>> void sort2(T[] data) {
        int h = data.length / 2;
        while (h >= 1) {
            for (int start = 0; start < h; start++) {
                for (int i = start + h; i < data.length; i += h) {
                    T temp = data[i];
                    int j;
                    for (j = i; j - h >= 0 && temp.compareTo(data[j - h]) < 0; j -= h) {
                        data[j] = data[j - h];
                    }
                    data[j] = temp;
                }
            }
            h /= 2;
        }
    }

    /**
     * 希爾排序法3
     *
     * @param data
     * @param <T>
     */
    static <T extends Comparable<T>> void sort3(T[] data) {
        int h = data.length / 2;
        while (h >= 1) {
            for (int i = h; i < data.length; i++) {
                T temp = data[i];
                int j;
                for (j = i; j - h >= 0 && temp.compareTo(data[j - h]) < 0; j -= h) {
                    data[j] = data[j - h];
                }
                data[j] = temp;
            }

            h /= 2;
        }
    }

    /**
     * 使用不同步長序列的希爾排序
     *
     * @param data
     * @param <T>
     */
    static <T extends Comparable<T>> void sort4(T[] data) {
        int h = 1;

        /* 1, 4, 13, 40, 121... */
        while (h < data.length) {
            h = h * 3 + 1;
        }

        while (h >= 1) {
            for (int i = h; i < data.length; i++) {
                T temp = data[i];
                int j;
                for (j = i; j - h >= 0 && temp.compareTo(data[j - h]) < 0; j -= h) {
                    data[j] = data[j - h];
                }
                data[j] = temp;
            }

            h /= 3;
        }
    }

    static <T extends Comparable<T>> void swap(T[] data, int i, int j) {
        T temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public static void main(String[] args) {
        int n = 100000;
        Integer[] data = ArrayGenerator.intArrayGenerator(n, n);
        Integer[] data2 = Arrays.copyOf(data, n);
        Integer[] data3 = Arrays.copyOf(data, n);
        Integer[] data4 = Arrays.copyOf(data, n);

        SortingHelper.sortTest("Shell Sort", data);
        SortingHelper.sortTest("Shell Sort2", data2);
        SortingHelper.sortTest("Shell Sort3", data3);
        SortingHelper.sortTest("Shell Sort4", data4);
    }
}
