package by.it.group151051.Makovetskiy.lesson01;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * Вам необходимо выполнить способ вычисления чисел Фибоначчи с вспомогательным массивом
 * без ограничений на размер результата (BigInteger)
 */

public class FiboB {

    private long startTime = System.currentTimeMillis();
    static BigInteger[] cache = new BigInteger[60000];

    static {
        cache[0] = BigInteger.ZERO;
        cache[1] = BigInteger.ONE;
    }


    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {

        //вычисление чисел простым быстрым методом

        FiboB fibo = new FiboB();

        int n = 50;
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
    }

    BigInteger fastB(Integer n) {
        if (cache[n] != null) return cache[n];
        cache[n] = fastB(n-1).add(fastB(n-2));
        return cache[n];
    }

}

