package by.it.group251051.korneliuk.lesson12;

import java.util.*;

public class MySplayMap implements NavigableMap<Integer, String> {

    private static class Str{
        String res;

        Str(String s) {
            res = s;
        }
    }

    MySplayMapNode root = null;
    int size = 0;

    private void traverseToString(MySplayMapNode p, Str res){
        if(p == null)
            return;
        if(p.left != null)
            traverseToString(p.left, res);
        res.res += p.key.toString() + "=" + p.data + ", ";
        if(p.right != null)
            traverseToString(p.right, res);
    }

    @Override
    public String toString(){
        Str res = new Str("{");
        traverseToString(root, res);
        if(res.res.length() > 2)
            res.res = res.res.substring(0, res.res.length() - 2);
        return res.res + "}";
    }

    private MySplayMapNode getParent(MySplayMapNode t){
        return t.parent;
    }

    private MySplayMapNode getGrandparent(MySplayMapNode t){
        MySplayMapNode p = getParent(t);
        return p != null ? getParent(p) : null;
    }

    private void leftRotate(MySplayMapNode a){
        MySplayMapNode ar = a.right;
        if(ar != null){
            MySplayMapNode c = ar.left;
            ar.parent = a.parent;
            if(ar.parent != null) {
                if(ar.parent.left == a)
                    ar.parent.left = ar;
                else
                    ar.parent.right = ar;
            }
            a.parent = ar;
            if(root == a)
                root = ar;
            ar.left = a;
            if(c != null)
                c.parent = a;
            a.right = c;
        }
    }

    private void rightRotate(MySplayMapNode a){
        MySplayMapNode al = a.left;
        if(al != null){
            al.parent = a.parent;
            if(al.parent != null){
                if(al.parent.left == a)
                    al.parent.left = al;
                else
                    al.parent.right = al;
            }
            a.parent = al;
            if(root == a)
                root = al;
            MySplayMapNode alr = al.right;
            al.right = a;
            if(alr != null)
                alr.parent = a;
            a.left = alr;
        }
    }

    private void Zig(MySplayMapNode a){
        if(a.parent.left == a)
            rightRotate(a.parent);
        else
            leftRotate(a.parent);
    }

    private void ZigZig(MySplayMapNode x){
        MySplayMapNode g = getGrandparent(x), p = getParent(x);
        if(p.left == x){
            rightRotate(g);
            rightRotate(p);
        } else {
            leftRotate(g);
            leftRotate(p);
        }
    }

    private void ZagZag(MySplayMapNode x){
        MySplayMapNode g = getGrandparent(x), p = getParent(x);
        if(p.right == x){
            leftRotate(p);
            rightRotate(g);
        } else {
            rightRotate(p);
            leftRotate(g);
        }
    }

    private void Splay(MySplayMapNode x){
        while (root != x) {
            MySplayMapNode p = getParent(x);
            if (p == root)
                Zig(x);
            else {
                MySplayMapNode g = getGrandparent(x);
                if ((g.left == p && p.left == x) || (g.right == p && p.right == x))
                    ZigZig(x);
                else
                    ZagZag(x);
            }
        }
    }

    private MySplayMapNode FindKey(Object key){
        MySplayMapNode t = root;
        while(t != null && !t.key.equals(key))
            t = (Integer)key < t.key ? t.left : t.right;
        return t;
    }

    private MySplayMapNode FindFirstGreater(MySplayMapNode t){
        t = t.right;
        while(t.left != null)
            t = t.left;
        return t;
    }

    private MySplayMapNode putKey(Integer key){
        MySplayMapNode prev = root, t = prev;
        while(t != null){
            prev = t;
            t = key < t.key ? t.left : t.right;
        }
        t = new MySplayMapNode(key);
        t.parent = prev;
        if(prev == null)
            root = t;
        else if (key < prev.key)
            prev.left = t;
        else
            prev.right = t;
        return t;
    }

    private void removeLeaveNode(MySplayMapNode x){
        if(x.parent == null)
            root = null;
        else if (x.parent.left == x)
            x.parent = x.parent.left = null;
        else
            x.parent = x.parent.right = null;
    }

    private void removeNode(MySplayMapNode exist){
        if(exist.left == null && exist.right == null) {
            MySplayMapNode p = getParent(exist);
            removeLeaveNode(exist);
            if(p != null)
                Splay(p);
        } else if(exist.left != null && exist.right != null){
            MySplayMapNode del = FindFirstGreater(exist);
            Integer tempKey = exist.key;
            String tempString = exist.data;
            exist.key = del.key;
            exist.data = del.data;
            del.key = tempKey;
            del.data = tempString;
            removeNode(del);
        } else {
            MySplayMapNode p;
            if(exist.left != null){
                p = exist.left;
                exist.left.parent = exist.parent;
                if(exist.parent == null)
                    root = exist.left;
                else if (exist.parent.left == exist)
                    exist.parent.left = exist.left;
                else
                    exist.parent.right = exist.left;
            } else {
                p = exist.right;
                exist.right.parent = exist.parent;
                if(exist.parent == null)
                    root = exist.right;
                else if (exist.parent.left == exist)
                    exist.parent.left = exist.right;
                else
                    exist.parent.right = exist.right;
            }
            Splay(p);
        }
    }

    @Override
    public Entry<Integer, String> lowerEntry(Integer key) {
        return null;
    }

    private Integer travelLowerKey(MySplayMapNode p, Integer key, boolean inclusion){
        Integer ans = (p.key < key) || (inclusion && p.key.equals(key)) ? p.key : null;
        Integer l = p.left != null ? travelLowerKey(p.left, key, inclusion) : null;
        Integer r = p.right != null ? travelLowerKey(p.right, key, inclusion) : null;
        if(ans == null)
            if(l != null)
                ans = l;
            else if(r != null)
                ans = r;
        if(ans != null & l != null && l > ans)
            ans = l;
        if(ans != null && r != null && r > ans)
            ans = r;
        return ans;
    }

    @Override
    public Integer lowerKey(Integer key) {
        if(isEmpty())
            return null;
        return travelLowerKey(root, key, false);
    }

    @Override
    public Entry<Integer, String> floorEntry(Integer key) {
        return null;
    }

    @Override
    public Integer floorKey(Integer key) {
        if(isEmpty())
            return null;
        return travelLowerKey(root, key, true);
    }

    @Override
    public Entry<Integer, String> ceilingEntry(Integer key) {
        return null;
    }

    @Override
    public Integer ceilingKey(Integer key) {
        if(isEmpty())
            return null;
        return travelHigherKey(root, key, true);
    }

    @Override
    public Entry<Integer, String> higherEntry(Integer key) {
        return null;
    }

    private Integer travelHigherKey(MySplayMapNode p, Integer key, boolean inclusion){
        Integer ans = (p.key > key) || (inclusion && p.key.equals(key)) ? p.key : null;
        Integer l = p.left != null ? travelHigherKey(p.left, key, inclusion) : null;
        Integer r = p.right != null ? travelHigherKey(p.right, key, inclusion) : null;
        if(ans == null)
            if(l != null)
                ans = l;
            else if(r != null)
                ans = r;
        if(ans != null & l != null && l < ans)
            ans = l;
        if(ans != null && r != null && r < ans)
            ans = r;
        return ans;
    }

    @Override
    public Integer higherKey(Integer key) {
        if(isEmpty())
            return null;
        return travelHigherKey(root, key, false);
    }

    @Override
    public Entry<Integer, String> firstEntry() {
        return null;
    }

    @Override
    public Entry<Integer, String> lastEntry() {
        return null;
    }

    @Override
    public Entry<Integer, String> pollFirstEntry() {
        return null;
    }

    @Override
    public Entry<Integer, String> pollLastEntry() {
        return null;
    }

    @Override
    public NavigableMap<Integer, String> descendingMap() {
        return null;
    }

    @Override
    public NavigableSet<Integer> navigableKeySet() {
        return null;
    }

    @Override
    public NavigableSet<Integer> descendingKeySet() {
        return null;
    }

    @Override
    public NavigableMap<Integer, String> subMap(Integer fromKey, boolean fromInclusive, Integer toKey, boolean toInclusive) {
        return null;
    }

    private void traverseHeadMap(MySplayMapNode p, NavigableMap<Integer, String> res, Integer toKey){
        if(p != null) {
            if (p.left != null)
                traverseHeadMap(p.left, res, toKey);
            if (p.key < toKey)
                res.put(p.key, p.data);
            if (p.right != null)
                traverseHeadMap(p.right, res, toKey);
        }
    }

    @Override
    public NavigableMap<Integer, String> headMap(Integer toKey) {
        MySplayMap ans = new MySplayMap();
        traverseHeadMap(root, ans, toKey);
        return ans;
    }

    private void traverseTailMap(MySplayMapNode p, NavigableMap<Integer, String> res, Integer fromKey){
        if(p == null)
            return;
        if(p.left != null)
            traverseTailMap(p.left, res, fromKey);
        if(p.key >= fromKey)
            res.put(p.key, p.data);
        if(p.right != null)
            traverseTailMap(p.right, res, fromKey);
    }

    @Override
    public NavigableMap<Integer, String> tailMap(Integer fromKey) {
        MySplayMap ans = new MySplayMap();
        traverseTailMap(root, ans, fromKey);
        return ans;
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
    public NavigableMap<Integer, String> headMap(Integer toKey, boolean inclusive) {
        return null;
    }

    @Override
    public NavigableMap<Integer, String> tailMap(Integer fromKey, boolean inclusive) {
        return null;
    }

    @Override
    public Integer firstKey() {
        MySplayMapNode p = root;
        while(p.left != null)
            p = p.left;
        return p.key;
    }

    @Override
    public Integer lastKey() {
        MySplayMapNode p = root;
        while(p.right != null)
            p = p.right;
        return p.key;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean containsKey(Object key) {
        return FindKey(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public String get(Object key) {
        MySplayMapNode x = FindKey(key);
        return x != null ? x.data : null;
    }

    @Override
    public String put(Integer key, String value) {
        MySplayMapNode exist = FindKey(key);
        if(exist != null) {
            String prev = exist.data;
            exist.data = value;
            return prev;
        } else {
            ++size;
            MySplayMapNode x = putKey(key);
            x.data = value;
            Splay(x);
            return null;
        }
    }

    @Override
    public String remove(Object key) {
        MySplayMapNode exist = FindKey(key);
        if(exist == null)
            return null;
        String prev = exist.data;
        --size;
        removeNode(exist);
        return prev;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {

    }

    @Override
    public void clear() {
        while(!isEmpty())
            remove(root.key);
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
}
