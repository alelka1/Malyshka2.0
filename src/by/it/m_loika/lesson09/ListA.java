package by.it.m_loika.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListA<E> implements List<E> {

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E item) {
            this.item = item;
        }
    }

    private Node<E> head;
    private int size;

    public ListA() {
        head = null;
        size = 0;
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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<E> current = head;
        while (current != null) {
            sb.append(current.item);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean add(E e) {
        if (head == null) {
            head = new Node<>(e);
        } else {
            Node<E> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node<>(e);
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
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
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
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

    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        E removedItem;
        if (index == 0) {
            removedItem = head.item;
            head = head.next;
        } else {
            Node<E> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            removedItem = current.next.item;
            current.next = current.next.next;
        }
        size--;
        return removedItem;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public int size() {
        return size;
    }
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == 0) {
            Node<E> newNode = new Node<>(element);
            newNode.next = head;
            head = newNode;
        } else {
            Node<E> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            Node<E> newNode = new Node<>(element);
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        Node<E> current = head;
        while (current != null) {
            if (current.item.equals(o)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
}
