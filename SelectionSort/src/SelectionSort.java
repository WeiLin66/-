public class SelectionSort {
    public static <T extends Comparable> void selectionSort(T[] arr) {
        int head = 0, find = -1;
        T small, temp;

        while (head < arr.length - 1) {
            small = arr[head];
            for (int i = head; i < arr.length - 1; i++) {
                try {
                    if (small.compareTo(arr[i + 1]) > 0) {
                        find = i + 1;
                        small = arr[i + 1];
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }

            if (find != -1) {
                temp = arr[find];
                arr[find] = arr[head];
                arr[head] = temp;
                find = -1;
            }
            head++;
        }

        for (T i : arr) {
            System.out.print(i + " ");
        }
    }

    public static void main(String[] args) {
        Integer[] integers = ArrayGenerator.intArrayGenerator(10);
        selectionSort(integers);
    }
}
