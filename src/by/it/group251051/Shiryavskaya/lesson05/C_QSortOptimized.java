package by.it.group251051.Shiryavskaya.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
Видеорегистраторы и площадь 2.
Условие то же что и в задаче А.

        По сравнению с задачей A доработайте алгоритм так, чтобы
        1) он оптимально использовал время и память:
            - за стек отвечает элиминация хвостовой рекурсии,
            - за сам массив отрезков - сортировка на месте
            - рекурсивные вызовы должны проводиться на основе 3-разбиения

        2) при поиске подходящих отрезков для точки реализуйте метод бинарного поиска
        для первого отрезка решения, а затем найдите оставшуюся часть решения
        (т.е. отрезков, подходящих для точки, может быть много)

    Sample Input:
    2 3
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0

*/

public class C_QSortOptimized {

    // отрезок
    private class Segment implements Comparable {
        int start;
        int stop;

        Segment(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Object o) {
            // if (this.start == o.start) {
            // return this.stop - o.stop;
            // }
            // return this.start - o.start;
            return 0;
        }
    }

    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);

        // Число отрезков
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];

        // Число точек
        int m = scanner.nextInt();
        int[] points = new int[m];
        int[] result = new int[m];

        // Считываем сами отрезки
        for (int i = 0; i < n; i++) {
            segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
        }

        // Считываем точки
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }

        // Сортируем отрезки по началам и, если начало одинаковое, то по концам
        Arrays.sort(segments);

        // Для каждой точки находим, скольким отрезкам она принадлежит,
        // используя бинарный поиск для первого отрезка решения
        // а затем находим оставшуюся часть решения

        for (int i = 0; i < m; i++) {
            int point = points[i];
            int count = 0;
            int left = 0;
            int right = n - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (segments[mid].start <= point && point <= segments[mid].stop) {
                    count++;
                    // Помечаем найденный отрезок, чтобы избежать повторного поиска
                    segments[mid].start = Integer.MAX_VALUE;
                    // Ищем оставшуюся часть решения
                    int next = mid + 1;
                    while (next < n && segments[next].start <= point && point <= segments[next].stop) {
                        count++;
                        segments[next].start = Integer.MAX_VALUE;
                        next++;
                    }
                    break;
                } else if (segments[mid].start > point) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            result[i] = count;
        }

        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group251051/Shiryavskaya/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result = instance.getAccessory2(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

}
