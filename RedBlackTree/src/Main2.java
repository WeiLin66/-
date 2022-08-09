import java.util.ArrayList;
import java.util.Random;

public class Main2 {

    public static void main(String[] args) {
        int nums = 1000000;
        ArrayList<Integer> arrayList = new ArrayList<>();
        Random rd = new Random();

        for (int i = 0; i < nums; i++) {
            arrayList.add(rd.nextInt(nums)); // 添加隨機數
        }

        /* BST */
        long startTime = System.nanoTime();
        BST<Integer, Integer> bst = new BST<>();
        for (int i = 0; i < nums; i++) {
            bst.add(arrayList.get(i), null);
        }
        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("BST: " + time + " s");

        /* AVL */
        startTime = System.nanoTime();
        AVLTree<Integer, Integer> avl = new AVLTree<>();
        for (int i = 0; i < nums; i++) {
            avl.add(arrayList.get(i), null);
        }
        endTime = System.nanoTime();
        time = (endTime - startTime) / 1000000000.0;
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
