package by.it.group251051.prach.lesson05;

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
        // Подготовка к чтению данных
        Scanner scanner = new Scanner(stream);

        // Размер массива
        int n = scanner.nextInt();
        int[] points = new int[n];

        // Чтение точек
        for (int i = 0; i < n; i++) {
            points[i] = scanner.nextInt();
        }

        // Нахождение максимального значения в массиве
        int max = Integer.MIN_VALUE;
        for (int num : points) {
            if (num > max) {
                max = num;
            }
        }

        // Создание массива для подсчета количества встречаемости чисел
        int[] countArray = new int[max + 1];

        // Подсчет количества встреч каждого числа
        for (int num : points) {
            countArray[num]++;
        }

        // Восстановление упорядоченного массива
        int index = 0;
        for (int i = 0; i < countArray.length; i++) {
            while (countArray[i] > 0) {
                points[index] = i;
                index++;
                countArray[i]--;
            }
        }

        return points;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataB.txt");
        B_CountSort instance = new B_CountSort();
        int[] result=instance.countSort(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
