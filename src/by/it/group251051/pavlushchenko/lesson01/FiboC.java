package by.it.group251051.pavlushchenko.lesson01;

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
        long a = 0, b = 1;
        long period = 0;
        for (int i = 0; i < m * m; i++) {
            long temp = (a + b) % m;
            a = b;
            b = temp;

            if (a == 0 && b == 1) {
                period = i + 1;
                break;
            }
        }
        long remainder = n % period;

        long fiboN = remainder == 0 ? 0 : 1;
        a = 0;
        b = 1;

        for (int i = 2; i <= remainder; i++) {
            fiboN = (a + b) % m;
            a = b;
            b = fiboN;
        }
        return fiboN;

    }

}

