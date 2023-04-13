/**
 * 自定義隊列接口
 */
public interface Queue <E> {
    void enqueue(E value);
    E dequeue();
    E getFront();
    int getSize();
    boolean isEmpty();
}
