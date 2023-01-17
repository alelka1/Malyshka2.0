package by.it.group151051.Makovetskiy.lesson04;

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

    int[] getMergeSort(InputStream stream)  {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        mergeSort(a);
        return a;
    }

     void mergeSort(int[] array) {

        if (array.length < 2) return;

        int[] left;
        int[] right;
        int size = array.length;
        //Сценарий для массива нечетной размерности
        if (size%2 != 0) {
            left = new int[(size/2)+1];
            right = new int[size/2];
            System.arraycopy(array, 0, left, 0, (size/2)+1);
            System.arraycopy(array, (size/2)+1, right, 0, size/2 );
        }
        //Сценарий для массива чётной размерности
        else {
            left = new int[size/2];
            right = new int[size/2];
            System.arraycopy(array, 0, left, 0, size/2);
            System.arraycopy(array, size/2, right, 0, size/2);
        }
        //Рекурсивные вызовы для разбиения большого массива до состояния двух массивов с одним числом
         mergeSort(left);
         mergeSort(right);
         //Слияние массивов
         merge(array, left, right);
     }

     void merge (int[] array, int[] left, int[] right) {
        int i = 0;
        int j = 0;
        int k = 0;
        //Основной алгоритм сортировки
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k] = left[i];
                i++;
            }
            else {
                array[k] = right[j];
                j++;
            }
            k++;
        }
        //На случай, если массив был нечетной размерности
        while (i < left.length) {
            array[k] = left[i];
            i++;
            k++;
        }

        while (j < right.length) {
            array[k] = right[j];
            j++;
            k++;
        }
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
