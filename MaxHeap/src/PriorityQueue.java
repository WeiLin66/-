public class PriorityQueue<T extends Comparable<T>> implements Queue<T> {
    private MaxHeap<T> maxHeap;

    public PriorityQueue(){
        maxHeap = new MaxHeap<>();
    }

    @Override
    public void enqueue(T value) {
        maxHeap.add(value);
    }

    @Override
    public T dequeue() {
        return maxHeap.extractMax();
    }

    @Override
    public T getFront() {
        return maxHeap.findMax();
    }

    @Override
    public int getSize() {
        return maxHeap.size();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }
}
