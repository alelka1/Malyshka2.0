package by.it.group251051.stezhko.lesson04;

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
    void mergeSort(int [] values, int[] buffer, int start, int end) {
        if (start == end) return;
        int mid = (start + end) / 2;
        mergeSort(values, buffer, start, mid);
        mergeSort(values, buffer, mid + 1, end);
        int leftIndex = start;
        int rightIndex = mid + 1;
        int bufferIndex = leftIndex;
        while(leftIndex <= mid && rightIndex <= end) {
            if (values[leftIndex] <= values[rightIndex]) {
                buffer[bufferIndex] = values[leftIndex];
                leftIndex++;
            } else {
                buffer[bufferIndex] = values[rightIndex];
                rightIndex++;
            }
            bufferIndex++;
        }
        int i;
        for (i = leftIndex; i <= mid; i++) {
            buffer[bufferIndex] = values[i];
            bufferIndex++;
        }
        for (i = rightIndex; i <= end; i++) {
            buffer[bufferIndex] = values[i];
            bufferIndex++;
        }
        for(i = start; i <= end; i++) {
            values[i] = buffer[i];
        }
    }
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
        mergeSort(a, new int[n], 0, n - 1);
        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием
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
