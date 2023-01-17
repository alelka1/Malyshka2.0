package by.it.group151051.Makovetskiy.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
Задача на программирование: рюкзак без повторов

Первая строка входа содержит целые числа
    1<=W<=100000     вместимость рюкзака
    1<=n<=300        число золотых слитков
                    (каждый можно использовать только один раз).
Следующая строка содержит n целых чисел, задающих веса каждого из слитков:
  0<=w[1]<=100000 ,..., 0<=w[n]<=100000

Найдите методами динамического программирования
максимальный вес золота, который можно унести в рюкзаке.

Sample Input:
10 3
1 4 8
Sample Output:
9

*/

public class B_Knapsack {

    int getMaxWeight(InputStream stream ) {
        Scanner scanner = new Scanner(stream);
        int size =scanner.nextInt();
        int goldCount =scanner.nextInt();
        ArrayList<Integer> goldList = new ArrayList<>();
        int[] gold = new int[goldCount];
        for (int i = 0; i < goldCount; i++) {
            gold[i]=scanner.nextInt();
        }
        for (int x : gold) {
            goldList.add(x);
        }
        Collections.sort(goldList);
        Collections.reverse(goldList);

        int currSize = 0;
        for (int i = 0; i < goldCount; i++) {
            if (currSize + goldList.get(i) <= size) {
                currSize += goldList.get(i);
            }
        }
        return currSize;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson08/dataB.txt");
        B_Knapsack instance = new B_Knapsack();
        int res=instance.getMaxWeight(stream);
        System.out.println(res);
    }

}
