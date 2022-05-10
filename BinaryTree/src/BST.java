public class BST<T extends Comparable<T>> {

    private class Node {
        public T value;
        public Node left, right;

        public Node(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private Node root = null;
    public int size;

    public BST() {
        this.root = null;
        setSize(0);
    }

    public BST(T value) {
        root = new Node(value);
        setSize(1);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void add(T value) {
        if (root == null) {
            root = new Node(value);
            setSize(1);
            return;
        }
        add(root, value);
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void add2(T value) {
        root = add2(root, value);
    }

    /**
     * 非遞迴實現的add
     *
     * @param value
     */
    public void add3(T value) {
        if (root == null) {
            root = new Node(value);
            setSize(getSize() + 1);
            return;
        }

        Node temp = root;
        while (true) {
            if (value.compareTo(temp.value) < 0) {
                if (temp.left == null) {
                    temp.left = new Node(value);
                    setSize(getSize() + 1);
                    break;
                }
                temp = temp.left;
            } else if (value.compareTo(temp.value) > 0) {
                if (temp.right == null) {
                    temp.right = new Node(value);
                    setSize(getSize() + 1);
                    break;
                }
                temp = temp.right;
            }
        }
    }

    public void preOrder() {
        preOrder(getRoot());
    }

    public boolean contains(T value){
        return contains(getRoot(), value);
    }

    /**
     * 插入節點(用子節點來判斷)
     * @param node
     * @param value
     */
    private void add(Node node, T value) {
        if (value.equals(node.value)) {
            return;
        }

        if (value.compareTo(node.value) < 0 && node.left == null) {
            node.left = new Node(value);
            return;
        } else if (value.compareTo(node.value) > 0 && node.right == null) {
            node.right = new Node(value);
            return;
        }

        add((value.compareTo(node.value) < 0) ? node.left : node.right, value);
    }

    /**
     * 遞迴優化版本，將邏輯統一並整理循環體本，使其更簡潔
     *
     * @param node
     * @param value
     * @return
     */
    private Node add2(Node node, T value) {
        /* 終止條件 */
        if (node == null) {
            setSize(getSize() + 1);
            return new Node(value);
        }

        if (value.equals(node.value)) {
            return node;
        }

        /* 遞迴本身(循環體) */
        if (value.compareTo(node.value) < 0) {
            node.left = add2(node.left, value);
        } else {
            node.right = add2(node.right, value);
        }

        /* 返回值 */
        return node;
    }

    /**
     * 前序走訪
     * @param node
     */
    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.value);
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 查詢指定元素
     * @param node
     * @param value
     * @return
     */
    private boolean contains(Node node, T value){
        if(node == null){
            return false;
        }else if(value.equals(node.value)){
            return true;
        }

        return (value.compareTo(node.value) > 0) ? contains(node.right, value) : contains(node.left, value);
    }

    public static void main(String[] args) {
        Integer[] i = {1, 3, 5, 7, 9, 4, 55, 0};
        BST<Integer> tree = new BST<Integer>();

        for (int v : i) {
            tree.add3(v);
        }
        System.out.println(tree.contains(17));
    }

}
