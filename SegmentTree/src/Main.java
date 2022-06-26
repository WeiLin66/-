public class Main {
    public static void main(String[] args) {
        Integer[] arr = {3, -4, 8, 10, 2, -8, 0, 15};

//    SegmentTree<Integer> segTree = new SegmentTree<>(arr, new Merger<Integer>() {
//        public Integer merge(Integer a, Integer b) {
//            return a + b;
//        }
//    });

        SegmentTree<Integer> segTree = new SegmentTree<>(arr, (a, b) -> a + b);
        System.out.println(segTree);
    }
}
