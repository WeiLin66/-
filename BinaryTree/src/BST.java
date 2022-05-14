import java.util.Stack;

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

    public void preOrderNR(){
        preOrderNR(getRoot());
    }

    public void inOrder(){
        inOrder(getRoot());
    }

    public void postOrder(){
        postOrder(getRoot());
    }

    public boolean contains(T value) {
        return contains(getRoot(), value);
    }

    /**
     * 插入節點(用子節點來判斷)
     *
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
     *
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
     * 中序走訪
     * 中序走訪可以將樹的數值從小到大排序
     * @param node
     */
    private void inOrder(Node node){
        if(node == null){
            return;
        }

        inOrder(node.left);
        System.out.println(node.value);
        inOrder(node.right);
    }

    /**
     * 後序走訪
     * @param node
     */
    private void postOrder(Node node){
        if(node == null){
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.value);
    }

    /**
     * 使用非遞迴方式編寫的前序走訪
     * @param node
     */
    private void preOrderNR(Node node){
        Stack<Node> stack = new Stack<>();
        stack.push(node);

        while(!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.value);

            if(cur.right != null){
                stack.push(cur.right);
            }

            if(cur.left != null){
                stack.push(cur.left);
            }
        }
    }

    /**
     * 課外作業: 新增非遞迴方式編寫的中序、後序走訪
     */

    /**
     * 查詢指定元素
     *
     * @param node
     * @param value
     * @return
     */
    private boolean contains(Node node, T value) {
        if (node == null) {
            return false;
        } else if (value.equals(node.value)) {
            return true;
        }

        return (value.compareTo(node.value) > 0) ? contains(node.right, value) : contains(node.left, value);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, res, 0);
        return res.toString();
    }

    private void generateBSTString(Node node, StringBuilder res, int depth) {
        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.value + "\n");
        generateBSTString(node.left, res, depth + 1);
        generateBSTString(node.right, res, depth + 1);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("==");
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Integer[] i = {10, 3, 15, 27, 9, 4, 5, 0};
        BST<Integer> tree = new BST<Integer>();

        for (int v : i) {
            tree.add3(v);
        }

        tree.preOrderNR();
        System.out.println();
        tree.preOrder();
    }

}
