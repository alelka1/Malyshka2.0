package by.it.group251051.smalianko.lesson12;

public class AVLMAPNode {
    int key;
    String value;
    int Height;
    AVLMAPNode Left, Right;

    AVLMAPNode(int key, String value) {
        this.key = key;
        this.value = value;
        this.Height = 1;
    }
}
