import java.util.Arrays;

public class SQRTDecomposition<T> {

    private T[] data, block;
    private int N;
    private int B; // 分塊數量
    private int nB; // 每塊數量
    private Comparator<T> compare;

    public SQRTDecomposition(T[] arr, Comparator<T> compare) {
        N = arr.length;
        if (N == 0) {
            return;
        }

        nB = (int) Math.sqrt(N);
        B = N / nB + ((N % nB == 0) ? 0 : 1);
        data = Arrays.copyOf(arr, N);
        this.compare = compare;

        block = (T[]) new Object[B];
        block[0] = data[0];

        for (int i = 0; i < N; i++) {
            if(block[i / nB] == null){
                block[i / nB] = data[i];
            }
            block[i / nB] = this.compare.compare(data[i], block[i / nB]);
        }
    }

    public T find(int left, int right) {

        if(left < 0 || left >= N || right < 0 || right >= N || left > right){
            throw new IllegalArgumentException("Argument could be wrong!");
        }

        T res;
        int rangeLeft = left / nB, rangeRight = right / nB;

        res = data[left];

        if (rangeLeft == rangeRight) {
            for (int i = left; i <= right; i++) {
                res = this.compare.compare(data[i], res);
            }
            return res;
        }


        for (int i = left; i < (rangeLeft + 1) * nB; i++) {
            res = this.compare.compare(data[i], res);
        }

        for (int i = rangeLeft + 1; i < rangeRight; i++) {
            res = this.compare.compare(block[i], res);
        }

        for (int i = (rangeRight) * nB; i <= right; i++) {
            res = this.compare.compare(data[i], res);
        }

        return res;
    }

}
