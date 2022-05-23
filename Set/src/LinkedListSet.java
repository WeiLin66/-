public class LinkedListSet<T> implements Set<T>{
    private MyLinkedList<T> list;

    public LinkedListSet(){
        list = new MyLinkedList<>();
    }

    @Override
    public void add(T t) {
        if(!list.contains(t)){
            list.addFirst(t);
        }
    }

    @Override
    public void remove(T t) {
        list.removeElement(t);
    }

    @Override
    public boolean contains(T t) {
        return list.contains(t);
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
