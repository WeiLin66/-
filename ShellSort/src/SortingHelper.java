/**
 * 排序測試類
 */
public class SortingHelper {
    private SortingHelper() {
    }

    /**
     * 判斷陣列是否成功排序，否則拋出異常
     *
     * @param arr 對象陣列
     * @return 布林值true或false
     */
    public static <T extends Comparable<T>> boolean isSort(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1].compareTo(arr[i]) > 0) {
                throw new RuntimeException("sorting fail");
            }
        }
        return true;
    }

    /**
     * 測試排序算法性能，可以依照傳入的name決定排序算法
     *
     * @param name 排序算法名
     * @param arr  對象陣列
     */
    public static <T extends Comparable<T>> void sortTest(String name, T[] arr) {
        long startTime = System.nanoTime();

        switch (name) {
            case "Shell Sort":
                ShellSort.sort(arr);
                break;

            case "Shell Sort2":
                ShellSort.sort2(arr);
                break;

            case "Shell Sort3":
                ShellSort.sort3(arr);
                break;

            case "Shell Sort4":
                ShellSort.sort4(arr);
                break;

            default:
                System.out.println("No Such Sort...");
                break;
        }

        if (!isSort(arr)) {
            throw new RuntimeException(name + " fail");
        }

        long endTime = System.nanoTime();
        double time = ((double) (endTime - startTime) / 1000000000);
        System.out.format("%s, for data size : %d, total time consumption : %f\n", name, arr.length, time);
    }

}
