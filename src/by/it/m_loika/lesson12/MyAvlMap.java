package by.it.m_loika.lesson12;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MyAvlMap implements Map<Integer, String> {
    private class Node {
        int key;
        String value;
        Node left, right;
        int height;

        Node(int key, String value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }
    }

    private Node root;
    private int size;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        toStringHelper(root, sb);
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 2);
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


    @Override
    public String put(Integer key, String value) {
        if (root == null) {
            root = new Node(key, value);
            size++;
            return null;
        }
        if (containsKey(key)) {
            String oldValue = get(key);
            if (oldValue.equals(value)) {
                return null;
            }
            find(root, key).value = value;
            return oldValue;
        }
        root = insert(root, key, value);
        return null;
    }




    private Node insert(Node node, Integer key, String value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (key < node.key) {
            node.left = insert(node.left, key, value);
        } else if (key > node.key) {
            node.right = insert(node.right, key, value);
        } else {
            node.value = value;
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);


        if (balance > 1 && key < node.left.key) {
            return rightRotate(node);
        }


        if (balance < -1 && key > node.right.key) {
            return leftRotate(node);
        }


        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }


        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }


    private int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    private int getBalance(Node node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;


        x.right = y;
        y.left = T2;


        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;


        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;


        y.left = x;
        x.right = T2;


        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;


        return y;
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

            if (root.left == null || root.right == null) {
                Node temp = null;
                if (temp == root.left) {
                    temp = root.right;
                } else {
                    temp = root.left;
                }


                if (temp == null) {
                    temp = root;
                    root = null;
                } else {
                    root = temp;
                }
            } else {

                Node temp = minValueNode(root.right);


                root.key = temp.key;


                root.right = deleteNode(root.right, temp.key);
            }
        }


        if (root == null) {
            return null;
        }


        root.height = Math.max(height(root.left), height(root.right)) + 1;


        int balance = getBalance(root);

        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0) {
            return rightRotate(root);
        }

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0) {
            return leftRotate(root);
        }

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

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
        return false;
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

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

}
