/**
 * 簡單的線性查找測試程式
 */
public class LinearSearch {

    /**
     * 該方法為複雜度O(n)的線性查找方法
     * @param arr    泛型陣列內容
     * @param target 陣列中欲查找目標
     * @return 返回找到的元素index，若找不到則返回-1
     */
    public static <T> int search(T[] arr, T target) {
        for (int i = 0; i < arr.length; i++) {
            try {
                if (arr[i].equals(target)) {
                    return i;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    /**
     * 該方法用於計算查找元素花費的時間，並對相同的查找執行100次，求其
     * 總時間消耗與平均消耗。目的為觀察資料數量與時間消耗之間的關係
     * @param numbers 生成陣列元素數量
     * @param target 查找目標元素
     */
    public static <T> void timeConsume(int numbers, T target) {
        long startTime = System.nanoTime();

        for (int i = 0; i < 100; i++) {
            if (target.getClass() == Integer.class) {
                search(ArrayGenerator.intArrayGenerator(numbers), target);
            } else if (target.getClass() == Double.class) {
                search(ArrayGenerator.doubleArrayGenerator(numbers), target);
            } else if (target.getClass() == Elements.class) {
                search(ArrayGenerator.elementsArrayGenerator(numbers), target);
            } else {
                System.out.println("No such data type search method...");
            }
        }

        long endTime = System.nanoTime();
        double time = ((double) (endTime - startTime)) / 1000000000;

        System.out.println("For numbers : " + numbers + ", runs 100 times, total consume : " + time + "s");
        System.out.println("Average time consume : " + time / 100);
    }

    public static void main(String[] args) {
        int[] times = {
            100000, 1000000
        } ;
        for (int i : times) {
            timeConsume(i, 10);
        }
    }

}
