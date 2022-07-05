import java.util.Map;
import java.util.TreeMap;

public class Trie {

    private class Node {
        private boolean isWord;
        private Map<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie() {
        size = 0; // 單字數量
        root = new Node();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Node getRoot() {
        return this.root;
    }

    /**
     * 非遞迴版本添加節點到Trie中
     *
     * @param s
     */
    public void add(String s) {
        char[] arr = s.toCharArray();
        Node cur = root;

        for (int i = 0; i < arr.length; i++) {
            if (!cur.next.containsKey(arr[i])) {
                cur.next.put(arr[i], new Node());
            }
            cur = cur.next.get(arr[i]);
        }

        if (!cur.isWord) {
            cur.isWord = true;
            setSize(getSize() + 1);
        }
    }

    /**
     * 遞迴式的添加節點到Trie中
     *
     * @param s
     */
    public void add2(String s) {
        add2(s, 0, getRoot());
    }

    private void add2(String s, int index, Node cur) {
        char c = s.charAt(index);

        if (cur.next.get(c) == null) {
            cur.next.put(c, new Node());
        }

        cur = cur.next.get(c);

        if (index + 1 == s.length()) {
            if (!cur.isWord) {
                cur.isWord = true;
                setSize(getSize() + 1);
            }
            return;
        }

        add2(s, index + 1, cur);
    }

    /**
     * 非遞迴版本查找
     *
     * @param word
     * @return
     */
    public boolean contains(String word) {
        char[] arr = word.toCharArray();
        Node cur = getRoot();

        for (int i = 0; i < arr.length; i++) {
            if (cur.next.get(arr[i]) == null) {
                return false;
            }
            cur = cur.next.get(arr[i]);
        }

        return cur.isWord;
    }

    /**
     * 遞迴版本查找
     *
     * @param word
     * @return
     */
    public boolean contains2(String word) {
        return contains2(word, 0, getRoot());
    }

    private boolean contains2(String word, int index, Node cur) {
        char c = word.charAt(index);

        if (cur.next.get(c) == null) {
            return false;
        }

        if (index + 1 == word.length()) {
            return true;
        }

        cur = cur.next.get(c);

        return contains2(word, index + 1, cur);
    }

    /**
     * 非遞回版本的前綴判斷
     *
     * @param prefix
     * @return
     */
    public boolean isPrefix(String prefix) {
        Node cur = root;
        char[] arr = prefix.toCharArray();
        for (int i = 0; i < prefix.length(); i++) {
            if (cur.next.get(arr[i]) == null) {
                return false;
            }
            cur = cur.next.get(arr[i]);
        }
        return true;
    }

}
