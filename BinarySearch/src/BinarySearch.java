import java.util.Arrays;

public class BinarySearch {
    public static <T extends Comparable<T>> int searchR(T[] arr, T target) {
        return binarySearchR(arr, target, 0, arr.length - 1);
    }

    public static <T extends Comparable<T>> int search(T[] arr, T target) {
        return binarySearch(arr, target);
    }

    public static <T extends Comparable<T>> int search2(T[] arr, T target) {
        return binarySearch2(arr, target);
    }

    public static <T extends Comparable<T>> int searchUpper(T[] arr, T target) {
        return upper(arr, 0, arr.length, target);
    }

    public static <T extends Comparable<T>> int searchUpper2(T[] arr, T target) {
        return upper2(arr, target);
    }

    public static <T extends Comparable<T>> int search3(T[] arr, T target){
        return findTarget(arr, target);
    }

    public static <T extends Comparable<T>> int searchUpperFloor(T[] arr, T target) {
        return upperFloor2(arr, target);
    }

    public static <T extends Comparable<T>> int searchCeil(T[] arr, T target){
            return upperCeil(arr, target);
    }

    public static <T extends Comparable<T>> int searchLowerCeil(T[] arr, T target) {
        return lowerCeil(arr, target);
    }

    public static <T extends Comparable<T>> int searchLower(T[] arr, T target) {
        return lower(arr, target);
    }

    public static <T extends Comparable<T>> int searchLowerFloor(T[] arr, T target) {
        return lowerFloor(arr, target);
    }

    /**
     * 遞迴版本二分搜索法
     *
     * @param arr 數據陣列
     * @param target 目標
     * @param l 左邊界
     * @param r 右邊界
     * @return 目標索引
     */
    private static <T extends Comparable<T>> int binarySearchR(T[] arr, T target, int l, int r) {
        if (l > r) {
            return -1;
        }

        int mid = l + (r - l) / 2;

        if (arr[mid].compareTo(target) == 0) {
            return mid;
        }

        if (arr[mid].compareTo(target) > 0) {
            return binarySearchR(arr, target, l, mid - 1);
        }
        return binarySearchR(arr, target, mid + 1, r);
    }

    /**
     * 迭代版本二分搜索法
     *
     * @param arr 數據陣列
     * @param target 目標
     * @return 目標索引
     */
    private static <T extends Comparable<T>> int binarySearch(T[] arr, T target) {
        int l = 0, r = arr.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (arr[mid].compareTo(target) == 0) {
                return mid;
            }

            if (arr[mid].compareTo(target) > 0) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 迭代版本二分搜索法
     * 改變循環不變量的定義版本
     *
     * @param arr 數據陣列
     * @param target 目標
     * @return 目標索引
     */
    public static <T extends Comparable<T>> int binarySearch2(T arr[], T target) {
        int r = arr.length;
        int l = 0;

        /* 在arr[l, r)的範圍中搜索target */
        while (l < r) {

            int mid = l + (r - l) / 2;

            if (arr[mid].compareTo(target) == 0) {
                return mid;
            } else if (arr[mid].compareTo(target) < 0) {
                r = mid; // 在arr[l ... mid)的範圍中搜索target
            } else {
                l = mid + 1; // 在arr[mid+1 ... r)的範圍中搜索target
            }
        }

        return -1;
    }

    /**
     * 遞迴查找大於target的最小值
     *
     * @param arr 數據陣列
     * @param l 陣列起始位置
     * @param r 陣列終止位置
     * @param target 目標
     * @return 目標索引
     */
    private static <T extends Comparable<T>> int upper(T[] arr, int l, int r, T target) {
        if (l >= r) {
            return r;
        }

        int mid = l + (r - l) / 2;

        if (arr[mid].compareTo(target) > 0) {
            return upper(arr, l, mid, target);
        } else {
            return upper(arr, mid + 1, r, target);
        }
    }

    /**
     * 迭代查找大於target的最小值
     *
     * @param arr 數據陣列
     * @param target 目標
     * @return 目標索引
     */
    private static <T extends Comparable<T>> int upper2(T[] arr, T target) {
        int l = 0, r = arr.length;

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid].compareTo(target) <= 0) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return r;
    }

    /**
     * 若陣列存在元素，返回最大索引，若不存在元素則返回lower
     *
     * @param arr 數據陣列
     * @param target 目標
     * @return 目標索引
     */
    private static <T extends Comparable<T>> int upperFloor(T[] arr, T target) {
        int index = lower(arr, target);

        if (index == arr.length - 1 || arr[index + 1].compareTo(target) != 0) {
            return index;
        }

        return upperCeil(arr, target);
    }

    /**
     * upperFloor version 2
     *
     * @param arr 數據陣列
     * @param target 目標
     * @return 目標索引
     */
    private static <T extends Comparable<T>> int upperFloor2(T[] arr, T target) {
        int l = -1, r = arr.length - 1;

        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            if (arr[mid].compareTo(target) > 0) {
                r = mid - 1;
            } else {
                l = mid;
            }
        }

        return l;
    }

    /**
     * 查找小於target的最大值
     *
     * @param arr 數據陣列
     * @param target 目標
     * @return 目標索引
     */
    private static <T extends Comparable<T>> int lower(T[] arr, T target) {
        int l = -1, r = arr.length - 1;

        while (l < r) {
            int mid = l + (r - l + 1) / 2; /* 為了避免進入死循環，使用向上取整 */
            if (arr[mid].compareTo(target) >= 0) {
                r = mid - 1;
            } else {
                l = mid;
            }
        }
        return l;
    }

    /**
     * 若陣列中存在元素，返回最大索引，否則返回upper
     * @param arr 數據陣列
     * @param target 目標
     * @return 目標索引
     */
    private static <T extends Comparable<T>> int upperCeil(T[] arr, T target) {
        int upper = searchUpper2(arr, target);

        if (upper - 1 >= 0 && arr[upper - 1].compareTo(target) == 0) {
            return upper - 1;
        }

        return upper;
    }

    /**
     * 若陣列中存在元素，返回最小索引，否則返回upper
     *
     * @param arr 數據陣列
     * @param target 目標
     * @return 目標索引
     */
    private static <T extends Comparable<T>> int lowerCeil(T[] arr, T target) {
        int l = 0, r = arr.length;

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid].compareTo(target) < 0) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return r;
    }

    /**
     * 若陣列存在元素，返回最小索引，若不存在則返回lower
     *
     * @param arr 數據陣列
     * @param target 目標
     * @return 目標索引
     */
    private static <T extends Comparable<T>> int lowerFloor(T[] arr, T target) {
        int index = lower(arr, target);

        if (index != arr.length - 1 && arr[index + 1].compareTo(target) == 0) {
            return index + 1;
        }

        return index;
    }

    /**
     * 若存在>=target元素，返回最小索引，否則返回-1
     *
     * @param arr 數據陣列
     * @param target 目標
     * @return 目標索引
     */
    private static <T extends Comparable<T>> int findTarget(T arr[], T target) {
        int r = arr.length;
        int l = 0;

        while (l < r) {
            int mid = l + (r - l) / 2;

            if (arr[mid].compareTo(target) >= 0) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        if (l < arr.length && arr[l].compareTo(target) == 0) {
            return l;
        }

        return -1;
    }

    public static void main(String[] args) {

    }
}
