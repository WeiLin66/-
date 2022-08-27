public class Main {

    public static void main(String[] args) {

        Integer[] arr = {3, -4, 8, 10, 2, -8, 0, 15};

        // 找區間最大值
        SQRTDecomposition<Integer> sqrt1 = new SQRTDecomposition<>(arr, new Comparator<Integer>() {
            public Integer compare(Integer a, Integer b) {
                return Math.max(a, b);
            }
        });

        // 找區間最小值
        SQRTDecomposition<Integer> sqrt2 = new SQRTDecomposition<>(arr, new Comparator<Integer>() {
            public Integer compare(Integer a, Integer b) {
                return Math.min(a, b);
            }
        });

        // lambda寫法
        SQRTDecomposition<Integer> sqrt3 = new SQRTDecomposition<>(arr, Math::max);
        SQRTDecomposition<Integer> sqrt4 = new SQRTDecomposition<>(arr, Math::min);

        System.out.println("Max: " + sqrt1.find(1, 6));
        System.out.println("Min: " + sqrt2.find(1, 6));
        System.out.println("=================================");
        System.out.println("Max: " + sqrt3.find(1, 6));
        System.out.println("Min: " + sqrt4.find(1, 6));
    }
}

