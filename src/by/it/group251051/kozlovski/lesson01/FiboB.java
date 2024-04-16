package by.it.group251051.kozlovski.lesson01;

import java.math.BigInteger;
import java.util.ArrayList;

/*
 * Вам необходимо выполнить способ вычисления чисел Фибоначчи с вспомогательным массивом
 * без ограничений на размер результата (BigInteger)
 */

public class FiboB {

    private long startTime = System.currentTimeMillis();
    private static  ArrayList<BigInteger> arr = new ArrayList<>();
    // создаем список для хранения элементов типа BigInteger

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

        if(n==0) return BigInteger.ZERO;

        arr.add(BigInteger.ONE);
        arr.add(BigInteger.ONE);// добавляем в список два первых элемента равных единице

        for (int i= 1; i < n; i++ ){
            arr.set(i%2,arr.get(0).add(arr.get(1)));
            // Используя остаток от деления, понимаем какой элемент "устаревает" и на его место ставим число Фибоначчи
        }

            return arr.get(n%2);
    }

}

