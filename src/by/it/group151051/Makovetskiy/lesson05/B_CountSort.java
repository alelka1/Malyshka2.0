package by.it.group151051.Makovetskiy.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
Первая строка содержит число 1<=n<=10000, вторая - n натуральных чисел, не превышающих 10.
Выведите упорядоченную по неубыванию последовательность этих чисел.

При сортировке реализуйте метод со сложностью O(n)

Пример: https://karussell.wordpress.com/2010/03/01/fast-integer-sorting-algorithm-on/
Вольный перевод: http://programador.ru/sorting-positive-int-linear-time/
*/

public class B_CountSort {


    int[] countSort(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] points=new int[n];
        for (int i = 0; i < n; i++) {
            points[i]=scanner.nextInt();
        }

        int min = points[0];
        int max = points[0];

        for (int i = 1; i < points.length; i++) {
            if (points[i] < min) {
                min = points[i];
            }
            if (points[i] > max) {
                max = points[i];
            }
        }
        return sort(points, min, max);
    }

    int[] sort(int[] array, int min, int max) {
        int[] freq = new int[max - min + 1];
        int[] result = new int[max - min + 1];
        Arrays.fill(freq, 0);

        for (int i = 0; i < array.length; i++) {
            int curr = array[i];
            int cnt = 0;
            for (int j = 0; j < array.length; j++) {
                if (array[j] == curr) {
                    cnt++;
                }
            }
            freq[curr] = cnt;
        }
        StringBuilder arr = new StringBuilder();
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > 0) {
                for (int j = 0; j < freq[i]; j++) {
                    arr.append(i);
                }
            }
        }
        int index = 0;
        for (char x : arr.toString().toCharArray()) {
            result[index] = Integer.parseInt(String.valueOf(x));
            index++;
        }
        return result;
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
