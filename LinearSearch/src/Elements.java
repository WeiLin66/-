/**
 * 自定義類
 */
public class Elements {
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
     * @param obj
     * 傳入比較對象
     * @return
     * 若兩對象屬性相同則返回true，否則返回false。屬性id比較無關大小寫
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
     * @return Elements類資訊
     */
    @Override
    public String toString() {
        return "Elements[ ID : " + getId() + ", Number : " + getNumber() + " ]";
    }

}
