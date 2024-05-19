package by.it.group251051.kozlovski.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyTreeSet<E extends Comparable<E>> implements Set<E> {

    TreeNode root;
    int size;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        inOrderTraversal(root, sb);
        return sb.append("]").toString();
    }

    @Override
    public boolean contains(Object o) {return contains(root, (E) o);}

    @Override
    public boolean add(E e) {
        if (!contains(e)) {root = insert(root, e);size++;return true;}
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (contains(o)) {root = delete(root, (E) o);size--;return true;}
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object obj : c) {if (!contains(obj)) return false;}
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean f = false;
        for (E element : c) {if (add(element)) f = true;}
        return f;
    }

    TreeNode findMin(TreeNode node) {
        while (node.left != null) {node = node.left;}
        return node;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean f = false;
        for (Object obj : c) {if (remove(obj)) f = true;}
        return f;
    }

    TreeNode insert(TreeNode node, E element) {
        if (node == null) return new TreeNode(element);
        int compare = element.compareTo((E) node.data);
        if (compare < 0) node.left = insert(node.left, element);
        else if (compare > 0) node.right = insert(node.right, element);
        return node;
    }

    TreeNode delete(TreeNode node, E element) {
        if (node == null) return null;
        int compare = element.compareTo((E) node.data);
        if (compare < 0) {node.left = delete(node.left, element);}
        else if (compare > 0) {node.right = delete(node.right, element);}
        else {if (node.left == null) {return node.right;}
        else if (node.right == null) {return node.left;}
            node.data = findMin(node.right).data;
            node.right = delete(node.right, (E) node.data);
        }
        return node;
    }

    void inOrderTraversal(TreeNode node, StringBuilder sb) {
        if (node == null) return;
        inOrderTraversal(node.left, sb);
        if (sb.length() > 1) sb.append(", ");
        sb.append(node.data);
        inOrderTraversal(node.right, sb);
    }

    boolean contains(TreeNode node, E element) {
        if (node == null) return false;
        int compare = element.compareTo((E) node.data);
        if (compare < 0) return contains(node.left, element);
        else if (compare > 0) return contains(node.right, element);
        else return true;
    }


    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.isEmpty()) {this.clear();return true;}
        boolean f = false;
        MyTreeSet<E> retainSet = new MyTreeSet<>();
        for (Object obj : c) {if (contains(obj)) {retainSet.add((E) obj);f = true;}}
        root = retainSet.root;
        size = retainSet.size;
        return f;
    }

    @Override
    public int size() {return size;}

    @Override
    public void clear() {root = null;size = 0;}

    @Override
    public boolean isEmpty() {return size == 0;}

    @Override
    public Iterator<E> iterator() {return null;}

    @Override
    public Object[] toArray() {return new Object[0];}

    @Override
    public <T> T[] toArray(T[] a) {return null;}

}