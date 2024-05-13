package by.it.m_loika.lesson10;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class MyPriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private E[] heap;
    private int size;

    // Constructor
    @SuppressWarnings("unchecked")
    public MyPriorityQueue() {
        this.heap = (E[]) new Comparable[DEFAULT_CAPACITY];
        this.size = 0;
    }

    // Other constructor
    @SuppressWarnings("unchecked")
    public MyPriorityQueue(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive");
        }
        this.heap = (E[]) new Comparable[initialCapacity];
        this.size = 0;
    }

    @Override
    public boolean add(E element) {
        offer(element); // Use offer() to add element and maintain heap property
        return true;
    }

    @Override
    public boolean offer(E element) {
        if (element == null) {
            throw new NullPointerException("Cannot add null element to the queue");
        }

        if (size == heap.length) {
            resizeHeap();
        }

        // Add the new element at the end of the heap
        heap[size] = element;
        size++;

        // Restore heap property by bubbling up the newly added element
        heapifyUp(size - 1);

        return true;
    }

    private void heapifyUp(int index) {
        int parentIndex = (index - 1) / 2;
        while (index > 0 && compare(heap[index], heap[parentIndex]) < 0) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    private void resizeHeap() {
        int newCapacity = heap.length * 2;
        E[] newHeap = (E[]) new Comparable[newCapacity];
        System.arraycopy(heap, 0, newHeap, 0, size);
        heap = newHeap;
    }

    private void swap(int i, int j) {
        E temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private int compare(E a, E b) {
        return a.compareTo(b); // Using Comparable interface to compare elements
    }

    // Other methods from Queue interface (omitted for brevity)
    // Implement these as required based on your needs

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            heap[i] = null;
        }
        size = 0;
    }

    @Override
    public E remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        E removedElement = heap[0];
        heap[0] = heap[size - 1];
        heap[size - 1] = null;
        size--;
        heapifyDown(0);
        return removedElement;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(heap[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    @Override
    public E peek() {
        return isEmpty() ? null : heap[0];
    }
    private void heapifyDown(int index) {
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        int smallest = index;
        if (leftChildIndex < size && compare(heap[leftChildIndex], heap[smallest]) < 0) {
            smallest = leftChildIndex;
        }
        if (rightChildIndex < size && compare(heap[rightChildIndex], heap[smallest]) < 0) {
            smallest = rightChildIndex;
        }
        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }
    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        return remove();
    }

    @Override
    public E element() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return heap[0];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (o.equals(heap[i])) {
                return true;
            }
        }
        return false;
    }


    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Method not supported");
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Method not supported");
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Method not supported");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Method not supported");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }


    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.isEmpty()) {
            return false;
        }
        for (E element : c) {
            add(element);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        int newSize = 0;
        for (int i = 0; i < size; i++) {
            if (!c.contains(heap[i])) {
                heap[newSize++] = heap[i];
            } else {
                modified = true;
            }
        }
        if (modified) {
            for (int i = newSize; i < size; i++) {
                heap[i] = null;
            }
            size = newSize;
            // Additional step: Heapify down to maintain heap property
            for (int i = (size / 2) - 1; i >= 0; i--) {
                heapifyDown(i);
            }
        }
        return modified;
    }



    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        int newSize = 0;
        for (int i = 0; i < size; i++) {
            if (c.contains(heap[i])) {
                if (newSize != i) {
                    heap[newSize] = heap[i];
                }
                newSize++;
            } else {
                modified = true;
            }
        }
        if (modified) {
            for (int i = newSize; i < size; i++) {
                heap[i] = null;
            }
            size = newSize;
            // Additional step: Heapify down to maintain heap property
            for (int i = (size / 2) - 1; i >= 0; i--) {
                heapifyDown(i);
            }
        }
        return modified;
    }

    private void removeAt(int index) {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        heap[index] = heap[size - 1];
        heap[size - 1] = null;
        size--;
    }
}
