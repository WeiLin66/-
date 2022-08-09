import java.util.ArrayList;

public class Main3 {

    public static void main(String[] args) {
        int nums = 10000000;
        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i = 0; i < nums; i++) {
            arrayList.add(i); // 按順序添加
        }

        /* AVL */
        long startTime = System.nanoTime();
        AVLTree<Integer, Integer> avl = new AVLTree<>();
        for (int i = 0; i < nums; i++) {
            avl.add(arrayList.get(i), null);
        }
        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("AVL: " + time + " s");

        /* RBT */
        startTime = System.nanoTime();
        RBTree<Integer, Integer> rbt = new RBTree<>();
        for (int i = 0; i < nums; i++) {
            rbt.add(arrayList.get(i), null);
        }
        endTime = System.nanoTime();
        time = (endTime - startTime) / 1000000000.0;
        System.out.println("RBT: " + time + " s");
    }
}
