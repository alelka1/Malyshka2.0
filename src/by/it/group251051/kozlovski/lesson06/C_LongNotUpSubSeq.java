package by.it.group251051.kozlovski.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
Задача на программирование: наибольшая невозростающая подпоследовательность

Дано:
    целое число 1<=n<=1E5 ( ОБРАТИТЕ ВНИМАНИЕ НА РАЗМЕРНОСТЬ! )
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    для которой каждый элемент A[i[k]] не больше любого предыдущего
    т.е. для всех 1<=j<k, A[i[j]]>=A[i[j+1]].

    В первой строке выведите её длину k,
    во второй - её индексы i[1]<i[2]<…<i[k]
    соблюдая A[i[1]]>=A[i[2]]>= ... >=A[i[n]].

    (индекс начинается с 1)

Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ

    Sample Input:
    5
    5 3 4 4 2

    Sample Output:
    4
    1 3 4 5
*/


public class C_LongNotUpSubSeq {

    static int[] Result_array;

    int getNotUpSeqSize(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //общая длина последовательности
        int n = scanner.nextInt();
        int[] m = new int[n];
        //читаем всю последовательность
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
        }
        //тут реализуйте логику задачи методами динамического программирования (!!!)
        int[] array = new int[n];
        // создаем новый массив, где будет происходить подсчет кол-ва элементов наибольшей последовательности(динамики)
        Arrays.fill(array,1); // заполним его единицами т.к. размер наименьшей последовательности

        int[] max_index = new int[n];
        // создаем массив в котором будет храниться в каком месте достигается максимум для каждого значения в array
        Arrays.fill(max_index, -1);

        for (int i = 1; i<n; i++){ //цикл
            for(int j = 0; j < i; j++){
                if(m[j] >= m[i] &&  array[j] + 1 > array[i]){
                    // если I-тое число меньше либо равно j-того и j-тая последовательность +1 больше i-той
                    array[i] = array[j] +1;// увеличиваем последовательность
                    max_index[i] = j; // записываем, где она увеличилась;
                }
            }
        }
        int result = 0;
        int max = 0;

        for (int i = 0; i<n; i++){
            if(result < array[i]){ // находим наибольшую последовательность
                result = array[i];
                max = i; //запоминаем индекс максимальной последовательности
            }
        }

        Result_array = new int[result]; //массив самой последовательности
        int j = result-1;

            while(max >-1){ //записываем в массив
                Result_array[j] = max+1;
                max = max_index[max];
                j--;
            }


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson06/dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result = instance.getNotUpSeqSize(stream);
        System.out.println(result);
        for(int i = 0; i < Result_array.length; i++){
            System.out.print(Result_array[i] + " ");
        }
    }

}