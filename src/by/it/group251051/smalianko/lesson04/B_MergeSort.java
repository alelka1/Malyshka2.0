package by.it.group251051.smalianko.lesson04;

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


        a = mergesort(a);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }

    public int[] mergesort (int[] a){

        if(a.length == 1) {
            return a;
        }
        int length = a.length/2;
        int[] ArrayOne = new int[length];
        for(int i = 0; i < a.length/2; i++){
            ArrayOne[i] = a[i];
        }
        int[] ArrayTwo = new int[a.length - length];
        int index = 0;
        for(int i = length; i < a.length; i++) {
            ArrayTwo[index] = a[i];
            index++;
        }

        ArrayOne = mergesort(ArrayOne);
        ArrayTwo = mergesort(ArrayTwo);

        return merge(ArrayOne,ArrayTwo);
    }

    public int[] merge (int[] ArrayOne, int[] ArrayTwo){
        int[] result = new int[ArrayOne.length + ArrayTwo.length];

        int i = 0;
        int j = 0;
        int index = 0;
        while(ArrayOne.length != i && ArrayTwo.length != j){
            if(ArrayOne[i] > ArrayTwo[j]){
                result[index] = ArrayTwo[j];
                index++;
                j++;
            } else {
                result[index] = ArrayOne[i];
                index++;
                i++;
            }
        }

        while(ArrayOne.length != i) {
            result[index] = ArrayOne[i];
            index++;
            i++;
        }

        while(ArrayTwo.length != j){
            result[index] = ArrayTwo[j];
            index++;
            j++;
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
