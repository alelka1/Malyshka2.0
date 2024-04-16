package by.it.group251051.smalianko.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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

    //отрезок
    private class Segment implements Comparable<Segment>{
        int start;
        int stop;

        Segment(int start, int stop){
            if (start < stop) {
                this.start = start;
                this.stop = stop;
            } else {
                this.start = stop;
                this.stop = start;
            }
        }

        @Override
        public int compareTo(Segment next) {
            //подумайте, что должен возвращать компаратор отрезков
            return Integer.compare(this.start, next.start);
        }
    }


    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        Segment[] segments=new Segment[n];
        //число точек
        int m = scanner.nextInt();
        int[] points=new int[m];
        int[] result=new int[m];

        //читаем сами отрезки
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            segments[i]=new Segment(scanner.nextInt(),scanner.nextInt());
        }
        //читаем точки
        for (int i = 0; i < m; i++) {
            points[i]=scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор

        quickSort(segments,0,segments.length-1);
        for (int i = 0; i < segments.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (segments[i].start <= points[j] && segments[i].stop >= points[j]) {
                    result[j] += 1;
                } else {
                    result[j] += 0;
                }
            }
        }



            //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    public void quickSort(Segment arr[], int begin, int end) {
        while(begin < end) {
            int[] partitionIndex = partition(arr, begin, end);
            int left = partitionIndex[0];
            int right = partitionIndex[1];
            quickSort(arr, begin, left);
            begin = right;
        }
    }

    private int[] partition(Segment arr[], int begin, int end) {
        Segment segment = arr[begin];
        int i = (begin-1);
        for (int j = begin; j < end; j++) {
            if(arr[j].compareTo(segment) <=0) {
                i++;
                Segment swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }
        Segment swapTemp = arr[i+1];
        arr[i+1] = arr[end];
        arr[end] = swapTemp;

        int[] result = new int[2];

        int right = i;

        for(; begin < end; begin++) {
            if(arr[begin].compareTo(segment) == 0) {
                right++;
                swapTemp = arr[i];
                arr[i] = arr[right];
                arr[right] = swapTemp;
            }
        }

        result[0] = i;
        result[1] = right;

        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
