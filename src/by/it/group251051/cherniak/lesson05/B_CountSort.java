package by.it.group251051.cherniak.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Первая строка содержит число 1<=n<=10000, вторая - n натуральных чисел, не превышающих 10.
Выведите упорядоченную по неубыванию последовательность этих чисел.

При сортировке реализуйте метод со сложностью O(n)

Пример: https://karussell.wordpress.com/2010/03/01/fast-integer-sorting-algorithm-on/
Вольный перевод: http://programador.ru/sorting-positive-int-linear-time/
*/

public class B_CountSort {

    public int[] countSort(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);

        // читаем размер массива
        int n = scanner.nextInt();
        int[] points = new int[n];

        // читаем числа
        for (int i = 0; i < n; i++) {
            points[i] = scanner.nextInt();
        }

        // Инициализируем массив для подсчета
        int[] countArray = new int[11]; // у нас натуральные числа, не превышающие 10, поэтому используем массив
                                        // размером 11

        // Подсчитываем количество каждого числа
        for (int num : points) {
            countArray[num]++;
        }

        // Перезаписываем исходный массив в упорядоченном виде
        int index = 0;
        for (int i = 1; i < countArray.length; i++) {
            while (countArray[i] > 0) {
                points[index++] = i;
                countArray[i]--;
            }
        }

        return points;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group251051/cherniak/lesson05/dataB.txt");
        B_CountSort instance = new B_CountSort();
        int[] result = instance.countSort(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

}
