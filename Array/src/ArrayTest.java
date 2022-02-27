/**
 * Array測試類
 */
public class ArrayTest {
    public static void main(String[] args) {
        Array str = new Array();
        for (int i = 0; i < 10; i++) {
            str.addLast(i);
        }
        str.addLast(11);
        str.addLast(12);
        System.out.println(str);

        str.removeFirst();
        str.removeFirst();
        str.removeFirst();
        System.out.println(str);
    }
}
