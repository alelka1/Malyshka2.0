package by.it.m_loika.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyHashSet<E> implements Set<E> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private Node<E>[] buckets;
    private int size;

    // Internal class for linked list node
    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }

    // Constructor
    @SuppressWarnings("unchecked")
    public MyHashSet() {
        buckets = new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        boolean isFirst = true;
        for (Node<E> bucket : buckets) {
            Node<E> current = bucket;
            while (current != null) {
                if (!isFirst) {
                    sb.append(", ");
                }
                sb.append(current.data);
                isFirst = false;
                current = current.next;
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
    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(E element) {
        int index = getIndex(element);
        if (buckets[index] == null) {
            buckets[index] = new Node<>(element);
        } else {
            Node<E> current = buckets[index];
            while (current != null) {
                if (current.data.equals(element)) {
                    return false; // Element already exists
                }
                current = current.next;
            }

            Node<E> newNode = new Node<>(element);
            newNode.next = buckets[index];
            buckets[index] = newNode;
        }
        size++;
        resizeIfNeeded();
        return true;
    }


    @Override
    public boolean remove(Object o) {
        int index = getIndex(o);
        Node<E> current = buckets[index];
        Node<E> prev = null;
        while (current != null) {
            if (current.data.equals(o)) {
                if (prev == null) {
                    buckets[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }



    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        int index = getIndex(o);
        Node<E> current = buckets[index];
        while (current != null) {
            if (current.data.equals(o)) {
                return true;
            }
            current = current.next;
        }
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

    // Helper method to get index for an element
    private int getIndex(Object o) {
        return Math.abs(o.hashCode() % buckets.length);
    }

    // Helper method to resize the array if necessary
    private void resizeIfNeeded() {
        if ((double)size / buckets.length >= LOAD_FACTOR) {
            int newCapacity = buckets.length * 2;
            Node<E>[] newBuckets = new Node[newCapacity];
            for (Node<E> bucket : buckets) {
                Node<E> current = bucket;
                while (current != null) {
                    Node<E> next = current.next;
                    int index = Math.abs(current.data.hashCode() % newCapacity);
                    current.next = newBuckets[index];
                    newBuckets[index] = current;
                    current = next;
                }
            }
            buckets = newBuckets;
        }
    }

}