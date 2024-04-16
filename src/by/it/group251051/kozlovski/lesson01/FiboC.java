package by.it.group251051.kozlovski.lesson01;

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

    public static long pisano(long m){ // функция нахождения периода Пизано

        long prev = 0;
        long curr = 1;
        long leng = 0;

        for(int i = 0; i<m*m; i++){

            long temp = 0;
            temp = curr;
            curr = (prev+curr) % m;
            prev = temp;

            if(prev == 0 && curr==1){
                leng = i+1;
            }
        }


        return leng;
    }

    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано

        long pisano_period = pisano(m);

        n= n % pisano_period;

        long prev = 0;
        long curr = 1;

        if(n==0) return 0;
        if(n==1) return 1;

        for(int i = 0; i<m*m; i++){

            long temp = 0;
            temp = curr;
            curr = (prev+curr) % m;
            prev = temp;

        }

        return curr % m;
    }



}

