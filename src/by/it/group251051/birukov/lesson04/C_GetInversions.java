package by.it.group251051.birukov.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Рассчитать число инверсий одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо посчитать число пар индексов 1<=i<j<n, для которых A[i]>A[j].

    (Такая пара элементов называется инверсией массива.
    Количество инверсий в массиве является в некотором смысле
    его мерой неупорядоченности: например, в упорядоченном по неубыванию
    массиве инверсий нет вообще, а в массиве, упорядоченном по убыванию,
    инверсию образуют каждые (т.е. любые) два элемента.
    )

Sample Input:
5
2 3 9 2 9
Sample Output:
2

Головоломка (т.е. не обязательно).
Попробуйте обеспечить скорость лучше, чем O(n log n) за счет многопоточности.
Докажите рост производительности замерами времени.
Большой тестовый массив можно прочитать свой или сгенерировать его программно.
*/


public class C_GetInversions {

    int calc(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!
        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int result = 0;
        //!!!!!!!!!!!!!!!!!!!!!!!!     тут ваше решение   !!!!!!!!!!!!!!!!!!!!!!!!

        class Inverse {
            public static int inversions;
            public static int[] sort(int[] mas, int start_i, int end_i) {
                if (start_i > end_i - 1) {
                    return new int[]{mas[start_i]};
                }
                int middle_i = (int) Math.floor(start_i + (double) (end_i - start_i) / 2);
                return merge(sort(mas, start_i, middle_i), sort(mas, middle_i + 1, end_i));
            }
            static int[] merge(int[] mas1, int[] mas2) {
                int i1 = 0;
                int i2 = 0;
                int[] merged = new int[mas1.length + mas2.length];
                int merged_i = 0;
                while (i1 < mas1.length && i2 < mas2.length) {
                    if (mas1[i1] <= mas2[i2])
                        merged[merged_i++] = mas1[i1++];
                    else {
                        merged[merged_i++] = mas2[i2++];
                        inversions += (mas1.length - i1);
                    }
                }
                while (i1 < mas1.length)
                    merged[merged_i++] = mas1[i1++];
                while (i2 < mas2.length)
                    merged[merged_i++] = mas2[i2++];
                return merged;
            }
        }
        Inverse.sort(a,0,a.length - 1);
        result = Inverse.inversions;

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        System.out.print(result);
    }
}
