package by.it.group251051.cherniak.lesson03;

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
        // Внутренний класс для представления максимальной кучи

        private List<Long> heap = new ArrayList<>();
        // Список для хранения элементов кучи

        int siftDown(int i) {
            // Процедура для перемещения элемента вниз по куче
            int leftChildIndex = 2 * i + 1;
            int rightChildIndex = 2 * i + 2;
            int largest = i;
            if (leftChildIndex < heap.size() && heap.get(leftChildIndex) > heap.get(largest)) {
                largest = leftChildIndex;
            }
            if (rightChildIndex < heap.size() && heap.get(rightChildIndex) > heap.get(largest)) {
                largest = rightChildIndex;
            }
            if (largest != i) {
                Collections.swap(heap, i, largest);
                siftDown(largest);
            }
            return largest;
        }
        // Процедура для перемещения элемента вниз по куче, если он больше своих
        // потомков

        int siftUp(int i) {
            // Процедура для перемещения элемента вверх по куче
            while (i > 0 && heap.get(i) > heap.get((i - 1) / 2)) {
                Collections.swap(heap, i, (i - 1) / 2);
                i = (i - 1) / 2;
            }
            return i;
        }
        // Процедура для перемещения элемента вверх по куче, если он меньше своего
        // родителя

        void insert(Long value) {
            // Метод для вставки значения в кучу
            heap.add(value);
            siftUp(heap.size() - 1);
        }
        // Метод для добавления элемента в кучу и выполнения процедуры siftUp для
        // поддержания свойств кучи

        Long extractMax() {
            // Метод для извлечения максимального элемента из кучи
            if (heap.isEmpty()) {
                return null;
            }
            long result = heap.get(0); // Получаем максимальное значение
            heap.set(0, heap.get(heap.size() - 1)); // Перемещаем последний элемент на место максимального
            heap.remove(heap.size() - 1); // Удаляем последний элемент (максимальный)
            siftDown(0); // Приводим кучу к состоянию максимальной
            return result; // Возвращаем извлеченный максимальный элемент
        }
        // Метод для извлечения максимального элемента из кучи и выполнения процедуры
        // siftDown для поддержания свойств кучи
    }

    // эта процедура читает данные из файла, ее можно не менять.
    Long findMaxValue(InputStream stream) {
        Long maxValue = 0L;
        MaxHeap heap = new MaxHeap();
        // прочитаем строку для кодирования из тестового файла
        Scanner scanner = new Scanner(stream);
        Integer count = scanner.nextInt();
        for (int i = 0; i < count;) {
            String s = scanner.nextLine();
            if (s.equalsIgnoreCase("extractMax")) {
                Long res = heap.extractMax();
                if (res != null && res > maxValue)
                    maxValue = res;
                System.out.println();
                i++;
            }
            if (s.contains(" ")) {
                String[] p = s.split(" ");
                if (p[0].equalsIgnoreCase("insert"))
                    heap.insert(Long.parseLong(p[1]));
                i++;
                // System.out.println(heap); //debug
            }
        }
        return maxValue;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group251051/cherniak/lesson03/heapData.txt");
        C_HeapMax instance = new C_HeapMax();
        System.out.println("MAX=" + instance.findMaxValue(stream));
    }

    // РЕМАРКА. Это задание исключительно учебное.
    // Свои собственные кучи нужны довольно редко.
    // "В реальном бою" все существенно иначе. Изучите и используйте коллекции
    // TreeSet, TreeMap, PriorityQueue и т.д. с нужным CompareTo() для объекта
    // внутри.
}
