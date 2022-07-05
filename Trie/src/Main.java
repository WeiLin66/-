public class Main {
    public static void main(String[] args) {
        Trie trie = new Trie();
        String a = "apple";
        String o = "orange";
        String c = "app";

        trie.add2(a);
        trie.add2(o);
        trie.add2(c);

        System.out.println(trie.isPrefix(c));
    }
}
