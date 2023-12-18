package by.it.group251051.Shiryavskaya.lesson01;

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

    long getPisanoPeriod(long m) {
        long first = 0;
        long second = 1;
        long period = 0;

        for (long i = 0; i < Math.pow(m, 2); i++) {
            long temp = 0;
            temp = second;
            second = (first + second) % m;
            first = temp;

            if (first == 0 && second == 1)
                period = i + 1;
        }
        return period;
    }

    long fasterC(long n, int m) {

        // Решение сложно найти интуитивно
        // возможно потребуется дополнительный поиск информации
        // см. период Пизано

        long numberPisanoPeriod = getPisanoPeriod(m);
        n %= numberPisanoPeriod;
        long prev = 0;
        long curr = 1;
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }

        for (int i = 0; i < n - 1; i++) {
            long buff = 0;
            buff = curr;
            curr = (prev + curr) % m;
            prev = buff;
        }
        return curr % m;

    }

}
