package by.it.group251051.pavlushchenko.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class C_Stairs {

    int getMaxSum(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] stairs = new int[n];
        for (int i = 0; i < n; i++) {
            stairs[i] = scanner.nextInt();
        }

        // Массив для хранения максимальной суммы для каждой ступеньки
        int[] maxSum = new int[n];

        if (n >= 1) {
            maxSum[0] = stairs[0];
        }

        if (n >= 2) {
            maxSum[1] = Math.max(stairs[0] + stairs[1], stairs[1]);
        }

        for (int i = 2; i < n; i++) {
            maxSum[i] = Math.max(maxSum[i - 1] + stairs[i], maxSum[i - 2] + stairs[i]);
        }

        return maxSum[n - 1];
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson08/dataC.txt");
        C_Stairs instance = new C_Stairs();
        int res = instance.getMaxSum(stream);
        System.out.println(res);
    }
}