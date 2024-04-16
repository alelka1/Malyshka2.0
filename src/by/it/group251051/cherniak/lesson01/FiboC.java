package by.it.group251051.cherniak.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */
public class FiboC {
    // Объявление класса с именем FiboC

    private long startTime = System.currentTimeMillis();
    // Создание приватной переменной startTime и инициализация её текущим значением времени в миллисекундах

    private long time() {
        return System.currentTimeMillis() - startTime;
    }
    // Приватный метод time(), который возвращает разницу между текущим временем и сохраненным временем startTime

    public static void main(String[] args) {
        // Главный метод

        FiboC fibo = new FiboC();
        // Создание объекта fibo класса FiboC

        int n = 10;
        int m = 2;
        // Объявление переменных n и m и присвоение им значений 10 и 2 соответственно

        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
        // Вывод форматированной строки, используя метод fasterC(n, m) объекта fibo и метод time() объекта fibo
    }

    long getPisanoPeriod(long m) {
        // Метод для вычисления периода Пизано

        long first = 0;
        long second = 1;
        long period = 0;
        // Инициализация переменных для вычисления периода Пизано

        for (long i = 0; i < Math.pow(m, 2); i++) {
            // Цикл для вычисления последовательности Фибоначчи и поиска периода Пизано

            long temp = 0;
            temp = second;
            second = (first + second) % m;
            first = temp;
            // Вычисление следующего значения в последовательности Фибоначчи

            if (first == 0 && second == 1)
                period = i + 1;
            // Проверка на завершение периода Пизано
        }
        return period;
        // Возвращение найденного периода Пизано
    }

    long fasterC(long n, int m) {
        // Метод для вычисления значения числа Фибоначчи быстрым методом с учетом периода Пизано

        long numberPisanoPeriod = getPisanoPeriod(m);
        // Получение периода Пизано для числа m

        n %= numberPisanoPeriod;
        // Вычисление остатка от деления n на период Пизано

        long prev = 0;
        long curr = 1;
        // Инициализация начальных значений для последовательности Фибоначчи

        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        // Проверка для n=0 и n=1

        for (int i = 0; i < n - 1; i++) {
            long buff = 0;
            buff = curr;
            curr = (prev + curr) % m;
            prev = buff;
            // Цикл для вычисления значения числа Фибоначчи с учетом периода Пизано
        }
        return curr % m;
        // Возвращение значения числа Фибоначчи
    }
}
