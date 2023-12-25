package by.it.group251051.Shiryavskaya.lesson07;

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
        int result = editDistanceRecursive(one, two, one.length(), two.length());
        return result;
    }

    int editDistanceRecursive(String str1, String str2, int m, int n) {
        if (m == 0) {
            return n;
        }

        if (n == 0) {
            return m;
        }

        if (str1.charAt(m - 1) == str2.charAt(n - 1)) {
            return editDistanceRecursive(str1, str2, m - 1, n - 1);
        }

        return 1 + min(
                editDistanceRecursive(str1, str2, m, n - 1), // Вставка
                editDistanceRecursive(str1, str2, m - 1, n), // Удаление
                editDistanceRecursive(str1, str2, m - 1, n - 1) // Замена
        );
    }

    int min(int x, int y, int z) {
        return Math.min(Math.min(x, y), z);
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group251051/Shiryavskaya/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }
}
