package by.it.group251051.korneliuk.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListB<E> implements List<E> {


    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    private E[] array = (E[])new Object[]{};
    private int size = 0;
    @Override
    public String toString() {
        String str = "[";
        for(int i = 0; i < size; ++i){
            str += array[i].toString();
            if(i != size - 1) str += ", ";
        }
        str += ']';
        return str;
    }

    @Override
    public boolean add(E e) {
        if(size == array.length){
            resize();
        }
        array[size++] = e;
        return true;
    }
    private void resize(){;
        Object[] temp = new Object[size+1];
        for(int i = 0; i < size; i++){
            temp[i] = array[i];
        }
        this.array = (E[]) temp;
    }
    @Override
    public E remove(int index) {
        E removed = array[index];
        for(int i = index; i < size - 1; i++){
            array[i] = array[i+1];
        }
        size--;
        return removed;
    }

    @Override
    public int size() {
        return size;
    }
    @Override
    public void add(int index, E element) {
        if(size == array.length){
            resize();
        }
        size++;
        for(int i = size - 1; i > index; i--){
            array[i] = array[i-1];
        }
        array[index] = element;
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
        E e = (E) array[index];
        array[index] = element;
        return e;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public void clear() {
        for(int i = 0; i < size; i++){
            array[i] = null;
        }
        size = 0;
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
        return (E) array[index];
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
        int index = -1;
        for(int i = 0; i < size; i++){
            if(array[i].equals(o)){
                index = i;
            }
        }
        return index;
    }


    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Опциональные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////


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
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    ////////        Эти методы имплементировать необязательно    ////////////
    ////////        но они будут нужны для корректной отладки    ////////////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public Iterator<E> iterator() {
        return null;
    }

}
