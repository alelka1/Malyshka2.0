package by.it.group251051.smalianko.lesson11;

import java.util.*;

public class MyLinkedHashSet<E> implements Set<E>  {


    public static void main(String[] args) {
        MyLinkedHashSet<Integer> x = new MyLinkedHashSet<>();

        x.add(1);
        x.add(2);
        x.add(3);
        x.add(4);

        x.remove(2);

        System.out.println(x);
    }

    private int size;
    final int SIZE_OF_ARRAY = 10;
    private LinkedNode[] array;

    private LinkedNode tail;

    private LinkedNode head;

    public MyLinkedHashSet(){
        array = new LinkedNode[SIZE_OF_ARRAY];
    }

    @Override
    public String toString() {
        if(size == 0){
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        LinkedNode node = head;

        while(node != null){
            stringBuilder.append(node.getItem());
            stringBuilder.append(',').append(' ');
            node = node.qNext;
        }

        stringBuilder.delete(stringBuilder.length()-2, stringBuilder.length());
        return stringBuilder.append(']').toString();
    }

    @Override
    public boolean remove(Object o) {
        for(int i = 0; i < array.length;i++){
            LinkedNode node = array[i];
            while(node != null){
                if(node.getItem().equals(o)){
                    array[i] = node.next;
                    size--;
                    removeFromLinks(node);
                    return true;
                }
                if(node.next != null) {
                    if (node.next.getItem().equals(o)) {
                        removeFromLinks(node.next);
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

    private void removeFromLinks(LinkedNode node){
        if(node == head && node == tail){
            head = null;
            tail = null;
        } else {
            if(node == tail){
                tail = node.qPrev;
                tail.qNext = null;
            } else if (node == head){
                head = node.qNext;
                head.qPrev = null;
            } else {
                node.qNext.qPrev = node.qPrev;
                node.qPrev.qNext = node.qNext;
            }
        }
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
            LinkedNode node = array[i];
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
        LinkedNode node = new LinkedNode();

        node.item = e;
        if(array[index] != null){
            LinkedNode startingNode = array[index];
            while(startingNode != null){
                if(startingNode.item.hashCode() != node.item.hashCode()){
                    if(startingNode.next == null){
                        startingNode.next = node;
                        size++;
                        addToLinks(node);
                        return true;
                    }
                    startingNode = startingNode.next;
                } else {
                    return false;
                }
            }
        } else {
            array[index] = node;
            size++;
            addToLinks(node);
            return true;
        }
        return false;
    }

    private void addToLinks(LinkedNode node){
        if(tail == null){
            tail = node;
            head = node;
        } else {
            if(head.qNext == null){
                node.qPrev = head;
                head.qNext = node;
                tail = node;
            } else {
                tail.qNext = node;
                node.qPrev = tail;
                tail = node;
            }
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if(c.size() == 0){
            return true;
        }
        for(Object x:c){
            if(!contains(x)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean flag = false;
        for(Object x : c){
            if(add((E) x)){
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean flag = false;
        for(Object x : c){
            if(remove(x)){
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public void clear() {
        size = 0;
        head = null;
        tail = null;
        array = new LinkedNode[SIZE_OF_ARRAY];
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean flag = false;

        for(int i = 0; i < array.length; i++){
            LinkedNode node = array[i];
            while(node != null){
                if(!c.contains(node.getItem())){
                    remove(node.getItem());
                    flag = true;
                }
                node = node.next;
            }
        }
        return flag;
    }
}
