package by.it.group251051.Malyshka.lesson01;

import java.math.BigInteger;

public class FiboB {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {

        FiboB fibo = new FiboB();
        int n = 55555;
        System.out.printf("fastB(%d)=%s \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
    }

    BigInteger fastB(int n) {
        if (n <= 0) {
            return BigInteger.ZERO;
        } else if (n == 1) {
            return BigInteger.ONE;
        }

        BigInteger[] fibArray = new BigInteger[n + 1];
        fibArray[0] = BigInteger.ZERO;
        fibArray[1] = BigInteger.ONE;

        for (int i = 2; i <= n; i++) {
            fibArray[i] = fibArray[i - 1].add(fibArray[i - 2]);
        }

        return fibArray[n];
    }
}



