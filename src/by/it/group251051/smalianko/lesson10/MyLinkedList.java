package by.it.group251051.smalianko.lesson10;

import java.util.*;

public class MyLinkedList<E> implements Deque<E> , List<E> {

    int size = 0;
    Node<E> first;
    Node<E> last;

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Node<E> node = first;
        if(first == null){
            return "[]";
        }
        stringBuilder.append("[");
        while (node != null) {
            stringBuilder.append(node.item);
            if(node.next == null){
                return stringBuilder.append(']').toString();
            }
            stringBuilder.append(',').append(' ');
            node = node.next;
        }
        return "[]";
    }

    @Override
    public void addFirst(E e) {
        Node<E> firstNode = first;
        Node<E> newNode = new Node<>(null, e, firstNode);
        first = newNode;
        if (firstNode == null){
            last = newNode;
        } else {
            firstNode.prev = newNode;
        }
        size++;
    }

    @Override
    public void addLast(E e) {
        Node<E> lastNode = last;
        Node<E> newNode = new Node<>(lastNode, e, null);
        last = newNode;
        if (lastNode == null)
            first = newNode;
        else
            lastNode.next = newNode;
        size++;
    }

    @Override
    public boolean offerFirst(E e) {
        return false;
    }

    @Override
    public boolean offerLast(E e) {
        return false;
    }

    @Override
    public E removeFirst() {
        return null;
    }

    @Override
    public E removeLast() {
        return null;
    }

    @Override
    public E pollFirst() {
        if(first != null){
            E e = first.item;
            first = first.next;
            first.prev = null;
            size--;
            return e;
        }
        return null;
    }

    @Override
    public E pollLast() {
        if(last != null){
            E e = last.item;
            last = last.prev;
            last.next = null;
            size--;
            return e;
        }
        return null;
    }

    @Override
    public E getFirst() {
        return element();
    }

    @Override
    public E getLast() {
        if(last != null){
            return last.item;
        }
        return null;
    }

    @Override
    public E peekFirst() {
        return null;
    }

    @Override
    public E peekLast() {
        return null;
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
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E poll() {
        return pollFirst();
    }

    @Override
    public E element() {
        if(first != null){
            return first.item;
        }
        return null;
    }

    @Override
    public E peek() {
        return null;
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
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
        Node<E> node = first;
        E toReturn = null;
        int i = 0;
        if(first == null){
            return toReturn;
        }
        while(node.next != null){
            if(i == index){
                toReturn = node.item;
                if(i == 0){
                    first = node.next;
                    node.next.prev = null;
                } else if (index == size - 1){
                    node.prev = last;
                    node.prev.next = null;
                } else {
                    node.next.prev = node.prev;
                    node.prev.next = node.next;
                }
                size--;
            }
            i++;
            node = node.next;
        }
        return toReturn;
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
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public void push(E e) {

    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        Node<E> node = first;
        if(first == null){
            return false;
        }
        while(node != null){
            if(node.item == (E) o){
                if(node.prev == null){
                    first = node.next;
                    node.next.prev = null;
                } else if (node.next == null){
                    node.prev = last;
                    node.prev.next = null;
                } else {
                    node.next.prev = node.prev;
                    node.prev.next = node.next;
                }
                size--;
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
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
    public Iterator<E> descendingIterator() {
        return null;
    }

}
