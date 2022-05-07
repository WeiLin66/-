import java.util.Arrays;

public class BinarySearch {
    public static <T extends Comparable<T>> int searchR(T[] arr, T target) {
        return binarySearchR(arr, target, 0, arr.length - 1);
    }

    public static <T extends Comparable<T>> int search(T[] arr, T target) {
        return binarySearch(arr, target);
    }

    public static <T extends Comparable<T>> int searchUpper(T[] arr, T target) {
        return upper(arr, 0, arr.length, target);
    }

    public static <T extends Comparable<T>> int searchUpper2(T[] arr, T target) {
        return upper2(arr, target);
    }

    public static <T extends Comparable<T>> int searchCeil(T[] arr, T target) {
        return ceil(arr, target);
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

    public static <T extends Comparable<T>> int searchUpperFloor(T[] arr, T target) {
        return upperFloor2(arr, target);
    }

    public static <T extends Comparable<T>> int findTarget(T arr[], T target) {
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

    private static <T extends Comparable<T>> int ceil(T[] arr, T target) {
        int upper = searchUpper2(arr, target);

        if (upper - 1 >= 0 && arr[upper - 1].compareTo(target) == 0) {
            return upper - 1;
        }

        return upper;
    }

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

    private static <T extends Comparable<T>> int lowerFloor(T[] arr, T target) {
        int index = lower(arr, target);

        if (index != arr.length - 1 && arr[index + 1].compareTo(target) == 0) {
            return index + 1;
        }

        return index;
    }

    private static <T extends Comparable<T>> int upperFloor(T[] arr, T target) {
        int index = lower(arr, target);

        if (index == arr.length - 1 || arr[index + 1].compareTo(target) != 0) {
            return index;
        }

        return ceil(arr, target);
    }

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

    public static void main(String[] args) {
        Integer[] arr = {1, 1, 3, 3, 5, 5};
        for (int i = 0; i <= 6; i++) {
            System.out.print(findTarget(arr, i) + " ");
        }
        System.out.println();
    }
}
