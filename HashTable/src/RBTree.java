public class RBTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        public K key;
        public V value;
        public Node left;
        public Node right;
        private boolean color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            right = null;
            left = null;
            color = RED;
        }
    }

    private Node root;
    private int size;

    public RBTree() {
        root = null;
        size = 0;
    }

    public void add(K key, V value) {
        root = add(root, key, value);
        root.color = BLACK;
    }

    public V remove(K key) {
        Node node = getNode(root, key);
        if(node != null){
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return (node == null) ? null : node.value;
    }

    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException("key : " + key + " doesn't exist!");
        }

        node.value = newValue;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 判斷節點node顏色
     */
    private boolean isRed(Node node) {
        if (node == null) {
            return BLACK;
        }
        return node.color;
    }

    //   node                     x
    //  /   \     左旋轉         /  \
    // T1   x   --------->   node   T3
    //     / \              /   \
    //    T2 T3            T1   T2
    private Node leftRotate(Node node) {
        Node x = node.right;
        Node T2 = x.left;

        x.left = node;
        node.right = T2;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    //     node                   x
    //    /   \     右旋轉       /  \
    //   x    T2   ------->   y   node
    //  / \                       /  \
    // y  T1                     T1  T2
    private Node rightRotate(Node node) {
        Node x = node.left;
        Node T1 = x.right;

        x.right = node;
        node.left = T1;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    /**
     * 對3節點添加元素，進行顏色翻轉
     */
    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) >= 0) {
            node.right = add(node.right, key, value);
        } else {
            node.value = value;
        }

        if(!isRed(node.left) && isRed(node.right)){
            node = leftRotate(node);
        }

        if(isRed(node.left) && isRed(node.left.left)){
            node = rightRotate(node);
        }

        if(isRed(node.left) && isRed(node.right)){
            flipColors(node);
        }

        return node;
    }

    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) == 0) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else {
            return getNode(node.right, key);
        }
    }

    private Node min(Node node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            Node ret = node.right;
            node.right = null;
            size--;
            return ret;
        }

        node.left = removeMin(node.left);
        return node;
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
        } else if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
        } else { // node.key == key
            if (node.left == null) { // 只有右子樹
                Node ret = node.right;
                node.right = null;
                size--;
                return ret;
            } else if (node.right == null) { // 只有左子樹
                Node ret = node.left;
                node.left = null;
                size--;
                return ret;
            }
            /* 同時擁有左右子樹 */
            Node successor = min(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }

        return node;
    }

}
