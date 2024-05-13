package by.it.m_loika.lesson12;

import java.util.*;

public class MyRbMap implements SortedMap<Integer, String> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        int key;
        String value;
        Node left, right, parent;
        boolean color;

        Node(int key, String value, boolean color, Node parent) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.parent = parent;
        }
    }

    private Node root;
    private int size;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        toStringHelper(root, sb);
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 2); // Удаляем последнюю запятую и пробел
        }
        sb.append("}");
        return sb.toString();
    }

    private void toStringHelper(Node node, StringBuilder sb) {
        if (node != null) {
            toStringHelper(node.left, sb);
            sb.append(node.key).append("=").append(node.value).append(", ");
            toStringHelper(node.right, sb);
        }
    }

    public String put(Integer key, String value) {
        if (root == null) {
            root = new Node(key, value, BLACK, null);
            size++;
            return null;
        }
        String oldValue = insert(key, value);
        if (oldValue == null) {
            size++;
        }
        return oldValue;
    }

    private String insert(int key, String value) {
        String oldValue = null;
        Node parent = null;
        Node current = root;

        while (current != null) {
            parent = current;
            if (key < current.key) {
                current = current.left;
            } else if (key > current.key) {
                current = current.right;
            } else {
                oldValue = current.value;
                current.value = value;
                break;
            }
        }

        if (oldValue != null) {
            return oldValue;
        }

        Node newNode = new Node(key, value, RED, parent); // Указываем родителя при создании нового узла
        if (parent == null) {
            root = newNode;
            root.color = BLACK;
        } else if (key < parent.key) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        fixInsertion(newNode);
        return null;
    }

    private void fixInsertion(Node node) {
        while (node != root && node.color == RED && node.parent.color == RED) {
            Node parent = node.parent;
            Node grandparent = parent.parent;

            // Case 1: Parent is a left child of grandparent
            if (parent == grandparent.left) {
                Node uncle = grandparent.right;
                if (uncle != null && uncle.color == RED) {
                    grandparent.color = RED;
                    parent.color = BLACK;
                    uncle.color = BLACK;
                    node = grandparent;
                } else {
                    if (node == parent.right) {
                        node = parent;
                        rotateLeft(node);
                    }
                    node.parent.color = BLACK;
                    grandparent.color = RED;
                    rotateRight(grandparent);
                }
            }
            // Case 2: Parent is a right child of grandparent
            else {
                Node uncle = grandparent.left;
                if (uncle != null && uncle.color == RED) {
                    grandparent.color = RED;
                    parent.color = BLACK;
                    uncle.color = BLACK;
                    node = grandparent;
                } else {
                    if (node == parent.left) {
                        node = parent;
                        rotateRight(node);
                    }
                    node.parent.color = BLACK;
                    grandparent.color = RED;
                    rotateLeft(grandparent);
                }
            }
        }
        root.color = BLACK;
    }

    private void rotateLeft(Node node) {
        Node rightChild = node.right;
        node.right = rightChild.left;
        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }
        rightChild.parent = node.parent;
        if (node.parent == null) {
            root = rightChild;
        } else if (node == node.parent.left) {
            node.parent.left = rightChild;
        } else {
            node.parent.right = rightChild;
        }
        rightChild.left = node;
        node.parent = rightChild;
    }

    private void rotateRight(Node node) {
        Node leftChild = node.left;
        node.left = leftChild.right;
        if (leftChild.right != null) {
            leftChild.right.parent = node;
        }
        leftChild.parent = node.parent;
        if (node.parent == null) {
            root = leftChild;
        } else if (node == node.parent.right) {
            node.parent.right = leftChild;
        } else {
            node.parent.left = leftChild;
        }
        leftChild.right = node;
        node.parent = leftChild;
    }

    @Override
    public String remove(Object key) {
        if (!(key instanceof Integer)) {
            return null;
        }
        int intKey = (Integer) key;
        if (!containsKey(intKey)) {
            return null;
        }
        String removedValue = get(intKey);
        root = deleteNode(root, intKey);
        size--;
        return removedValue;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {

    }

    private Node deleteNode(Node root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.key) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.key) {
            root.right = deleteNode(root.right, key);
        } else {
            // Node with only one child or no child
            if (root.left == null || root.right == null) {
                Node temp = root.left != null ? root.left : root.right;

                // No child case
                if (temp == null) {
                    temp = root;
                    root = null;
                } else { // One child case
                    root = temp; // Copy the contents of the non-empty child
                }
            } else {
                // Node with two children: Get the inorder successor (smallest
                // in the right subtree)
                Node temp = minValueNode(root.right);

                // Copy the inorder successor's data to this node
                root.key = temp.key;
                root.value = temp.value;

                // Delete the inorder successor
                root.right = deleteNode(root.right, temp.key);
            }
        }

        // If the tree had only one node then return
        if (root == null) {
            return null;
        }

        // Fix the balance
        return root;
    }

    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    @Override
    public String get(Object key) {
        if (!(key instanceof Integer)) {
            return null;
        }
        int intKey = (Integer) key;
        Node node = find(root, intKey);
        return (node == null) ? null : node.value;
    }

    private Node find(Node node, int key) {
        if (node == null || node.key == key) {
            return node;
        }
        if (key < node.key) {
            return find(node.left, key);
        }
        return find(node.right, key);
    }

    @Override
    public boolean containsKey(Object key) {
        if (!(key instanceof Integer)) {
            return false;
        }
        int intKey = (Integer) key;
        return find(root, intKey) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        if (value == null || !(value instanceof String)) {
            return false;
        }
        return containsValueHelper(root, (String) value);
    }

    private boolean containsValueHelper(Node node, String value) {
        if (node == null) {
            return false;
        }
        if (node.value.equals(value)) {
            return true;
        }
        return containsValueHelper(node.left, value) || containsValueHelper(node.right, value);
    }




    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Comparator<? super Integer> comparator() {
        return null;
    }

    @Override
    public SortedMap<Integer, String> subMap(Integer fromKey, Integer toKey) {
        return null;
    }

    @Override
    public SortedMap<Integer, String> headMap(Integer toKey) {
        SortedMap<Integer, String> resultMap = new MyRbMap();
        headMapHelper(root, resultMap, toKey);
        return resultMap;
    }

    private void headMapHelper(Node node, SortedMap<Integer, String> resultMap, Integer toKey) {
        if (node == null) {
            return;
        }

        if (node.key < toKey) {
            resultMap.put(node.key, node.value);
            headMapHelper(node.left, resultMap, toKey);
            headMapHelper(node.right, resultMap, toKey);
        } else {
            headMapHelper(node.left, resultMap, toKey);
        }
    }


    @Override
    public SortedMap<Integer, String> tailMap(Integer fromKey) {
        SortedMap<Integer, String> resultMap = new MyRbMap();
        tailMapHelper(root, resultMap, fromKey);
        return resultMap;
    }

    private void tailMapHelper(Node node, SortedMap<Integer, String> resultMap, Integer fromKey) {
        if (node == null) {
            return;
        }

        if (node.key >= fromKey) {
            tailMapHelper(node.left, resultMap, fromKey); // Рекурсивно обходим левое поддерево

            // Добавляем текущий узел в результат, если его ключ больше или равен fromKey
            resultMap.put(node.key, node.value);

            tailMapHelper(node.right, resultMap, fromKey); // Рекурсивно обходим правое поддерево
        } else {
            tailMapHelper(node.right, resultMap, fromKey); // Обходим только правое поддерево
        }
    }



    @Override
    public Integer firstKey() {
        if (root == null) {
            throw new NoSuchElementException("Map is empty");
        }
        Node minNode = findMin(root);
        return minNode.key;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    @Override
    public Integer lastKey() {
        if (root == null) {
            throw new NoSuchElementException("Map is empty");
        }
        Node maxNode = findMax(root);
        return maxNode.key;
    }

    @Override
    public Set<Integer> keySet() {
        return null;
    }

    @Override
    public Collection<String> values() {
        return null;
    }

    @Override
    public Set<Entry<Integer, String>> entrySet() {
        return null;
    }

    private Node findMax(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

}