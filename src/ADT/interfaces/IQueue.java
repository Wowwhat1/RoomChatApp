package ADT.interfaces;

public interface IQueue<E> {
    void enQueue(E element);
    E deQueue();
    E peek();
    int size();
    boolean isEmpty();
}
