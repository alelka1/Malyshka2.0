package by.it.group251051.birukov.lesson01;

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

    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано

        if (n<2) return n;
        else {
            long f, f1=1, f2=1, mod;
            long[] mas_mod = new long[93];
            mas_mod[1] = 1;
            mas_mod[2] = 1;
            int j=0, P_m=3, p=0, b=0, pos_mod;
            for (int i = 3; i <= 92 && P_m != p; i++) {
                f = f2 + f1;
                f1 = f2;
                f2 = f;
                mas_mod[i]=f-f/m*m;
                if (mas_mod[j] == mas_mod[i]) {
                    if (b == 0) b = 1;
                    p++;
                    j++;
                }
                else {
                    if (b == 1) {
                        P_m += p;
                        p = 0;
                        j = 0;
                    }
                    P_m++;
                }
            }
            pos_mod = (int) (n-n/P_m*P_m);
            mod = mas_mod[pos_mod];
            return mod;
        }
    }
}

