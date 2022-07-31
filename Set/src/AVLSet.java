public class AVLSet<T extends Comparable<T>> implements Set<T>{

    private AVLTree<T, Object> avl;

    public AVLSet(){
        avl = new AVLTree<>();
    }

    @Override
    public void add(T t){
        avl.add(t, null);
    }

    @Override
    public void remove(T t){
        avl.remove(t);
    }

    @Override
    public boolean contains(T t){
        return avl.contains(t);
    }

    @Override
    public int getSize(){
        return avl.getSize();
    }

    @Override
    public boolean isEmpty(){
        return avl.isEmpty();
    }
}
