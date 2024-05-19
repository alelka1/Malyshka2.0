package by.it.group251051.kozlovski.lesson12;

public class MyAvlNode {
    int key;
    String value;
    int Height;
    MyAvlNode Left, Right;

    MyAvlNode(int key, String value) {
        this.key = key;
        this.value = value;
        this.Height = 1;
    }
}
