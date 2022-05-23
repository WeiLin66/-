public class BSTSet<T extends Comparable<T>> implements Set<T> {
    private BST<T> bst;

    public BSTSet(){
        bst = new BST<>();
    }

    @Override
    public void add(T t) {
        bst.add2(t);
    }

    @Override
    public void remove(T t) {
        bst.remove(t);
    }

    @Override
    public boolean contains(T t) {
        return bst.contains(t);
    }

    @Override
    public int getSize() {
        return bst.size;
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }
}
