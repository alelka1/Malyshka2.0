package by.it.group251051.kozlovski.lesson12;

public class MyRbNode{
    Integer key;
    String value;
    boolean color;
    MyRbNode left, right, parrent;
    MyRbNode(Integer k){
        key = k;
        value = null;
        color = false;
        left = right = parrent = null;
    }

}
