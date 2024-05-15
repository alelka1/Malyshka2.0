package by.it.group251051.korneliuk.lesson12;

public class MySplayMapNode {
    MySplayMapNode parent, left, right;
    String data;
    Integer key;
    MySplayMapNode(Integer k){
        parent = left = right = null;
        data = null;
        key = k;
    }
}
