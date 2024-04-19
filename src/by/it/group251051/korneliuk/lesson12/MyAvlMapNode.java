package by.it.group251051.korneliuk.lesson12;

public class MyAvlMapNode {
    MyAvlMapNode left, right;
    int height, key;
    String value;

    MyAvlMapNode(int key, String value) {
        this.key = key;
        this.value = value;
        this.height = 1;
    }
}
