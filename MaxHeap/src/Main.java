import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int n = 50;
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random rnd = new Random();

        for (int i = 0; i < n; i++) {
            maxHeap.add(rnd.nextInt(n));
        }

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = maxHeap.extractMax();
        }

        for (int i = 0; i < n; i++) {
            if(i > 0 && arr[i] > arr[i-1]){
                throw new IllegalArgumentException("Error!");
            }
            System.out.print(arr[i] + " ");
        }
    }
}
