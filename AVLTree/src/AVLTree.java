import java.util.ArrayList;

/**
 * 使用映射版本的二元樹來建構AVL樹
 */

public class AVLTree<K extends Comparable<K>, V> {

    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public int height; // 樹高

        public Node(K key, V value, Node right, Node left) {
            this.key = key;
            this.value = value;
            this.right = right;
            this.left = left;
            this.height = 1;
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

    public AVLTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 檢查是否為一棵二元樹
     *
     * @return
     */
    public boolean isBST() {
        if (root == null) {
            return true;
        }

        ArrayList<K> arr = new ArrayList<>();
        inOrder(root, arr);

        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i).compareTo(arr.get(i + 1)) > 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * 中序走訪
     * @param node
     * @param arr
     */
    private void inOrder(Node node, ArrayList<K> arr) {
        if (node == null) {
            return;
        }

        inOrder(node.left, arr);
        arr.add(node.key);
        inOrder(node.right, arr);
    }

    public boolean isBalanced(){
        return isBalanced(root);
    }

    private boolean isBalanced(Node node){
        if(node == null){
            return true;
        }

        if(Math.abs(getBalanceFactor(node)) > 1){
            return false;
        }

        return isBalanced(node.left) && isBalanced(node.right);
    }


    /**
     * 獲取高度
     */
    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

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
        if (node == null) {
            throw new IllegalArgumentException("key : " + key + " doesn't exist!");
        }

        node.value = newValue;
    }

    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }

        return getHeight(node.left) - getHeight(node.right);
    }

    // 對節點y進行向右旋轉操作，返回旋轉後新的根節點x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋轉 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private Node rightRotate(Node y){
        Node x = y.left;
        Node T3 = x.right;

        // 右旋轉
        x.right = y;
        y.left = T3;

        // 更新height
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));

        return x;
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

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        // 計算平衡因子
        int balanceFactor = getBalanceFactor(node);

        if (Math.abs(balanceFactor) > 1) {
            System.out.println("unbalanced: " + balanceFactor);
        }

        // 右旋轉
        if(balanceFactor > 1 && getHeight(node.left) >= 0){
            return rightRotate(node);
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
