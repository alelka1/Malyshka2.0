package by.it.group251051.korneliuk.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyHashSet<E> implements Set<E> {

    static class Node<E> {
        Node next;
        Object item;

        public Node() {
        }

        public Node getNext() {
            return next;
        }

        public Object getItem() {
            return item;
        }
    }

    private final int SIZE_OF_ARRAY = 10;
    private Node[] array;

    private int size;

    public String toString() {
        if(size == 0) {
            return "[]";
        }
        StringBuilder str = new StringBuilder().append("[");
        for (Node value : array) {
            if (value != null) {
                Node node = value;
                while (node != null) {
                    str.append(node.getItem()).append(',').append(' ');
                    node = node.next;
                }
            }
        }
        str.delete(str.length()-2, str.length());
        return str.append(']').toString();
    }
    public MyHashSet(){
        array = new Node[SIZE_OF_ARRAY];
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
        for(int i = 0; i < array.length; i++){
            Node node = array[i];
            while(node != null){
                if(node.getItem().equals(o)){
                    return true;
                }
                node = node.next;
            }
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

    @Override
    public boolean add(E e) {
        int hash = e.hashCode();
        int index = (hash & (SIZE_OF_ARRAY - 1));
        Node node = new Node();
        node.item = e;
        if(array[index] != null){
            Node startingNode = array[index];
            while(startingNode != null){
                if(startingNode.item.hashCode() != node.item.hashCode()){
                    if(startingNode.next == null){
                        startingNode.next = node;
                        size++;
                        return true;
                    }
                    startingNode = startingNode.getNext();
                } else {
                    return false;
                }
            }
        } else {
            array[index] = node;
            size++;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        for(int i = 0; i < array.length;i++){
            Node node = array[i];
            while(node != null){
                if(node.getItem().equals(o)){
                    array[i] = node.next;
                    size--;
                    return true;
                }
                if(node.next != null) {
                    if (node.next.getItem().equals(o)) {
                        node.next = node.next.next;
                        size--;
                        return true;
                    }
                }
                node = node.next;
            }
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
    public void clear() {
        size = 0;
        array = new Node[SIZE_OF_ARRAY];
    }
}
