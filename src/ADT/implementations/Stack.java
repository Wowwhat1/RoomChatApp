package ADT.implementations;

import ADT.interfaces.IStack;

import java.util.Iterator;

public class Stack<E> implements IStack<E> {
    private Node<E> top;
    private int size;

    private static class Node<E> {
        private E element;
        private Node<E> previous;

        public Node(E value) {
            this.element = value;
        }
    }

    public Stack() {

    }

    @Override
    public void push(E element) {
        Node<E> newNode = new Node<>(element);
        newNode.previous = top;
        top = newNode;
        this.size++;
    }

    @Override
    public E pop() {
        ensureNonEmpty();
        E element = this.top.element;
        Node<E> temp = this.top.previous;
        this.top.previous = null;
        this.top = temp;
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
        return this.top.element;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Node<E> current = top;

            @Override
            public boolean hasNext() { return current != null; }

            @Override
            public E next() {
                E element = current.element;
                this.current = this.current.previous;
                return element;
            }
        };
    }
}
