package ADT.implementations;

import ADT.interfaces.IQueue;

public class Queue<E> implements IQueue<E> {
    int front = -1;
    int rear = -1;
    int max;
    int size;
    Object[] q;

    public Queue() {
        this.max = 250;
        this.q = new Object[max];
        size = 0;
    }

    @Override
    public void enQueue(E element) {
        if ((rear + 1) % max == front) {
            throw new IndexOutOfBoundsException("Overflow");
        }
        if (front == -1 & rear == - 1){
            front = 0; rear = 0;
        }
        else if (rear == max - 1 & front != 0){
            rear = 0;
        }
        else {
            rear = (rear + 1) % max;
        }
        q[rear] = element;
        size++;
    }

    @Override
    public E deQueue() {
        if (front == -1) {
            throw new IndexOutOfBoundsException("Underflow");
        }
        var value = q[front];
        if (front == rear){
            front = -1;
            rear = -1;
        }
        else if (front == max - 1){
            front = 0;
        }
        else {
            front = front + 1;
        }
        size--;
        return (E) value;
    }

    @Override
    public boolean isEmpty() {
        return front == -1 && rear == -1;
    }

    public void clear() {
        for (int i = front; i != rear; i = (i+1)%max){
            q[i] = null;
        }
        front = -1;
        rear = -1;
        size = 0;
    }

    public int size() {return size;}
}
