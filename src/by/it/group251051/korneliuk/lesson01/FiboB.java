package by.it.group251051.korneliuk.lesson01;

import java.math.BigInteger;
import java.util.ArrayList;

/*
 * Вам необходимо выполнить способ вычисления чисел Фибоначчи с вспомогательным массивом
 * без ограничений на размер результата (BigInteger)
 */

public class FiboB {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {

        //вычисление чисел простым быстрым методом
        FiboB fibo = new FiboB();
        int n = -7;
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
    }

    BigInteger fastB(Integer n) {
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
        ArrayList<BigInteger> arr = new ArrayList<BigInteger>();
        arr.add(BigInteger.valueOf(0));
        arr.add(BigInteger.valueOf(1));
        for (int i = 1; i < Math.abs(n); i++) {
            arr.add(BigInteger.valueOf(0));
            arr.set(2, arr.get(0).add(arr.get(1)));
            arr.remove(0);
        }
        if (n > 0) return arr.get(arr.size()-1);
        else {
            return n % 2 == 0 ? arr.get(arr.size()-1) : arr.get(arr.size()-1).negate();
        }
    }

}

