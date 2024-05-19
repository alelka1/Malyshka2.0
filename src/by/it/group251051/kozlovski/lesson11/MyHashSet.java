package by.it.group251051.kozlovski.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyHashSet<E> implements Set<E> {

    class Node<E> {
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

    @Override
    public int size() {
        return size;
    }
    @Override
    public void clear() {
        size = 0;
        array = new Node[SIZE_OF_ARRAY];
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
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

    public String toString() {
        if(size == 0){
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for(int i = 0; i < array.length; i++ ){
            if(array[i] != null){
                Node node = array[i];
                while(node != null){
                    stringBuilder.append(node.getItem());
                    stringBuilder.append(',').append(' ');
                    node = node.next;
                }
            }
        }
        stringBuilder.delete(stringBuilder.length()-2, stringBuilder.length());
        return stringBuilder.append(']').toString();
    }
    public MyHashSet(){
        array = new Node[SIZE_OF_ARRAY];
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
}
