package by.it.m_loika.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class B_MergeSort {

    // Метод для сортировки слиянием
    int[] mergeSort(int[] array) {
        if (array.length <= 1) {
            return array; // Если массив содержит 1 элемент или меньше, он уже отсортирован
        }

        // Разделение массива на две половины
        int mid = array.length / 2;
        int[] left = new int[mid];
        int[] right = new int[array.length - mid];
        System.arraycopy(array, 0, left, 0, mid);
        System.arraycopy(array, mid, right, 0, array.length - mid);

        // Рекурсивный вызов для сортировки каждой половины
        left = mergeSort(left);
        right = mergeSort(right);

        // Слияние отсортированных половин
        return merge(left, right);
    }

    // Метод для слияния двух отсортированных массивов
    private int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;

        // Слияние элементов в результирующий массив
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        // Добавление оставшихся элементов из левого и правого массивов, если они есть
        while (i < left.length) {
            result[k++] = left[i++];
        }
        while (j < right.length) {
            result[k++] = right[j++];
        }

        return result;
    }

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);

        // Чтение размера массива
        int n = scanner.nextInt();
        // Создание массива
        int[] a = new int[n];
        // Чтение элементов массива
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        // Сортировка слиянием
        a = mergeSort(a);

        return a;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/m_loika/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        int[] result = instance.getMergeSort(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }
}
