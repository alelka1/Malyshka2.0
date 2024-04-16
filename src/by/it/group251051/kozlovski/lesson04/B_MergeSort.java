package by.it.group251051.kozlovski.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Реализуйте сортировку слиянием для одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо отсортировать полученный массив.

Sample Input:
5
2 3 9 2 9
Sample Output:
2 2 3 9 9
*/
public class B_MergeSort {

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a=new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);
        }

        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием



        a = sortArray(a);


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }

    public int[] sortArray(int[] a){ // функция сортировки слиянием

        if(a == null){ //проверка есть ли содержимое массива
            return null;
        }

        if(a.length < 2){ // проверка на единственный элемент массива
            return a;
        }

        int[] b = new int[a.length/2];// создаем массив размером в половину от искомого
        for(int i = 0; i <a.length/2; i++){ // и записываем первую половину в него
            b[i] = a[i];
        }

        int[] c = new int[a.length -(a.length/2)]; //также создаем массив, содержащий уже вторую половину
        int i = 0;
        for(int j = a.length/2; j < a.length; j++ ){
            c[i] = a[j];
            i++;
        }

        b = sortArray(b);// такое же разделение проводим с получившимися массивами(рекурсия)
        c = sortArray(c);

        return merge(b,c); // возвращаем результат, полученный после функции слияния

    }

    public int[] merge(int[] b, int[] c){ // функция слияния
        int[] result = new int[b.length + c.length]; // результирующий массив

        int i = 0; //индексы для массивов
        int j = 0;
        int k = 0;

        while (b.length != i && c.length != j){ // пока не закончатся оба массива
            if(b[i] > c[j]){ // если i-тый элемент первого массива больше j-того элемента второго массива
                result[k] = c[j]; // записываем меньший элемент
                j++; //увеличиваем индексы второго и итогового массива
                k++;
            } else { //иначе наоборот
                result[k] = b[i];
                k++;
                i++;
            }
        }

         while (b.length != i){ //записываем оставшиеся элементы
            result[k] = b[i];
            i++;
            k++;
        }

        while(c.length != j){
            result[k] = c[j];
            j++;
            k++;
        }

        return result;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
