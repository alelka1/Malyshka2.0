package by.it.group251051.smalianko.lesson01;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * Вам необходимо выполнить способ вычисления чисел Фибоначчи с вспомогательным массивом
 * без ограничений на размер результата (BigInteger)
 */

public class FiboB {

    //private static ArrayList<BigInteger> cash = new ArrayList<>(Arrays.asList(BigInteger.valueOf(0),BigInteger.valueOf(1)));
    private static ArrayList<BigInteger> cash = new ArrayList<>();
    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {

        //вычисление чисел простым быстрым методом
        FiboB fibo = new FiboB();
        int n = 55555;

        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());

    }

    BigInteger fastB(Integer n) {
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)

//        BigInteger f[] = new BigInteger[n+2];
//        int i;
//        f[0] = BigInteger.ZERO;
//        f[1] = BigInteger.ONE;
//        for(i = 2; i<=n; i++){
//            f[i] = f[i-1].add(f[i-2]);
//        }
//        return f[n];

        cash.add(BigInteger.ZERO);
        cash.add(BigInteger.ONE);
        for (int i = 0; i < n-1; i++){
            cash.add(BigInteger.ZERO);
            cash.set(2,cash.get(0).add(cash.get(1)));
            cash.remove(0);
        }
        return cash.get(cash.size()-1);


    }

}

