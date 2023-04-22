package ADT.implementations;

import ADT.interfaces.IQueue;

public class Queue<E> implements IQueue<E> {
    private Node<E> head;
    private int size;

    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E value) {
            this.element = value;
        }
    }

    public Queue() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public void enQueue(E element) {
        Node<E> newNode = new Node<>(element);
        if (this.head == null) {
            this.head = newNode;
        } else {
            Node<E> current = this.head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        this.size++;
    }

    @Override
    public E deQueue() {
        ensureNonEmpty();
        E element = this.head.element;
        if (this.size == 1) {
            this.head = null;
        } else {
            Node<E> next = this.head.next;
            this.head.next = null;
            this.head = next;
        }
        this.size--;
        return element;
    }

    private void ensureNonEmpty() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
    }

    @Override
    public E peek() {
        ensureNonEmpty();
        return head.element;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
