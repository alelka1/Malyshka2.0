package by.it.group251052.ramazanov.lesson05;

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
    int[] countSort(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);

        // Читаем размер массива
        int n = scanner.nextInt();
        int[] originalArray = new int[n];

        // Читаем элементы массива
        for (int i = 0; i < n; i++) {
            originalArray[i] = scanner.nextInt();
        }

        // Находим максимальное значение в массиве
        int maxVal = originalArray[0];
        for (int num : originalArray) {
            if (num > maxVal) {
                maxVal = num;
            }
        }

        // Создаем массив для подсчета частот
        int[] countArray = new int[maxVal + 1];

        // Подсчитываем частоты
        for (int num : originalArray) {
            countArray[num]++;
        }

        // Создаем упорядоченный массив
        int[] sortedArray = new int[n];
        int index = 0;

        // Заполняем упорядоченный массив
        for (int i = 0; i < countArray.length; i++) {
            while (countArray[i] > 0) {
                sortedArray[index] = i;
                index++;
                countArray[i]--;
            }
        }

        return sortedArray;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataB.txt");
        B_CountSort instance = new B_CountSort();
        int[] result = instance.countSort(stream);
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}
