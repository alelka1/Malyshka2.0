package by.it.m_loika.lesson01;

import java.math.BigInteger;

/*
 * Вам необходимо выполнить рекурсивный способ вычисления чисел Фибоначчи
 */

public class FiboA {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboA fibo = new FiboA();
        int n = 33;
        System.out.printf("calc(%d)=%d \n\t time=%d \n\n", n, fibo.calc(n), fibo.time());

        //вычисление чисел фибоначчи медленным методом (рекурсией)
        fibo = new FiboA();
        BigInteger bigN = new BigInteger("33");
        System.out.printf("slowA(%d)=%d \n\t time=%d \n\n", bigN, fibo.slowA(bigN), fibo.time());
    }


    private int calc(int n) {
        //здесь простейший вариант, в котором код совпадает с мат.определением чисел Фибоначчи
        //время O(2^n)
        if (n < 2) return n;

        return calc(n - 1) + calc (n -2);
    }


    BigInteger slowA(BigInteger n) {
        //рекурсия
        //здесь нужно реализовать вариант без ограничения на размер числа,
        //в котором код совпадает с мат.определением чисел Фибоначчи
        //время O(2^n)

        if (n.equals(BigInteger.ZERO)) return BigInteger.ZERO;
        if (n.equals(BigInteger.ONE)) return BigInteger.ONE;

        return slowA(n.subtract(BigInteger.ONE)).add(slowA(n.subtract(new BigInteger("2"))));
    }
}
