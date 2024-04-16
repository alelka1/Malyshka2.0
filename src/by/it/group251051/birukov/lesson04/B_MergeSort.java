package by.it.group251051.birukov.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Реализуйте сортировку слиянием для одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо отсортировать полученный массив.

Sample Input:
5
2 3 9 2 9
Sample Output:
2 2 3 9 9
*/
public class B_MergeSort {

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);
        }

        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием

        for (int size = 1; size <= n-1; size = 2 * size) {
            for (int left = 0; left < n - 1; left += 2 * size) {
                int mid, i, j, k;
                if (left + size - 1 < n - 1)
                    mid = left + size - 1;
                else
                    mid = n - 1;
                int right;
                if (left + 2 * size - 1 < n - 1)
                    right = left + 2 * size - 1;
                else
                    right = n - 1;
                int n1 = mid - left + 1, n2 = right - mid;
                int[] left_m = new int[n1];
                int[] right_m = new int[n2];
                for (i = 0; i < n1; i++)
                    left_m[i] = a[left + i];
                for (j = 0; j < n2; j++)
                    right_m[j] = a[mid + 1 + j];
                i = 0;
                j = 0;
                k = left;
                while (i < n1 && j < n2) {
                    if (left_m[i] <= right_m[j]) {
                        a[k] = left_m[i];
                        i++;
                    } else {
                        a[k] = right_m[j];
                        j++;
                    }
                    k++;
                }
                while (i < n1) {
                    a[k] = left_m[i];
                    i++;
                    k++;
                }
                while (j < n2) {
                    a[k] = right_m[j];
                    j++;
                    k++;
                }
            }
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
