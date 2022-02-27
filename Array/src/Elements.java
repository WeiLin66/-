/**
 * 自定義Elements類
 */
public class Elements implements Comparable<Elements> {
    private String id;
    private int number;

    public Elements(String id, int number) {
        setId(id);
        setNumber(number);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * 重寫的equals方法，用於比對成員屬性是否相同
     *
     * @param obj 傳入比較對象
     * @return 若兩對象屬性相同則返回true，否則返回false。屬性id比較無關大小寫
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            System.out.println("class is null...");
            return false;
        }

        if (obj.getClass() != Elements.class) {
            System.out.println("Wrong class type...");
            return false;
        }

        return (getId().equalsIgnoreCase(((Elements) obj).getId()) && getNumber() == ((Elements) obj).getNumber());

    }

    /**
     * 重寫的toString方法，用於打印類資訊
     *
     * @return Elements類資訊
     */
    @Override
    public String toString() {
        return "Elements[ ID : " + getId() + ", Number : " + getNumber() + " ]";
    }

    /**
     * 實現Comparable接口中的compareTo()方法
     *
     * @param o Elements類型對象
     * @return 正數如果當前類的number較大；負數傳入的對象number較大；0兩者的number相等
     */
    @Override
    public int compareTo(Elements o) {
        return this.getNumber() - o.getNumber();
    }

    public static void main(String[] args) {
        Array<Elements> arr1 = new Array<>();
        arr1.addLast(new Elements("Alice", 100));
        arr1.addLast(new Elements("Bob", 66));
        arr1.addLast(new Elements("Charlie", 88));

        System.out.println(arr1);
    }
}