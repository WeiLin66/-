public class LinearSearch {
    private int[] arr;

    public LinearSearch(int[] arr) {
        setArr(arr);
    }

    public int[] getArr() {
        return this.arr;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    public int search(int target) {
        for (int i = 0; i < getArr().length; i++) {
            if (getArr()[i] == target) {
                return i;
            }
        }
        return -1;
    }

}
