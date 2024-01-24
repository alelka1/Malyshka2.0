package by.it.group251051.pavlushchenko.lesson03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
        public Long extractMax() {
            return null;
        }

        public void insert(long l) {
        }

        /**НАЧАЛО ЗАДАЧИ*/
        public class Heap<T extends Comparable<T>> {

            private List<T> heap = new ArrayList<>();

            private void siftDown(int i) {
                while (true) {
                    int leftChild = 2 * i + 1;
                    int rightChild = leftChild + 1;
                    int largest = i;

                    if (leftChild < heap.size() && heap.get(leftChild).compareTo(heap.get(largest)) > 0)
                        largest = leftChild;

                    if (rightChild < heap.size() && heap.get(rightChild).compareTo(heap.get(largest)) > 0)
                        largest = rightChild;

                    if (largest == i) break;

                    Collections.swap(heap, largest, i);
                    i = largest;
                }
            }

            private void siftUp(int i) {
                while (i > 0) {
                    int parent = (i - 1) / 2;
                    if (heap.get(parent).compareTo(heap.get(i)) >= 0)
                        break;

                    Collections.swap(heap, parent, i);
                    i = parent;
                }
            }

            public void insert(T value) {
                heap.add(value);
                siftUp(heap.size() - 1);
            }

            public T extractMax() {
                if (heap.size() == 0)
                    return null;

                T result = heap.get(0);
                heap.set(0, heap.get(heap.size() - 1));
                heap.remove(heap.size() - 1);
                siftDown(0);

                return result;
            }
        }

        /**КОНЕЦ ЗАДАЧИ */
    }

    Long findMaxValue(InputStream stream) {
        Long maxValue=0L;
        MaxHeap heap = new MaxHeap();
        Scanner scanner = new Scanner(stream);
        Integer count = scanner.nextInt();
        for (int i = 0; i < count; ) {
            String s = scanner.nextLine();
            if (s.equalsIgnoreCase("extractMax")) {
                Long res=heap.extractMax();
                if (res!=null && res>maxValue) maxValue=res;
                System.out.println();
                i++;
            }
            if (s.contains(" ")) {
                String[] p = s.split(" ");
                if (p[0].equalsIgnoreCase("insert"))
                    heap.insert(Long.parseLong(p[1]));
                i++;
                /**System.out.println(heap); debug*/
            }
        }
        return maxValue;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson03/heapData.txt");
        C_HeapMax instance = new C_HeapMax();
        System.out.println("MAX="+instance.findMaxValue(stream));
    }

}