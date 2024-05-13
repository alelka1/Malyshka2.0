package by.it.m_loika.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class C_GetInversions {

    // Метод для слияния двух отсортированных массивов
    private int merge(int[] left, int[] right, int[] merged) {
        int inversionCount = 0;
        int i = 0, j = 0, k = 0;

        // Слияние элементов в результирующий массив
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                merged[k++] = left[i++];
            } else {
                merged[k++] = right[j++];
                // Если элемент из правой части меньше, чем элемент из левой, то это инверсия
                // Так как left[] и right[] уже отсортированы, то все оставшиеся элементы в left[] тоже больше right[j]
                inversionCount += left.length - i;
            }
        }

        // Добавление оставшихся элементов из левой и правой части, если они есть
        while (i < left.length) {
            merged[k++] = left[i++];
        }
        while (j < right.length) {
            merged[k++] = right[j++];
        }

        return inversionCount;
    }

    // Метод для сортировки слиянием и подсчета числа инверсий
    private int mergeSortAndCountInversions(int[] array) {
        if (array.length <= 1) {
            return 0; // Если массив содержит 1 элемент или меньше, инверсий нет
        }

        // Разделение массива на две половины
        int mid = array.length / 2;
        int[] left = new int[mid];
        int[] right = new int[array.length - mid];
        System.arraycopy(array, 0, left, 0, mid);
        System.arraycopy(array, mid, right, 0, array.length - mid);

        // Рекурсивный вызов для сортировки каждой половины
        int inversionCount = mergeSortAndCountInversions(left);
        inversionCount += mergeSortAndCountInversions(right);

        // Слияние отсортированных половин и подсчет инверсий
        inversionCount += merge(left, right, array);

        return inversionCount;
    }

    int calc(InputStream stream) throws FileNotFoundException {
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

        // Сортировка слиянием и подсчет числа инверсий
        return mergeSortAndCountInversions(a);
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/m_loika/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        int result = instance.calc(stream);
        System.out.print(result);
    }
}
