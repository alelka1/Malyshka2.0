package by.it.group251051.smalianko.lesson10;

import java.util.*;

public class MyPriorityQueue<E> implements Queue<E> {

    private int initSize;
    private E[] heap;
    private int size;

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
            return (E) heap[currentIndex++];
        }
    }

    public MyPriorityQueue(){
        initSize = 10;
        heap = (E[]) new Object[initSize];
        size = 0;
    }

    <T> void siftUp(int k, T x) {
        Comparable<? super T> key = (Comparable<? super T>) x;
        Object[] es = heap;
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = es[parent];
            if (key.compareTo((T) e) >= 0)
                break;
            es[k] = e;
            k = parent;
        }
        es[k] = key;
    }

    <T> void siftDown(int k, T x, int n){
        Comparable<? super T> key = (Comparable<? super T>)x;
        Object[] es = heap;
        int half = n >>> 1;           // loop while a non-leaf
        while (k < half) {
            int child = (k << 1) + 1; // assume left child is least
            Object c = es[child];
            int right = child + 1;
            if (right < n &&
                    ((Comparable<? super T>) c).compareTo((T) es[right]) > 0)
                c = es[child = right];
            if (key.compareTo((T) c) <= 0)
                break;
            es[k] =c;
            k = child;
        }
        es[k] = key;
    }

    void insert(E e) {//вставка
        if(size == heap.length){
            heapGrow();
        }
        heap[size] = e;
        size++;
        siftUp(size -1, e);
    }

    void heapGrow(){
        Object[] newArray = new Object[++initSize];
        for(int i = 0; i < size; i++){
            newArray[i] = heap[i];
        }
        heap = (E[]) newArray;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for(int i = 0; i < size; i++){
            if(o.equals(heap[i])){
                return true;
            }
        }
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
    public boolean add(E e) {
        insert(e);
        return true;
    }

    @Override
    public boolean remove(Object o) {

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object e : c){
            if(!contains(e)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if(c.size() != 0) {
            for (E e : c) {
                add(e);
            }
            return true;
        }
        return false;
    }

    private static long[] nBits(int n) {
        return new long[((n - 1) >> 6) + 1];
    }
    private static void setBit(long[] bits, int i) {
        bits[i >> 6] |= 1L << i;
    }
    private static boolean isClear(long[] bits, int i) {
        return (bits[i >> 6] & (1L << i)) == 0;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean flag = false;
        if(c.isEmpty()){
            return false;
        }
        int index = 0;
        int newSize = size;
        Object[] newArray = new Object[size];
        for(int i = 0; i < size; i++){
            if(c.contains(heap[i])){
                newSize--;
                flag = true;
            } else {
                newArray[index] = heap[i];
                index++;
            }
        }
        size = newSize;
        heap = (E[]) newArray;
        heapify();
        return flag;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        final Object[] es = heap;
        final int end = size;
        int i;
        for (i = 0; i < end && c.contains(es[i]); i++);
        final int beg = i;
        final long[] deathRow = nBits(end - beg);
        deathRow[0] = 1L;   // set bit 0
        for (i = beg + 1; i < end; i++)
            if (!c.contains(es[i]))
                setBit(deathRow, i - beg);
        int w = beg;
        for (i = beg; i < end; i++)
            if (isClear(deathRow, i - beg))
                es[w++] = es[i];
        for (i = size = w; i < end; i++)
            es[i] = null;
        heapify();
        return true;
    }

    private void heapify() {
        final Object[] es = heap;
        int n = size, i = (n >>> 1) - 1;
        final Comparator<? super E> cmp;
        for (; i >= 0; i--)
            siftDown(i, (E) es[i], n);
    }

    boolean removeAt(int i){
        return false;
    }

    @Override
    public void clear() {
        size = 0;
        initSize = 10;
        heap = (E[]) new Object[initSize];
    }

    @Override
    public boolean offer(E e) {
        return add(e);
    }

    @Override
    public E remove() {
        return poll();
    }

    @Override
    public E poll() {
        final E result;
        if (((result = (E) heap[0])) != null) {
            int n;
            E x = (E) heap[(n = --size)];
            heap[n] = null;
            if (n > 0) {
                siftDown(0,x,n);
            }
        }
        return result;
    }

    @Override
    public E element() {
        return peek();
    }

    @Override
    public E peek() {
        return heap[0];
    }
}
