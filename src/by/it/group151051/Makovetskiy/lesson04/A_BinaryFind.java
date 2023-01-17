package by.it.group151051.Makovetskiy.lesson04;

import java.io.*;
import java.util.ArrayList;

/*
В первой строке источника данных даны:
        - целое число 1<=n<=100000 (размер массива)
        - сам массив A[1...n] из n различных натуральных чисел,
          не превышающих 10E9, в порядке возрастания,
Во второй строке
        - целое число 1<=k<=10000 (сколько чисел нужно найти)
        - k натуральных чисел b1,...,bk не превышающих 10E9 (сами числа)
Для каждого i от 1 до kk необходимо вывести индекс 1<=j<=n,
для которого A[j]=bi, или -1, если такого j нет.

        Sample Input:
        5 1 5 8 12 13
        5 8 1 23 1 11

        Sample Output:
        3 1 -1 1 -1

(!) Обратите внимание на смещение начала индекса массивов JAVA относительно условий задачи
*/

public class A_BinaryFind {

    int[] findIndex(InputStream stream) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String first = reader.readLine();
        String second = reader.readLine();
        String[] tokensFirst = first.split(" ");
        String[] tokensSecond = second.split(" ");
        ArrayList<Integer> listFirst = new ArrayList<>();
        ArrayList<Integer> listSecond = new ArrayList<>();
        ArrayList<Integer> res = new ArrayList<>();

        for(int i = 1; i < tokensFirst.length; i++) {

                listFirst.add(Integer.parseInt(tokensFirst[i]));
                listSecond.add(Integer.parseInt(tokensSecond[i]));

       }

        for (Integer x : listSecond) {
            if (listFirst.contains(x)) res.add(listFirst.indexOf(x)+1);
            else res.add(-1);
        }

        int[] result = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            result[i] = res.get(i);
         }

        return result;
    }


    public static void main(String[] args) throws IOException {
        InputStream stream = new FileInputStream("src/by/it/group151051/makovetskiy/lesson04/dataA.txt");
        A_BinaryFind instance = new A_BinaryFind();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.findIndex(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }

    }

}
