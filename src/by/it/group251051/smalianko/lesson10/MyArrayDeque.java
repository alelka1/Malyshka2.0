package by.it.group251051.smalianko.lesson10;

import java.util.*;

public class MyArrayDeque<E> implements Deque<E> {

    private Object[] elements;

    private int elementsSize = 10;

    private int size;

    public MyArrayDeque(){
        elements = new Object[elementsSize];
        size = 0;
    }

    @Override
    public String toString() {
        Iterator<E> iterator = iterator();
        if(! iterator.hasNext()){
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for(;;){
            E e = iterator.next();
            stringBuilder.append(e == this ? "(this Collection)" : e);
            if (! iterator.hasNext())
                return stringBuilder.append(']').toString();
            stringBuilder.append(',').append(' ');
        }
    }

    private class OwnIterator implements Iterator<E> {
        int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public E next() {
            return (E) elements[currentIndex++];
        }
    }

    private void resize(){
        elementsSize = elementsSize * 2;
        Object[] newArray = new Object[elementsSize];
        for(int i = 0; i < size; i++){
            newArray[i] = elements[i];
        }
        elements = newArray;
    }
    @Override
    public void addFirst(E e) {
        if(size == elements.length){
            resize();
        }
        size++;
        for(int i = size - 1; i > 0; i--){
            elements[i] = elements[i-1];
        }
        elements[0] = e;
    }

    @Override
    public void addLast(E e) {
        if(size == elements.length){
            resize();
        }
        elements[size++] = e;
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
        return poll();
    }

    @Override
    public E pollLast() {
        if(size == 0){
            return null;
        }
        E e = (E) elements[--size];
        return e;
    }

    @Override
    public E getFirst() {
        return element();
    }

    @Override
    public E getLast() {
        if(size == 0){
            return null;
        }
        return (E) elements[size - 1];
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
        if(size == 0){
            return null;
        }
        E e = (E) elements[0];
        for(int i = 0; i < size - 1; i++){
            elements[i] = elements[i+1];
        }
        size--;
        return e;
    }

    @Override
    public E element() {
        if(size == 0){
            return null;
        }
        return (E) elements[0];
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
    public void push(E e) {

    }

    @Override
    public E pop() {
        return null;
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
        return new OwnIterator();
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
