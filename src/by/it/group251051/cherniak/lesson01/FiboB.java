package by.it.group251051.cherniak.lesson01;

import java.math.BigInteger;

/*
 * Вам необходимо выполнить способ вычисления чисел Фибоначчи с вспомогательным массивом
 * без ограничений на размер результата (BigInteger)
 */

public class FiboB {
    // Объявление класса с именем FiboB

    private long startTime = System.currentTimeMillis();
    // Создание приватной переменной startTime и инициализация её текущим значением
    // времени в миллисекундах

    private long time() {
        return System.currentTimeMillis() - startTime;
    }
    // Приватный метод time(), который возвращает разницу между текущим временем и
    // сохраненным временем startTime

    public static void main(String[] args) {
        // Главный метод

        // вычисление чисел простым быстрым методом
        FiboB fibo = new FiboB();
        // Создание объекта fibo класса FiboB

        int n = 55555;
        // Объявление переменной n и присвоение ей значения 55555

        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
        // Вывод форматированной строки, используя метод fastB(n) объекта fibo и метод
        // time() объекта fibo
    }

    BigInteger fastB(Integer n) {
        // Метод для вычисления числа Фибоначчи быстрым методом

        BigInteger[] fib = new BigInteger[n + 1];
        // Создание массива fib для хранения чисел Фибоначчи до значения n+1

        fib[0] = BigInteger.ZERO;
        fib[1] = BigInteger.ONE;
        // Инициализация базовых значений в массиве для чисел Фибоначчи

        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i - 1].add(fib[i - 2]);
            // Заполнение массива путем сложения двух предыдущих элементов массива
        }

        return fib[n];
        // Возвращение значения числа Фибоначчи
    }
}