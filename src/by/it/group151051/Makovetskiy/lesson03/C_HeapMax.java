package by.it.group151051.Makovetskiy.lesson03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

// Lesson 3. C_Heap.
// Задача: построить max-кучу = пирамиду = бинарное сбалансированное дерево на массиве.
// ВАЖНО! НЕЛЬЗЯ ИСПОЛЬЗОВАТЬ НИКАКИЕ КОЛЛЕКЦИИ, КРОМЕ ARRAYLIST (его можно, но только для массива)

//      Проверка проводится по данным файла
//      Первая строка входа содержит число операций 1 ≤ n ≤ 100000.
//      Каждая из последующих nn строк задают операцию одного из следующих двух типов:

//      Insert x, где 0 ≤ x ≤ 1000000000 — целое число;
//      ExtractMax.

//      Первая операция добавляет число x в очередь с приоритетами,
//      вторая — извлекает максимальное число и выводит его.

//      Sample Input:
//      6
//      Insert 200
//      Insert 10
//      ExtractMax
//      Insert 5
//      Insert 500
//      ExtractMax
//
//      Sample Output:
//      200
//      500


public class C_HeapMax {

    private class MaxHeap {
        Node root;

        void insert(Long value) {
            if (root == null) {
                root = new Node(value);
            }
            else {
                insertRecursive(value, root);
            }
        }

        void insertRecursive(Long value, Node parent) {
            if (value < parent.value) {
                if (parent.left == null) {
                    parent.left = new Node(value);
                }
                else {
                    insertRecursive(value, parent.left);
                }
            }
            else if (value > parent.value) {
                if (parent.right == null) {
                    parent.right = new Node(value);
                }
                else {
                    insertRecursive(value, parent.right);
                }
            }
        }

        Long extractMax() {
            Node curr = root;
            long max;

            while (curr.right != null) {
                curr = curr.right;
            }
            max = curr.value;

            if (root.left != null) {
                curr = root.left;
                while (curr.right != null) {
                    curr = curr.right;
                }
                if (curr.value > max) {
                    max = curr.value;
                }
            }

            return max;
        }

        private class Node {
            Long value;
            Node left;
            Node right;

            public Node(Long value) {
                this.value = value;
                this.left = null;
                this.right = null;
            }
        }
    }

    //эта процедура читает данные из файла, ее можно не менять.
    Long findMaxValue(InputStream stream) {
        long maxValue = 0L;
        MaxHeap heap = new MaxHeap();
        Scanner scanner = new Scanner(stream);
        int count = scanner.nextInt();
        for (int i = 0; i < count; ) {
            String s = scanner.nextLine();
            if (s.equalsIgnoreCase("extractMax")) {
                Long res = heap.extractMax();
                if (res != null && res > maxValue) maxValue = res;
                System.out.println();
                i++;
            }
            if (s.contains(" ")) {
                String[] p = s.split(" ");
                if (p[0].equalsIgnoreCase("insert"))
                    heap.insert(Long.parseLong(p[1]));
                i++;
            }
        }
        return maxValue;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson03/heapData.txt");
        C_HeapMax instance = new C_HeapMax();
        System.out.println("MAX=" + instance.findMaxValue(stream));
    }
}
