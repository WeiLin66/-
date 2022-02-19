/**
 * 簡單的線性查找測試程式
 */
public class LinearSearch {

    /**
     *
     * @param arr
     * 泛型陣列內容
     * @param target
     * 陣列中欲查找目標
     * @return
     * 返回找到的元素index，若找不到則返回-1
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

    public static void main(String[] args) {
        /* 整數類型線性查找 */
        Integer[] arr = {12, 65, 98, 4, 77, 14};
        System.out.println("Index : " + search(arr, 4));
        System.out.println("Index : " + search(arr, 666));

        System.out.println("=======================================");

        /* 浮點類型線性查找 */
        Double[] arr2 = {12.3, 78.6, 9.9, 45.3, 12.6, 22.2};
        System.out.println("Index : " + search(arr2, 9.9));
        System.out.println("Index : " + search(arr2, 22.2));

        System.out.println("=======================================");

        /* 自定義類線性查找 */
        Elements[] arr3 = new Elements[3];
        Elements target = new Elements("S003", 3);
        arr3[0] = new Elements("s001", 1);
        arr3[1] = new Elements("s002", 2);
        arr3[2] = new Elements("s003", 3);

        System.out.println("Index : " + search(arr3, target));
    }

}
