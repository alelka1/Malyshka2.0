package by.it.group251051.kozlovski.lesson05;

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

        quickSort(segments,0,segments.length-1); //быстрая сортировка
        for (int i = 0; i < segments.length; i++) { // сравниваем сегменты с точками и выводим результат
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

    public void quickSort(Segment array[], int low, int high) {
        while(low < high) {
            int[] Divide_Index = Divide(array, low, high); // найдем точку раздела
            int left = Divide_Index[0];
            int right = Divide_Index[1];
            quickSort(array, low, left);
            low = right;
        }
    }

    private int[] Divide(Segment array[], int low, int high) { // функция раздела
        Segment segment = array[low]; // получаем первый отрезок
        int j = (low-1); //индекс точки раздела
        for (int i = low; i < high; i++) { //пока не кончаться элементы
            if(array[i].compareTo(segment) <=0) {  //сравниваем элементы массива
                j++; // перемещаем точку раздела и размещаем меньшие элементы до него, большие - после
                Segment swap = array[j];
                array[j] = array[i];
                array[i] = swap;
            }
        }
        Segment swap = array[j+1];
        array[j+1] = array[high];
        array[high] = swap;

        int[] result = new int[2];

        int right = j;

        for(; low < high; low++) {
            if(array[low].compareTo(segment) == 0) {
                right++;
                swap = array[j];
                array[j] = array[right];
                array[right] = swap;
            }
        }

        result[0] = j;
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