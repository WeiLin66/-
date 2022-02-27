/**
 * Stack接口實現類
 */
public class ArrayStack<E> implements Stack<E> {
    private Array<E> array;

    public ArrayStack(int capacity){
        array = new Array<>(capacity);
    }

    public ArrayStack(){
        this(10);
    }

    /**
     *
     * @param value
     */
    @Override
    public void push(E value) {
        array.addLast(value);
    }

    /**
     *
     * @return
     */
    @Override
    public E pop() {
        return array.removeLast();
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     *
     * @return
     */
    @Override
    public E peek() {
        return array.getLast();
    }

    /**
     *
     * @return
     */
    @Override
    public int getSize() {
        return array.getSize();
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: [");
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.getData()[i]);
            if (i != array.getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("] Top");
        return res.toString();
    }
}
