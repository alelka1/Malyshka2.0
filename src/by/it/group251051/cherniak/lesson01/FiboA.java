package by.it.group251051.cherniak.lesson01;

import java.math.BigInteger;

/*
 * Вам необходимо выполнить рекурсивный способ вычисления чисел Фибоначчи
 */
public class FiboA {
    // Объявление класса с именем FiboA

    private long startTime = System.currentTimeMillis();
    // Создание приватной переменной startTime и инициализация её текущим значением
    // времени в миллисекундах

    private long time() {
        return System.currentTimeMillis() - startTime;
    }
    // Приватный метод time(), который возвращает разницу между текущим временем и
    // сохраненным временем startTime

    public static void main(String[] args) {
        FiboA fibo = new FiboA();
        // Создание объекта fibo класса FiboA

        int n = 33;
        // Объявление переменной n и присвоение ей значения 33

        System.out.printf("calc(%d)=%d \n\t time=%d \n\n", n, fibo.calc(n), fibo.time());
        // Вывод форматированной строки, используя метод calc(n) объекта fibo и метод
        // time() объекта fibo

        fibo = new FiboA();
        // Пересоздание объекта fibo класса FiboA

        n = 33;
        // Присвоение переменной n нового значения 33

        System.out.printf("slowA(%d)=%d \n\t time=%d \n\n", n, fibo.slowA(n), fibo.time());
        // Вывод форматированной строки, используя метод slowA(n) объекта fibo и метод
        // time() объекта fibo
    }

    private int calc(int n) {
        if (n < 2)
            return n;
        // Если n меньше 2, вернуть n

        return calc(n - 1) + calc(n - 2);
        // Рекурсивный вызов метода calc для n-1 и n-2, и возврат их суммы
    }

    BigInteger slowA(Integer n) {
        if (n == 0)
            return BigInteger.ZERO;
        // Если n равно 0, вернуть BigInteger.ZERO

        if (n == 1)
            return BigInteger.ONE;
        // Если n равно 1, вернуть BigInteger.ONE

        return slowA(n - 1).add(slowA(n - 2));
        // Рекурсивный вызов метода slowA для n-1 и n-2, а затем сложение их результатов
    }
}