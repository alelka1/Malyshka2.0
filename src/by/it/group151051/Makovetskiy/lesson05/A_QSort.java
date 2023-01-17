package by.it.group151051.Makovetskiy.lesson05;

import java.io.*;
import java.util.ArrayList;

/*
Видеорегистраторы и площадь.
На площади установлена одна или несколько камер.
Известны данные о том, когда каждая из них включалась и выключалась (отрезки работы)
Известен список событий на площади (время начала каждого события).
Вам необходимо определить для каждого события сколько камер его записали.

В первой строке задано два целых числа:
    число включений камер (отрезки) 1<=n<=50000
    число событий (точки) 1<=m<=50000.

Следующие n строк содержат по два целых числа ai и bi (ai<=bi) -
координаты концов отрезков (время работы одной какой-то камеры).
Последняя строка содержит m целых чисел - координаты точек.
Все координаты не превышают 10E8 по модулю (!).

Точка считается принадлежащей отрезку, если она находится внутри него или на границе.

Для каждой точки в порядке их появления во вводе выведите,
скольким отрезкам она принадлежит.
    Sample Input:
    2 3
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0

*/

public class A_QSort {

    //отрезок
    private class Segment  implements Comparable<Segment>{
        int start;
        int stop;

        Segment(int start, int stop){
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Segment o) {
            return Integer.compare(this.stop, o.start);
        }
    }


    int[] getAccessory(InputStream stream) throws IOException {
        int[] result;
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        line = reader.readLine();
        ArrayList<Segment> segments = new ArrayList<>();
        ArrayList<Integer> res = new ArrayList<>();

        int size = Integer.parseInt(line.split(" ")[0]);
        for (int i = 0; i < size; i++ ) {
            line = reader.readLine();
            String[] tokens = line.split(" ");
            segments.add(new Segment(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1])));
         }
        line = reader.readLine();
        for (String point : line.split(" ")) {
            int count = 0;
            int p = Integer.parseInt(point);
            for (Segment segment : segments) {
                if (p >= segment.start && p <= segment.stop) count++;
            }
            res.add(count);
        }
        result = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            result[i] = res.get(i);
        }
     return result;
    }


    public static void main(String[] args) throws IOException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result=instance.getAccessory(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
