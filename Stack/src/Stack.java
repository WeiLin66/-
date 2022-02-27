/**
 * 自定義Stack接口
 */
public interface Stack <E> {
    void push(E value);
    E pop();
    boolean isEmpty();
    E peek();
    int getSize();
}
