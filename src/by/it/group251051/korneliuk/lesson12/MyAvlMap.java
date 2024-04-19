package by.it.group251051.korneliuk.lesson12;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MyAvlMap implements Map<Integer, String> {

    MyAvlMapNode Root;
    StringBuilder result;

    @Override
    public int size() {return size(Root);}

    private int size(MyAvlMapNode node) {
        if (node == null) {return 0;}
        return 1 + size(node.left) + size(node.right);
    }

    int height(MyAvlMapNode node) {return node == null ? 0 : node.height;}

    int BalanceState(MyAvlMapNode node) {return node == null ? 0 : height(node.left) - height(node.right);}

    @Override
    public boolean containsKey(Object key) {return Search((Integer) key, Root) != null;}

    @Override
    public boolean isEmpty() {return Root == null;}

    @Override
    public void clear() {Root = clear(Root);}

    @Override
    public String toString() {
        if (Root == null) return "{}";
        StringBuilder sb = new StringBuilder().append('{');
        InOrderTraversal(Root, sb);
        sb.replace(sb.length() - 2, sb.length(), "}");
        return sb.toString();
    }

    private void InOrderTraversal(MyAvlMapNode node, StringBuilder sb) {
        if (node != null) {
            InOrderTraversal(node.left, sb);
            sb.append(node.key).append("=").append(node.value).append(", ");
            InOrderTraversal(node.right, sb);
        }
    }

    MyAvlMapNode Search(Integer key, MyAvlMapNode node) {
        if (node == null)
            return null;
        int comparison = key.compareTo(node.key);
        return comparison != 0 ? Search(key, comparison < 0 ? node.left : node.right) : node;
    }

    @Override
    public String get(Object key) {
        MyAvlMapNode result = Search((Integer) key, Root);
        return result == null ? null : result.value;
    }

    @Override
    public String put(Integer key, String value) {
        result = new StringBuilder();
        Root = put(Root, key, value);
        return result.isEmpty() ? null : result.toString();
    }

    MyAvlMapNode put(MyAvlMapNode node, Integer key, String value) {
        if (node == null)
            return new MyAvlMapNode(key, value);
        int compare_res = key.compareTo(node.key);
        if (compare_res < 0)
            node.left = put(node.left, key, value);
        else if (compare_res > 0)
            node.right = put(node.right, key, value);
        else
            if (!node.value.equalsIgnoreCase(value)) {
                node.value = value;
                result.append("generate").append(key);
            }
        return Balance(node);
    }

    MyAvlMapNode Rotateright(MyAvlMapNode node)
    {
        MyAvlMapNode newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) + 1;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return newRoot;
    }

    MyAvlMapNode RotateLeft(MyAvlMapNode node) {
        MyAvlMapNode newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        newRoot.height = 1 + Math.max(height(newRoot.left), height(newRoot.right));
        node.height = 1 + Math.max(height(node.left), height(node.right));
        return newRoot;
    }

    MyAvlMapNode Balance(MyAvlMapNode node) {
        if (node == null)
            return null;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        int BalanceState = BalanceState(node);
        if (BalanceState > 1) {
            if (BalanceState(node.left) < 0) node.left = RotateLeft(node.left);
            return Rotateright(node);
        }
        if (BalanceState < -1) {
            if (BalanceState(node.right) > 0) node.right = Rotateright(node.right);
            return RotateLeft(node);
        }
        return node;
    }

    @Override
    public String remove(Object key) {
        result = new StringBuilder();
        Root = remove(Root, (Integer) key);
        return result.isEmpty() ? null : result.toString();
    }

    MyAvlMapNode remove(MyAvlMapNode node, Integer key) {
        if (node == null)
            return node;
        int comparison = key.compareTo(node.key);
        if (comparison < 0)
            node.left = remove(node.left, key);
        else if (comparison > 0)
            node.right = remove(node.right, key);
        else {
            result.append("generate").append(key);
            if (node.left == null)
                return node.right;
            if (node.right == null)
                return node.left;
            MyAvlMapNode minNode = minValueNode(node.right);
            node.right = RemoveMinNode(node.right);
            node.value = minNode.value;
        }
        return Balance(node);
    }

    MyAvlMapNode RemoveMinNode(MyAvlMapNode node) {
        if (node.left == null)
            return node.right;
        node.left = RemoveMinNode(node.left);
        return Balance(node);
    }

    MyAvlMapNode minValueNode(MyAvlMapNode node) {
        return node.left == null ? node : minValueNode(node.left);
    }

    MyAvlMapNode clear(MyAvlMapNode node) {
        if (node != null) {
            node.left = clear(node.left);
            node.right = clear(node.right);
        }
        return null;
    }
    @Override
    public boolean containsValue(Object value) {return false;}
    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {}
    @Override
    public Set<Integer> keySet() {return null;}
    @Override
    public Collection<String> values() {return null;}
    @Override
    public Set<Entry<Integer, String>> entrySet() {return null;}
}