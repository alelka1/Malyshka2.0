package by.it.group251052.hassan.lesson01;

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
        long n = 1000000000L;
        int m = 99999;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        if (m > 1000) { // нужно понять откуда взялось это ограничение
            m = 1000;
        }
        int max = m * m + 1;

        int[] rem = new int[max];
        rem[0] = 0;
        rem[1] = 1;
        int p = 0;

        if (m == 2) {
            p = 3;
            rem[2] = 1;
            return rem[(int) (n % p)];

        } else {
            p = 6;
            for (int i = 2; i < max; i++) {
                rem[i] = (rem[i - 2] + rem[i - 1]) % m;
            }

            for (int i = 0; i < max - p; i++) {
                if (rem[i] == rem[i + p]) {
                } else {
                    i = 0;
                    p += 2;
                }
            }
        }

        return rem[(int) (n % p)];
    }


}

