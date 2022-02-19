/**
 * 陣列產生類
 */
public class ArrayGenerator {
    /**
     * 整數類型的陣列產生器
     * @param n 陣列長度
     * @return 整數類型陣列對象
     */
    public static Integer[] intArrayGenerator(int n) {
        Integer[] integers = new Integer[n];
        for (int i = 0; i < n; i++) {
            integers[i] = (int) (Math.random() * (n - 1) + 1);
        }

        return integers;
    }

    /**
     * 浮點數類型的陣列產生器
     * @param n 陣列長度
     * @return 浮點數類型陣列對象
     */
    public static Double[] doubleArrayGenerator(int n) {
        Double[] doubles = new Double[n];
        for (int i = 0; i < n; i++) {
            doubles[i] = Math.random() % 100 + 1;
        }

        return doubles;
    }

    /**
     * Elements類型的陣列產生器
     * @param n 陣列長度
     * @return Elements類型陣列對象
     */
    public static Elements[] elementsArrayGenerator(int n) {
        Elements[] elements = new Elements[n];
        for (int i = 0; i < n; i++) {
            String id = "s" + i;
            elements[i] = new Elements(id, i + 1);
        }

        return elements;
    }
}
