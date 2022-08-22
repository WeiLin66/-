public class BST<K extends Comparable<K>, V>  {

    private class Node {
        public K key;
        public V value;
        public Node left;
        public Node right;

        public Node(K key, V value, Node right, Node left) {
            this.key = key;
            this.value = value;
            this.right = right;
            this.left = left;
        }

        public Node(K key) {
            this(key, null, null, null);
        }

        public Node() {
            this(null, null, null, null);
        }

        @Override
        public String toString() {
            return this.key.toString() + " : " + this.value.toString();
        }

    }

    private Node root;
    private int size;

    public void add(K key, V value) {
        root = add(root, key, value);
    }

    public V remove(K key) {
        root = remove(root, key);
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
        if(node == null){
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

    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value, null, null);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) >= 0) {
            node.right = add(node.right, key, value);
        } else {
            node.value = value;
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

    private Node remove(Node node, K key){
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
