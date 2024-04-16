package by.it.group251052.ramazanov.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

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
        // Проверка на случай, когда n <= 1, так как для таких значений остаток от деления всегда равен n % m
        if (n <= 1) {
            return n % m;
        }

        long previous = 0,current = 1,period = 0; // Инициализация предыдущего числа и текущего Фибоначчи,а также Пизано

        // Вычисление последовательности чисел Фибоначчи и поиск периода Пизано
        for (long i = 2; i <= n; i++) {
            // Рассчет следующего числа Фибоначчи по модулю m
            long temp = (previous + current) % m;
            previous = current;
            current = temp;

            // Проверка на период Пизано (когда предыдущее и текущее числа равны 0 и 1)
            if (previous == 0 && current == 1) {
                // Найден период Пизано, завершаем вычисления
                period = i - 1;
                break;
            }
        }

        // Остаток от деления n на длину периода Пизано
        long remainder = n % period;

        // Вычисление F(n % k) для искомого остатка
        previous = 0; // Инициализация предыдущего числа Фибоначчи
        current = 1;  // Инициализация текущего числа Фибоначчи
        for (long i = 2; i <= remainder; i++) {
            // Рассчет следующего числа Фибоначчи по модулю m
            long temp = (previous + current) % m;
            previous = current;
            current = temp;
        }

        return current; // Возвращение остатка от деления n-го числа Фибоначчи на m
    }



}

