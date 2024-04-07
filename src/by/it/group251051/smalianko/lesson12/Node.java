package by.it.group251051.smalianko.lesson12;

public class Node{
    Integer key;
    String value;
    boolean color;
    Node left, right, parrent;
    Node(Integer k){
        key = k;
        value = null;
        color = false;
        left = right = parrent = null;
    }

}