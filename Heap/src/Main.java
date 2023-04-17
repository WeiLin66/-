import java.util.Random;

public class Main {
    public static void main(String[] args) {

        MaxHeap<Integer> maxheap = new MaxHeap<>();
        for(int i=0; i<10; ++i) {
            maxheap.add(i);
        }

        System.out.println(maxheap.findMax());
    }
}
