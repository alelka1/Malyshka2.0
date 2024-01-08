package by.it.group251051.Malyshka.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class B_Knapsack {

    int getMaxWeight(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        int W = scanner.nextInt(); // вместимость рюкзака
        int n = scanner.nextInt(); // количество золотых слитков
        int[] gold = new int[n]; // массив весов слитков

        for (int i = 0; i < n; i++) {
            gold[i] = scanner.nextInt(); // вес слитка
        }

        // Массив для хранения максимального веса для каждой вместимости
        int[][] maxWeight = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= W; j++) {
                maxWeight[i][j] = maxWeight[i - 1][j]; // не берем текущий слиток

                if (gold[i - 1] <= j) {
                    int val = maxWeight[i - 1][j - gold[i - 1]] + gold[i - 1];
                    maxWeight[i][j] = Math.max(maxWeight[i][j], val); // берем текущий слиток
                }
            }
        }

        return maxWeight[n][W];
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson08/dataB.txt");
        B_Knapsack instance = new B_Knapsack();
        int res = instance.getMaxWeight(stream);
        System.out.println(res);
    }
}
