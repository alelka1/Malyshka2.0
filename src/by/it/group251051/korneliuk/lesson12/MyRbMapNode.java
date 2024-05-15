package by.it.group251051.korneliuk.lesson12;

public class MyRbMapNode {
    Integer key;
    String data;
    boolean color;
    MyRbMapNode left, right, parent;
    MyRbMapNode(Integer k){
        key = k;
        data = null;
        color = false;
        left = right = parent = null;
    }
}
