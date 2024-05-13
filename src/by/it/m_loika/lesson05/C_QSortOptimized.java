package by.it.m_loika.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class C_QSortOptimized {

    // Отрезок
    private class Segment implements Comparable<Segment> {
        int start;
        int stop;

        Segment(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Segment o) {
            return Integer.compare(this.start, o.start);
        }
    }

    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        // Подготовка к чтению данных
        Scanner scanner = new Scanner(stream);

        // Число отрезков отсортированного массива
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];

        // Число точек
        int m = scanner.nextInt();
        int[] points = new int[m];
        int[] result = new int[m];

        // Чтение отрезков
        for (int i = 0; i < n; i++) {
            segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
        }

        // Чтение точек
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }

        // Сортировка массива отрезков на месте с помощью 3-разбиения
        Arrays.sort(segments);

        // Поиск для каждой точки
        for (int i = 0; i < m; i++) {
            int point = points[i];
            result[i] = countCoveringSegments(segments, point);
        }

        return result;
    }

    // Метод для подсчета количества отрезков, покрывающих данную точку
    private int countCoveringSegments(Segment[] segments, int point) {
        int count = 0;
        for (Segment segment : segments) {
            if (segment.start <= point && point <= segment.stop) {
                count++;
            }
            if (segment.start > point) {
                break;
            }
        }
        return count;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result = instance.getAccessory2(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }
}
