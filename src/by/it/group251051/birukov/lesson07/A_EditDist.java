package by.it.group251051.birukov.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Рекурсивно вычислить расстояние редактирования двух данных непустых строк

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    0

    Sample Input 2:
    short
    ports
    Sample Output 2:
    3

    Sample Input 3:
    distance
    editing
    Sample Output 3:
    5

*/

public class A_EditDist {


    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        int m = one.length();
        int n = two.length();
        int[][] T = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) T[i][0] = i;
        for (int j = 0; j < n; j++) T[0][j] = j;
        int i = 1, j = 1;

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return Result(one, two, i, j, T, m, n);
    }

    int Result(String one, String two, int i, int j, int[][] T, int m, int n) {
        if (i <= m) {
            if (j <= n) {
                int c;
                if (one.charAt(i - 1) == two.charAt(j - 1))
                    c = 0;
                else
                    c = 1;
                if (T[i - 1][j] + 1 < T[i][j - 1] + 1)
                    T[i][j] = T[i - 1][j] + 1;
                else
                    T[i][j] = T[i][j - 1] + 1;
                if (T[i - 1][j - 1] + c < T[i][j])
                    T[i][j] = T[i - 1][j - 1] + c;
                j++;
            } else {
                i++;
                j = 1;
            }
        } else return T[m][n];
        return Result(one, two, i, j, T, m, n);
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}
