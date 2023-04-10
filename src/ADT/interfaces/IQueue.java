package ADT.interfaces;

public interface IQueue<E> {
    void enQueue(E element);
    E deQueue();
    int size();
    boolean isEmpty();
}
