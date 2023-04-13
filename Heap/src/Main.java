import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int n = 15;
        Integer[] ret = new Integer[n];
        Random rnd = new Random();

        for (int i = 0; i < n; i++) {
            ret[i] = rnd.nextInt(n);
        }

        HeapSort.sort(ret);

        for(int i : ret){
            System.out.print(i + " ");
        }
    }
}
