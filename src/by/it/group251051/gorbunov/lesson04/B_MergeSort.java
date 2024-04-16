package by.it.group251051.gorbunov.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Arrays;

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
        int[] a=new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);
        }
        return  merge(a, n);
    }

    int[] merge(int[] array, int size) {
        if (size < 2) {
            return array;
        }

        int middle = size / 2;

        int[] left = merge(Arrays.copyOfRange(array, 0, middle), middle);
        int[] right = merge(Arrays.copyOfRange(array, middle, size), size - middle);

        int i, il, ir;
        i = il = ir = 0;

        while (i < size) {
            if ((il < middle) && (ir < size - middle)) {
                if (left[il] < right[ir]) {
                    array[i] = left[il];
                    il++;
                } else {
                    array[i] = right[ir];
                    ir++;
                }
            } else if (il < middle) {
                array[i] = left[il];
                il++;
            } else if (ir < size - middle) {
                array[i] = right[ir];
                ir++;
            }
            i++;
        }

        return array;
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
