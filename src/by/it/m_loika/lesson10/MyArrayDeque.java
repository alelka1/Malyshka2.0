package by.it.m_loika.lesson10;

import java.util.*;

public class MyArrayDeque<E> implements Deque<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private E[] array;
    private int size;
    private int head; // Index of the first element
    private int tail; // Index of the last element

    public MyArrayDeque() {
        this(DEFAULT_CAPACITY);
    }

    public MyArrayDeque(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        array = (E[]) new Object[capacity];
        size = 0;
        head = 0;
        tail = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(array[(head + i) % array.length]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public void addFirst(E element) {
        if (size == array.length) {
            resize();
        }
        head = (head - 1 + array.length) % array.length;
        array[head] = element;
        size++;
    }

    @Override
    public void addLast(E element) {
        if (size == array.length) {
            resize();
        }
        array[tail] = element;
        tail = (tail + 1) % array.length;
        size++;
    }

    @Override
    public E element() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return array[head];
    }

    @Override
    public E getFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return array[head];
    }

    @Override
    public E getLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return array[(tail - 1 + array.length) % array.length];
    }

    @Override
    public boolean add(E element) {
        addLast(element);
        return true;
    }

    @Override
    public E poll() {
        if (size == 0) {
            return null;
        }
        E element = array[head];
        head = (head + 1) % array.length;
        size--;
        return element;
    }

    @Override
    public E pollFirst() {
        return poll();
    }

    @Override
    public E pollLast() {
        if (size == 0) {
            return null;
        }
        tail = (tail - 1 + array.length) % array.length;
        E element = array[tail];
        size--;
        return element;
    }

    private void resize() {
        int newCapacity = array.length * 2;
        E[] newArray = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[(head + i) % array.length];
        }
        array = newArray;
        head = 0;
        tail = size;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }


    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<E> descendingIterator() {
        throw new UnsupportedOperationException();
    }


    @Override
    public boolean offer(E e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean offerFirst(E e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean offerLast(E e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E peek() {
        throw new UnsupportedOperationException();
    }

    @Override
    public E peekFirst() {
        throw new UnsupportedOperationException();
    }

    @Override
    public E peekLast() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
    }

    @Override
    public E pop() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void push(E e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public E removeFirst() {
        throw new UnsupportedOperationException();
    }

    @Override
    public E removeLast() {
        return null;
    }
}