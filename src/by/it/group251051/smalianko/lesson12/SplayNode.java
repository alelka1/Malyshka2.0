package by.it.group251051.smalianko.lesson12;

public class SplayNode{
    Integer key;
    String value;
    SplayNode parent, left, right;
    SplayNode(Integer k){key = k; value = null; parent = left = right = null;}
}