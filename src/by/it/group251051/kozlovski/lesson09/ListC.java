package by.it.group251051.kozlovski.lesson09;

import java.util.*;
import java.util.function.Consumer;

public class ListC<E> implements List<E> {

    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    private int initSize = 10;
    private Object[] array;
    private int size = 0;

    public ListC (){
        this.array = new Object[initSize];
    }

    private void resize(){
        initSize = initSize * 2;
        Object[] newArray = new Object[initSize];
        for(int i = 0; i < size; i++){
            newArray[i] = array[i];
        }
        this.array = newArray;
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

    @Override
    public boolean add(E e) {
        if(size == array.length){
            resize();
        }
        array[size++] = e;
        return true;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else {
            E e = (E) array[index];
            for(int i = index; i < size - 1; i++){
                array[i] = array[i+1];
            }
            size--;
            return e;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        } else {
            if(size == array.length){
                resize();
            }
            size++;
            for(int i = size - 1; i > index; i--){
                array[i] = array[i-1];
            }
            array[index] = element;
        }
    }

    @Override
    public boolean remove(Object o) {
        for(int i = 0; i < size; i++){
            if(array[i].equals(o)){
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else {
            E e = (E) array[index];
            array[index] = element;
            return e;
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
        initSize = 10;
        array = new Object[initSize];
    }

    @Override
    public int indexOf(Object o) {
        for(int i = 0; i < size; i++){
            if(array[i].equals(o)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >=size){
            throw new IndexOutOfBoundsException();
        } else {
            return (E) array[index];
        }
    }

    @Override
    public boolean contains(Object o) {
        for(int i = 0; i < size; i++){
            if(array[i].equals(o)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int lastIndexOf(Object o) {
        int saved = -1;
        for(int i = 0; i < size; i++){
            if(array[i].equals(o)){
                saved = i;
            }
        }
        return saved;
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

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if(c.size() != 0) {
            for (E e : c) {
                add(index++, e);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean overAllFlag = false;
        for(Object o:c){
            boolean flag = true;
            while(flag){
                flag = remove(o);
                if(!overAllFlag && flag){
                    overAllFlag = true;
                }
            }
        }
        return overAllFlag;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean overAllFlag = false;
        for(int i = size-1; i >= 0; i--){
            if(!c.contains(array[i])){
                remove(array[i]);
                overAllFlag = true;
            }
        }
        return overAllFlag;
    }

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Опциональные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0)
            throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
        if (toIndex > size)
            throw new IndexOutOfBoundsException("toIndex = " + toIndex);
        if (fromIndex > toIndex)
            throw new IllegalArgumentException("fromIndex(" + fromIndex +
                    ") > toIndex(" + toIndex + ")");
        List<E> newList = new ListC<>();
        for(int i = fromIndex; i <= toIndex; i++){
            newList.add((E) array[i]);
        }
        return newList;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new ListC.OwnListIterator(index);
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListC.OwnListIterator(0);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if(a.length < size){
            Object[] newArray = new Object[size];
            for(int i = 0; i < size; i++){
                newArray[i] = array[i];
            }
            return (T[]) newArray;
        } else {
            for(int i = 0; i < size; i++){
                a[i] = (T) array[i];
            }
            return a;
        }
    }

    @Override
    public Object[] toArray() {
        Object[] newArray = new Object[size];
        for(int i = 0; i < size; i++){
            newArray[i] = array[i];
        }
        return newArray;
    }

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    ////////        Эти методы имплементировать необязательно    ////////////
    ////////        но они будут нужны для корректной отладки    ////////////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public Iterator<E> iterator() {
        return new ListC.OwnIterator();
    }

    private class OwnIterator implements Iterator<E> {
        int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public E next() {
            return (E) array[currentIndex++];
        }
    }

    private class OwnListIterator extends OwnIterator implements ListIterator<E> {
        OwnListIterator(int index) {
            super();
            currentIndex = index;
        }

        public boolean hasPrevious() {
            return currentIndex != 0;
        }

        public E previous() {
            return (E) array[currentIndex--];
        }

        @Override
        public int nextIndex() {
            return currentIndex++;
        }

        @Override
        public int previousIndex() {
            return currentIndex--;
        }

        @Override
        public void remove() {
            ListC.this.remove(currentIndex);
        }

        @Override
        public void forEachRemaining(Consumer action) {
            super.forEachRemaining(action);
        }

        @Override
        public void add(E e) {
            ListC.this.add(currentIndex, e);
            currentIndex++;
        }

        @Override
        public void set(E e) {
            ListC.this.set(currentIndex, e);
            currentIndex++;
        }
    }

    public static void main(String[] args) {
        ListC<Integer> list = new ListC<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(4);

        ArrayList<Integer> x = new ArrayList<>();
        x.add(1);
        x.add(2);
        list.retainAll(x);


        System.out.println(list);
    }
}
