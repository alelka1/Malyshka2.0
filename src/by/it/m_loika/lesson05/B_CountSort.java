package by.it.m_loika.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class B_CountSort {

    int[] countSort(InputStream stream) throws FileNotFoundException {
        // Подготовка к чтению данных
        Scanner scanner = new Scanner(stream);

        // Чтение размера массива
        int n = scanner.nextInt();
        int[] points = new int[n];

        // Чтение и сохранение точек
        for (int i = 0; i < n; i++) {
            points[i] = scanner.nextInt();
        }

        // Создание массива для подсчета количества вхождений каждого числа
        int max = 10; // Максимальное значение чисел во входном массиве
        int[] countArray = new int[max + 1];

        // Подсчет количества вхождений каждого числа
        for (int num : points) {
            countArray[num]++;
        }

        // Восстановление отсортированного массива
        int index = 0;
        for (int i = 0; i < countArray.length; i++) {
            while (countArray[i] > 0) {
                points[index++] = i;
                countArray[i]--;
            }
        }

        return points;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/m_loika/lesson05/dataB.txt");
        B_CountSort instance = new B_CountSort();
        int[] result = instance.countSort(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }
}

