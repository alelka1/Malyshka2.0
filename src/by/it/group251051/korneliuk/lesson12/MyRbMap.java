package by.it.group251051.korneliuk.lesson12;

import java.util.*;

public class MyRbMap implements SortedMap<Integer, String>{

    private static class Str{
        String res;

        Str(String s) {
            res = s;
        }
    }

    MyRbMapNode root = null;
    int size = 0;

    @Override
    public Comparator<? super Integer> comparator() {
        return null;
    }

    @Override
    public SortedMap<Integer, String> subMap(Integer fromKey, Integer toKey) {
        return null;
    }

    private void traverseHead(MyRbMapNode p, SortedMap<Integer, String> res, Integer toKey){
        if(p == null)
            return;
        if(p.left != null)
            traverseHead(p.left, res, toKey);
        if(p.right != null)
            traverseHead(p.right, res, toKey);
        if(p.key < toKey)
            res.put(p.key, p.data);
    }

    @Override
    public SortedMap<Integer, String> headMap(Integer toKey) {
        MyRbMap res = new MyRbMap();
        traverseHead(root, res, toKey);
        return res;
    }

    private void traverseTail(MyRbMapNode p, SortedMap<Integer, String> res, Integer fromKey){
        if(p == null)
            return;
        if(p.left != null)
            traverseTail(p.left, res, fromKey);
        if(p.right != null)
            traverseTail(p.right, res, fromKey);
        if(p.key >= fromKey)
            res.put(p.key, p.data);
    }

    @Override
    public SortedMap<Integer, String> tailMap(Integer fromKey) {
        MyRbMap ans = new MyRbMap();
        traverseTail(root, ans, fromKey);
        return ans;
    }

    private void traverse(MyRbMapNode p, Str res){
        if(p == null)
            return;
        if(p.left != null)
            traverse(p.left, res);
        res.res += p.key.toString() + "=" + p.data + ", ";
        if(p.right != null)
            traverse(p.right, res);
    }

    public String toString(){
        Str res = new Str("{");
        traverse(root, res);
        if(res.res.length() > 2)
            res.res = res.res.substring(0, res.res.length() - 2);
        return res.res + "}";
    }

    @Override
    public Integer firstKey() {
        MyRbMapNode p = root;
        while(p.left != null)
            p = p.left;
        return p.key;
    }

    @Override
    public Integer lastKey() {
        MyRbMapNode p = root;
        while(p.right != null)
            p = p.right;
        return p.key;
    }

    @Override
    public int size() {
        return size;
    }

    private boolean getColor(MyRbMapNode p){
        return p != null && p.color;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    private MyRbMapNode FindKey(Object key){
        MyRbMapNode p = root;
        while(p != null && !p.key.equals(key)){
            p = (Integer)key < p.key ? p.left : p.right;
        }
        return p;
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
        MyRbMapNode p = FindKey(key);
        return p != null ? p.data : null;
    }

    private void leftRotate(MyRbMapNode n){
        MyRbMapNode nr = n.right;

        n.right = nr.left;
        if(nr.left != null)
            nr.left.parent = n;
        nr.parent = n.parent;
        if(nr.parent != null)
            if(nr.parent.left == n)
                nr.parent.left = nr;
            else
                nr.parent.right = nr;
        n.parent = nr;
        nr.left = n;
        if(root == n)
            root = nr;
    }

    private void rightRotate(MyRbMapNode n) {
        MyRbMapNode nl = n.left;

        n.left = nl.right;
        if(nl.right != null)
            nl.right.parent = n;
        nl.parent = n.parent;
        if(nl.parent != null)
            if(nl.parent.left == n)
                nl.parent.left = nl;
            else
                nl.parent.right = nl;
        n.parent = nl;
        nl.right = n;
        if(root == n)
            root = nl;
    }

    private MyRbMapNode getParent(MyRbMapNode n){
        return n.parent;
    }

    private MyRbMapNode getGrandparent(MyRbMapNode n){
        return n.parent != null ? n.parent.parent : null;
    }

    private MyRbMapNode getUncle(MyRbMapNode n){
        if(n.parent == null || n.parent.parent == null)
            return null;
        else if(n.parent == n.parent.parent.left)
            return n.parent.parent.right;
        else
            return n.parent.parent.left;
    }

    private void fixInsertion(MyRbMapNode t) {
        if (root == t) {
            t.color = false;
            return;
        }
        while (getColor(t.parent)) {
            MyRbMapNode p = getParent(t), g = getGrandparent(t), u = getUncle(t);
            if(getColor(p) && getColor(u)){
                p.color = false;
                u.color = false;
                g.color = true;
                t = g;
            } else {
                if(p != null && p.right == t){
                    leftRotate(p);
                    MyRbMapNode temp = t;
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
        MyRbMapNode t = new MyRbMapNode(key);
        t.color = true;
        if(isEmpty())
            root = t;
        else {
            MyRbMapNode p = root;
            MyRbMapNode q = null;
            while(p != null) {
                q = p;
                if(p.key < t.key)
                    p = p.right;
                else
                    p = p.left;
            }
            t.parent = q;
            if(q.key < t.key)
                q.right = t;
            else
                q.left = t;
        }
        fixInsertion(t);
    }

    @Override
    public String put(Integer key, String value) {
        String prev = get(key);
        if(!containsKey(key)) {
            insertKey(key);
            ++size;
        }
        FindKey(key).data = value;
        return prev;
    }

    private void deleteLeaveMyRbMapNode(MyRbMapNode del){
        if(del == root)
            root = null;
        else if (del.parent.left == del)
            del.parent.left = null;
        else
            del.parent.right = null;
    }

    private MyRbMapNode getFirstGrater(MyRbMapNode p){
        p = p.right;
        while(p.left != null)
            p = p.left;
        return p;
    }

    private MyRbMapNode getBrother(MyRbMapNode t){
        MyRbMapNode p = getParent(t);
        if(p != null)
            return p.right == t ? p.left : p.right;
        return null;
    }

    private MyRbMapNode getLeftNephew(MyRbMapNode t){
        MyRbMapNode b = getBrother(t);
        return b != null ? b.left : null;
    }

    private MyRbMapNode getRightNephew(MyRbMapNode t){
        MyRbMapNode b = getBrother(t);
        return b != null ? b.right : null;
    }

    private void fixDelete(MyRbMapNode x){
        MyRbMapNode p = getParent(x), b = getBrother(x), ln = getLeftNephew(x), rn = getRightNephew(x);
        if(b != null){
            if(!getColor(x) && !getColor(p) && getColor(b) && !getColor(ln) && !getColor(rn)){
                leftRotate(p);
                p = getParent(x);
                b = getBrother(x);
                ln = getLeftNephew(x);
                rn = getRightNephew(x);
                if(!getColor(b) && getColor(ln) && !getColor(rn)){
                    rightRotate(b);
                    b.color = true;
                    ln.color = false;
                    p = getParent(x);
                    b = getBrother(x);
                    ln = getLeftNephew(x);
                    rn = getRightNephew(x);
                }
                if(!getColor(b) && getColor(rn)){
                    leftRotate(p);
                    p.color = false;
                    rn.color = false;
                    p = getParent(x);
                    b = getBrother(x);
                    ln = getLeftNephew(x);
                    rn = getRightNephew(x);
                }
            }
            if(b != null && getColor(p) && !getColor(b) && !getColor(ln) && !getColor(rn)){
                p.color = false;
                b.color = true;
                fixDelete(p);
            }
        }
    }

    @Override
    public String remove(Object key){
        return deleteByKey(FindKey(key));
    }

    private String deleteByKey(MyRbMapNode del) {
        if(del == null)
            return null;
        String res = del.data;
        if(del.left == null && del.right == null) {
            deleteLeaveMyRbMapNode(del);
            --size;
        }
        else if(del.left == null || del.right == null){
            --size;
            if(del.right != null) {
                del.right.parent = del.parent;
                if(getColor(del.parent) && del.right.color)
                    del.right.color = false;
                if(del.parent == null)
                    root = del.right;
                else if (del.parent.left == del)
                    del.parent.left = del.right;
                else
                    del.parent.right = del.right;
            } else {
                if(getColor(del.parent) && del.left.color)
                    del.left.color = false;
                del.left.parent = del.parent;
                if(del.parent == null)
                    root = del.left;
                else if (del.parent.left == del)
                    del.parent.left = del.left;
                else
                    del.parent.right = del.left;
            }
        } else {
            MyRbMapNode x = getFirstGrater(del);
            String str = x.data;
            Integer temp = x.key;
            x.data = del.data;
            x.key = del.key;

            del.data = str;
            del.key = temp;
            if(!x.color)
                fixDelete(x);
            deleteByKey(x);
        }
        return res;
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
