package by.it.group251051.kozlovski.lesson12;

import java.util.*;

public class MyRbMap implements SortedMap<Integer, String>{

    private class Str{
        String res;
    }

    MyRbNode root = null;
    int size = 0;

    public String toString(){
        Str res = new Str();
        res.res = "{";
        traverse(root, res);
        if(res.res.length() > 2)
            res.res = res.res.substring(0, res.res.length() - 2);
        return res.res + "}";
    }
    @Override
    public String put(Integer key, String value) {
        String prev = get(key);
        if(!containsKey(key)) {
            insertKey(key);
            ++size;
        }
        FindKey(key).value = value;
        return prev;
    }
    @Override
    public String remove(Object key){
        return removeNode(FindKey(key));
    }
    @Override
    public String get(Object key) {
        MyRbNode p = FindKey(key);
        return p != null ? p.value : null;
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
    public int size() {
        return size;
    }
    @Override
    public void clear() {
        while(!isEmpty())
            remove(root.key);
    }
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public SortedMap<Integer, String> headMap(Integer toKey) {
        MyRbMap ans = new MyRbMap();
        traverseHeadMap(root, ans, toKey);
        return ans;
    }
    @Override
    public SortedMap<Integer, String> tailMap(Integer fromKey) {
        MyRbMap ans = new MyRbMap();
        traverseTailMap(root, ans, fromKey);
        return ans;
    }
    @Override
    public Integer firstKey() {
        MyRbNode p = root;
        while(p != null && p.left != null)
            p = p.left;
        return p != null ? p.key : null;
    }
    @Override
    public Integer lastKey() {
        MyRbNode p = root;
        while(p != null && p.right != null)
            p = p.right;
        return p != null ? p.key : null;
    }

    @Override
    public Comparator<? super Integer> comparator() {
        return null;
    }

    @Override
    public SortedMap<Integer, String> subMap(Integer fromKey, Integer toKey) {
        return null;
    }

    private void traverseHeadMap(MyRbNode p, SortedMap<Integer, String> res, Integer toKey){
        if(p == null) return;
        if(p.left != null)
            traverseHeadMap(p.left, res, toKey);
        if(p.key < toKey)
            res.put(p.key, p.value);
        if(p.right != null)
            traverseHeadMap(p.right, res, toKey);
    }

    private void traverseTailMap(MyRbNode p, SortedMap<Integer, String> res, Integer fromKey){
        if(p == null) return;
        if(p.left != null)
            traverseTailMap(p.left, res, fromKey);
        if(p.key >= fromKey)
            res.put(p.key, p.value);
        if(p.right != null)
            traverseTailMap(p.right, res, fromKey);
    }

    private void traverse(MyRbNode p, Str res){
        if(p == null) return;
        if(p.left != null)
            traverse(p.left, res);
        res.res += p.key.toString() + "=" + p.value + ", ";
        if(p.right != null)
            traverse(p.right, res);
    }

    private boolean getColor(MyRbNode p){
        return p == null ? false : p.color;
    }

    private MyRbNode FindKey(Object key){
        MyRbNode p = root;
        while(p != null && !p.key.equals(key)){
            if((Integer)key < p.key)
                p = p.left;
            else
                p = p.right;
        }
        return p;
    }

    private void leftRotate(MyRbNode n){
        MyRbNode a = n, b = a.right, l = a.left, c = b.left, r = b.right;

        a.right = c;
        if(c != null)
            c.parrent = a;
        b.parrent = a.parrent;
        if(b.parrent != null)
            if(b.parrent.left == a)
                b.parrent.left = b;
            else
                b.parrent.right = b;
        a.parrent = b;
        b.left = a;
        if(root == a)
            root = b;
    }

    private void rightRotate(MyRbNode n) {
        MyRbNode a = n, b = a.left, l = b.left, c = b.right, r = a.right;

        a.left = c;
        if(c != null)
            c.parrent = a;
        b.parrent = a.parrent;
        if(b.parrent != null)
            if(b.parrent.left == a)
                b.parrent.left = b;
            else
                b.parrent.right = b;
        a.parrent = b;
        b.right = a;
        if(root == a)
            root = b;
    }

    private MyRbNode getParent(MyRbNode n){
        return n.parrent;
    }

    private MyRbNode getGrandparent(MyRbNode n){
        if(n.parrent != null)
            return n.parrent.parrent;
        return null;
    }

    private MyRbNode getUncle(MyRbNode n){
        if(n.parrent != null && n.parrent.parrent != null)
            if(n.parrent == n.parrent.parrent.left)
                return n.parrent.parrent.right;
            else
                return n.parrent.parrent.left;
        return null;
    }

    private void fixInsertion(MyRbNode t) {
        if (root == t) {
            t.color = false;
            return;
        }
        while (getColor(t.parrent) == true) {
            MyRbNode p = getParent(t), g = getGrandparent(t), u = getUncle(t);
            if(getColor(p) == true && getColor(u) == true){
                p.color = false;
                u.color = false;
                g.color = true;
                t = g;
            } else {
                if(p != null && p.right == t){
                    leftRotate(p);
                    MyRbNode temp = t;
                    t = p;
                    p = temp;
                }

                if(g != null && g.left == p)
                    rightRotate(g);
                else if(g != null && g.right == p)
                    leftRotate(g);
                if(p != null)
                    p.color = false;
                if(g != null)
                    g.color = true;
                if(u != null)
                    u.color = false;
                t = root;
            }
        }
        root.color = false;
    }

    private void insertKey(Integer key) {
        MyRbNode t = new MyRbNode(key);
        t.color = true;
        if(isEmpty())
            root = t;
        else {
            MyRbNode p = root;
            MyRbNode q = null;
            while(p != null) {
                q = p;
                if(p.key < t.key)
                    p = p.right;
                else
                    p = p.left;
            }
            t.parrent = q;
            if(q.key < t.key)
                q.right = t;
            else
                q.left = t;
        }
        fixInsertion(t);
    }

    private void deleteLeaveNode(MyRbNode del){
        if(del == root) {
            root = null;
            del = null;
        } else {
            if (del.parrent.left == del)
                del.parrent.left = null;
            else
                del.parrent.right = null;
            del = null;
        }
    }

    private MyRbNode FindFirstGrater(MyRbNode p){
        p = p.right;
        while(p.left != null)
            p = p.left;
        return p;
    }

    private MyRbNode getBrother(MyRbNode t){
        MyRbNode p = getParent(t);
        if(p != null)
            return p.right == t ? p.left : p.right;
        return null;
    }

    private MyRbNode getLeftNephew(MyRbNode t){
        MyRbNode b = getBrother(t);
        return b != null ? b.left : null;
    }

    private MyRbNode getRightNephew(MyRbNode t){
        MyRbNode b = getBrother(t);
        return b != null ? b.right : null;
    }

    private void fixDeleting(MyRbNode x){
        MyRbNode p = getParent(x), b = getBrother(x), ln = getLeftNephew(x), rn = getRightNephew(x);
        if(b != null){
            if(!getColor(x) && !getColor(p) && getColor(b) && !getColor(ln) && !getColor(rn)){
                leftRotate(p);
                p = getParent(x);
                b = getBrother(x);
                ln = getLeftNephew(x);
                rn = getRightNephew(x);
                if(!getColor(x) && !getColor(b) && getColor(ln) && !getColor(rn)){
                    rightRotate(b);
                    b.color = true;
                    ln.color = false;
                    p = getParent(x);
                    b = getBrother(x);
                    ln = getLeftNephew(x);
                    rn = getRightNephew(x);
                }
                if(!getColor(x) && !getColor(b) && getColor(rn)){
                    leftRotate(p);
                    p.color = false;
                    rn.color = false;
                    p = getParent(x);
                    b = getBrother(x);
                    ln = getLeftNephew(x);
                    rn = getRightNephew(x);
                }
            }
            if(b != null && !getColor(x) && getColor(p) && !getColor(b) && !getColor(ln) && !getColor(rn)){
                p.color = false;
                b.color = true;
                fixDeleting(p);
            }
        }
    }

    private String removeNode(MyRbNode del) {
        if(del == null)
            return null;
        String res = del.value;
        if(del.left == null && del.right == null) {
            deleteLeaveNode(del);
            --size;
        }else
        if(del.left == null || del.right == null){
            --size;
            if(del.right != null){
                del.right.parrent = del.parrent;
                if(del.parrent != null) {
                    if (del.parrent.left == del)
                        del.parrent.left = del.right;
                    else
                        del.parrent.right = del.right;
                } else
                    root = del.right;
                if(getColor(del.parrent) && del.right.color)
                    del.right.color = false;
                del = null;
            } else {
                del.left.parrent = del.parrent;
                if(del.parrent != null) {
                    if (del.parrent.left == del)
                        del.parrent.left = del.left;
                    else
                        del.parrent.right = del.left;
                } else
                    root = del.left;
                if(getColor(del.parrent) && del.left.color)
                    del.left.color = false;
                del = null;
            }
        } else {
            MyRbNode x = FindFirstGrater(del);
            String tempStr = x.value;
            Integer tempKey = x.key;
            x.value = del.value;
            x.key = del.key;
            del.value = tempStr;
            del.key = tempKey;
            if(!x.color)
                fixDeleting(x);
            removeNode(x);
        }
        return res;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {

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
