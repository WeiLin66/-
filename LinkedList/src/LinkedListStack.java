public class LinkedListStack<E> implements Stack<E> {

    private MyLinkedList<E> linkedList;

    public LinkedListStack() {
        linkedList = new MyLinkedList<>();
    }

    /**
     * 入棧操作
     * 添加節點到鏈表頭
     *
     * @param value 節點數值
     */
    @Override
    public void push(E value) {
        linkedList.addFirst(value);
    }

    /**
     * 出棧操作
     * 刪除頭節點
     *
     * @return 返回移除節點數值
     */
    @Override
    public E pop() {
        return linkedList.removeFront();
    }

    /**
     * 棧是否為空
     * 判斷鏈表是否為空
     *
     * @return 棧為空則返回真
     */
    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    /**
     * 查看棧頂元素
     * 查看頭節點數值
     *
     * @return
     */
    @Override
    public E peek() {
        return linkedList.getFront();
    }

    /**
     * 獲取棧的大小
     * 計算鏈表長度
     *
     * @return
     */
    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack front: ");
        res.append(linkedList);
        return res.toString();
    }

    public static void main(String[] args) {
        LinkedListStack<Integer> linkedList = new LinkedListStack<>();
        for (int i = 0; i < 5; i++) {
            linkedList.push(i);
            System.out.println(linkedList);
        }

        for (int i = 0; i < 5; i++) {
            linkedList.pop();
            System.out.println(linkedList);
        }
    }
}
