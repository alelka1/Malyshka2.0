package by.it.group251051.pavlushchenko.lesson04;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class C_GetInversions {

    int calc(InputStream stream) {
        Scanner scanner = new Scanner(stream);

        int n = scanner.nextInt();
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        int[] temp = new int[n];
        return countInversions(a, temp, 0, n - 1);
    }

    private int countInversions(int[] a, int[] temp, int left, int right) {
        int invCount = 0;

        if (right > left) {
            int mid = (right + left) / 2;

            invCount += countInversions(a, temp, left, mid);
            invCount += countInversions(a, temp, mid + 1, right);
            invCount += mergeAndCount(a, temp, left, mid + 1, right);
        }

        return invCount;
    }

    private int mergeAndCount(int[] a, int[] temp, int left, int mid, int right) {
        int i = left, j = mid, k = left;
        int invCount = 0;

        while ((i <= mid - 1) && (j <= right)) {
            if (a[i] <= a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
                invCount += (mid - i);
            }
        }

        while (i <= mid - 1) {
            temp[k++] = a[i++];
        }

        while (j <= right) {
            temp[k++] = a[j++];
        }

        for (i = left; i <= right; i++) {
            a[i] = temp[i];
        }

        return invCount;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        int result = instance.calc(stream);
        System.out.print(result);
    }
}
