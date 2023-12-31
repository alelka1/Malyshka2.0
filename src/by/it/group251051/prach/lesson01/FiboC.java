package by.it.group251051.prach.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.math.BigInteger;
import java.util.Stack;

public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 10;
        int m = 2;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        if (n <= 1) {
            return n;
        }

        long a = 0;
        long b = 1;
        long period = 0;
        long result = a + b;

        for (long i = 2; i <= n; i++) {
            result = (a + b) % m;
            a = b;
            b = result;

            // Проверяем, если начинается новый период Пизано
            if (a == 0 && b == 1) {
                period = i - 1;
                break;
            }
        }

        if (period > 0) {
            // Находим остаток от деления n на период Пизано
            n %= period;
            a = 0;
            b = 1;
            result = n;

            for (long i = 2; i <= n; i++) {
                result = (a + b) % m;
                a = b;
                b = result;
            }
        }

        return result;
    }
}

