package by.it.group251051.prach.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class B_MergeSort {

    // Метод для слияния двух подмассивов arr[l..m] и arr[m+1..r] массива arr[]
    void merge(int[] arr, int l, int m, int r) {
        // Находим размеры двух подмассивов для слияния
        int n1 = m - l + 1;
        int n2 = r - m;

        // Создаем временные массивы
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Копируем данные во временные массивы L[] и R[]
        for (int i = 0; i < n1; ++i) {
            L[i] = arr[l + i];
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = arr[m + 1 + j];
        }

        // Слияние временных массивов
        int i = 0, j = 0;

        // Индекс начала слияния в исходном массиве
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Копирование оставшихся элементов L[], если таковые есть
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Копирование оставшихся элементов R[], если таковые есть
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Основной метод, который выполняет сортировку слиянием массива arr[]
    void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            // Находим середину массива
            int m = (l + r) / 2;

            // Рекурсивно сортируем два подмассива
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            // Сливаем два отсортированных массива
            merge(arr, l, m, r);
        }
    }

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);

        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        mergeSort(a, 0, n - 1); // Вызываем сортировку слиянием

        return a;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        int[] result = instance.getMergeSort(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }
}