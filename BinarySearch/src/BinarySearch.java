public class BinarySearch {
    public static <T extends Comparable<T>> int searchR(T[] arr, T target) {
        return binarySearchR(arr, target, 0, arr.length - 1);
    }

    public static <T extends Comparable<T>> int search(T[] arr, T target) {
        return binarySearch(arr, target);
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
            r = mid - 1;
        } else if (arr[mid].compareTo(target) < 0) {
            l = mid + 1;
        }

        return binarySearchR(arr, target, l, r);
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

    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("Index find at: " + search(arr, 10));
    }
}
