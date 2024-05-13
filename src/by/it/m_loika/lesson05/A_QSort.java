package by.it.m_loika.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class A_QSort {

    // Отрезок
    private class Segment {
        int start;
        int stop;

        Segment(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }
    }

    int[] getAccessory(InputStream stream) throws FileNotFoundException {
        // Подготовка к чтению данных
        Scanner scanner = new Scanner(stream);

        // Число отрезков
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

        // Сортировка отрезков по начальной координате
        Arrays.sort(segments, Comparator.comparingInt(segment -> segment.start));

        // Для каждой точки определяем количество отрезков, в которые она попадает
        for (int i = 0; i < m; i++) {
            int count = 0;
            for (Segment segment : segments) {
                if (points[i] >= segment.start && points[i] <= segment.stop) {
                    count++;
                } else if (points[i] < segment.start) {
                    break;
                }
            }
            result[i] = count;
        }

        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/m_loika/lesson05/dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result = instance.getAccessory(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }
}
