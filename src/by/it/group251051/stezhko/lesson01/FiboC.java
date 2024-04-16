package by.it.group251051.stezhko.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.math.BigInteger;

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

    int periodPisano(int m)  {
        if (m == 1) return 1;
        int prev = 1;
        int curr = 1;
        int res = 1;
        while(!(prev == 0 && curr == 1)) {
            int t = (prev + curr) % m;
            prev = curr;
            curr = t;
            res += 1;
        }
        return res;
    }
    BigInteger fibonacci(int n) {
        if (n == 0) return BigInteger.ZERO;
        if (n == 1) return BigInteger.ONE;
        BigInteger array [];
        array = new BigInteger[n + 1];
        array[0] = BigInteger.ZERO;
        array[1] = BigInteger.ONE;
        int currentN = 2;
        while(currentN <= n) {
            array[currentN] = array[currentN - 1].add(array[currentN - 2]);
            currentN++;
        }
        return array[n];
    }
    long fasterC(long n, long m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        if (n < 2) return n;
        long period = periodPisano(Math.toIntExact(m));
        int newN = Math.toIntExact(n % period);
        return fibonacci(newN).mod(BigInteger.valueOf(m)).longValue();
    }
}

