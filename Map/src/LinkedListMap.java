public class LinkedListMap<K, V> implements Map<K, V>{

    private class Node {
        public K key;
        public V value;
        public Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key) {
            this(key, null,  null);
        }

        public Node() {
            this(null, null, null);
        }

        @Override
        public String toString() {
            return this.key.toString() + " : " + this.value.toString();
        }

    }

    private Node dummyHead;
    private int size;

    public LinkedListMap(){

    }

    @Override
    public void add(K key, V value) {
        if(getNode(key) != null){
            return;
        }

        dummyHead.next = new Node(key, value, dummyHead.next);
        size++;
    }

    @Override
    public V remove(K key) {
        Node cur = dummyHead;
        while (cur.next != null){
            if(cur.next.key.equals(key)){
                Node res = cur.next;
                cur.next = res.next;
                res.next = null;
                size--;
                return res.value;
            }
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return (node != null) ? node.value : null;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(key);
        if(node == null){
            throw new IllegalArgumentException("key : " + key + " doesn't exist!");
        }

        node.value = newValue;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Node getNode(K key){
        Node cur = dummyHead;
        while(cur != null){
            if(cur.key.equals(key)){
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }
}
