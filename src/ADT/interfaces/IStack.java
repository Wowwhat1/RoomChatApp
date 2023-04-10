package ADT.interfaces;

public interface IStack<E> extends Iterable<E> {
    void push(E element);
    E pop();
    E peek();
    int size();
    boolean isEmpty();
}
