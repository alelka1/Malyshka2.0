package by.it.group251051.korneliuk.lesson01;

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
    //Решение сложно найти интуитивно
    //возможно потребуется дополнительный поиск информации
    //см. период Пизано
    long fasterC(long n, int m) {
        int[] arr = {0,1,0,0};
        for (int i = 0; i < m*m; i++) { // [0] - предыдущий, [1] - текущий, [2] - temp, [3] - результат
            arr[2] = arr[1];
            arr[1] = (arr[0] +  arr[1]) % m;
            // arr[0] = arr[2];
            if ((arr[0] = arr[2]) == 0 &&  arr[1] == 1)
                arr[3]= i + 1;
        }
        arr[1] = 1 + (arr [0] = 0);

        n %= arr[3];
        if (n == 0 || n == 1)
            return arr[(int)n];

        for (int i = 1; i < n; i++) {
            arr[2] = arr[1];
            arr[1] = (arr[0] +  arr[1]) % m;
            arr[0] = arr[2];
        }
        return arr[1] % m;
    }

}