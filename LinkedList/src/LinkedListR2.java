import javafx.util.Pair;

public class LinkedListR2<E> {

    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e, null);
        }

        public Node(){
            this(null, null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }

    // 在鏈表的遞歸實現中，我們不使用虛擬頭結點，也能無差異的處理位置0的問題：）
    private Node head;
    private int size;

    public LinkedListR2(){
        head = null;
        size = 0;
    }

    // 獲取鏈表中的元素個數
    public int getSize(){
        return size;
    }

    // 返回鏈表是否為空
    public boolean isEmpty(){
        return size == 0;
    }

    // 在鏈表的index(0-based)位置添加新的元素e
    public void add(int index, E e){
        if(index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }

        head = add(head, index, e);
        size ++;
    }

    // 在以node為頭結點的鏈表的index位置插入元素e，遞歸算法
    private Node add(Node node, int index, E e){
        if(index == 0) {
            return new Node(e, node);
        }
        node.next = add(node.next, index - 1, e);
        return node;
    }

    // 在鏈表頭添加新的元素e
    public void addFirst(E e){
        add(0, e);
    }

    // 在鏈表末尾添加新的元素e
    public void addLast(E e){
        add(size, e);
    }

    // 獲得鏈表的第index(0-based)個位置的元素
    public E get(int index){
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Illegal index.");
        }
        return get(head, index);
    }

    // 在以node為頭結點的鏈表中，找到第index個元素，遞歸算法
    private E get(Node node, int index){
        if(index == 0) {
            return node.e;
        }
        return get(node.next, index - 1);
    }

    // 獲得鏈表的第一個元素
    public E getFirst(){
        return get(0);
    }

    // 獲得鏈表的最後一個元素
    public E getLast(){
        return get(size - 1);
    }

    // 修改鏈表的第index(0-based)個位置的元素為e
    public void set(int index, E e){
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("Update failed. Illegal index.");
        }
        set(head, index, e);
    }

    // 修改以node為頭結點的鏈表中，第index(0-based)個位置的元素為e，遞歸算法
    private void set(Node node, int index, E e){
        if(index == 0){
            node.e = e;
            return;
        }
        set(node.next, index - 1, e);
    }

    // 查找鏈表中是否有元素e
    public boolean contains(E e){
        return contains(head, e);
    }

    // 在以node為頭結點的鏈表中，查找是否存在元素e，遞歸算法
    private boolean contains(Node node, E e){
        if(node == null) {
            return false;
        }
        if(node.e.equals(e)) {
            return true;
        }
        return contains(node.next, e);
    }

    // 從鏈表中刪除index(0-based)位置的元素, 返回刪除的元素
    public E remove(int index){
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
        }
        Pair<Node, E> res = remove(head, index);
        size --;
        head = res.getKey();
        return res.getValue();
    }

    // 從以node為頭結點的鏈表中，刪除第index位置的元素，遞歸算法
    // 返回值包含兩個元素，刪除後的鏈表頭結點和刪除的值：）
    private Pair<Node, E> remove(Node node, int index){
        if(index == 0) {
            return new Pair<>(node.next, node.e);
        }
        Pair<Node, E> res = remove(node.next, index - 1);
        node.next = res.getKey();
        return new Pair<>(node, res.getValue());
    }

    // 從鏈表中刪除第一個元素, 返回刪除的元素
    public E removeFirst(){
        return remove(0);
    }

    // 從鏈表中刪除最後一個元素, 返回刪除的元素
    public E removeLast(){
        return remove(size - 1);
    }

    // 從鏈表中刪除元素e
    public void removeElement(E e){
        head = removeElement(head, e);
    }

    // 從以node為頭結點的鏈表中，刪除元素e，遞歸算法
    private Node removeElement(Node node, E e){
        if(node == null) {
            return null;
        }

        // 先遞歸處理 node.next
        node.next = removeElement(node.next, e);

        // 再根據 node 的值判斷應該返回什麽
        if(node.e.equals(e)){
            size --;
            return node.next;
        }
        return node;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        Node cur = head;
        while(cur != null){
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL");

        return res.toString();
    }

    public static void main(String[] args) {
        LinkedListR2<Integer> list = new LinkedListR2<>();
        for(int i = 0 ; i < 10 ; i ++) {
            list.addFirst(i);
        }

        while(!list.isEmpty()) {
            System.out.println("removed " + list.removeLast());
        }
    }
}