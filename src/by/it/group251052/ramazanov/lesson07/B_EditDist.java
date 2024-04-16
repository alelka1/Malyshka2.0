package by.it.group251052.ramazanov.lesson07;

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
    Итерационно вычислить расстояние редактирования двух данных непустых строк

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

public class B_EditDist {



    int getDistanceEdinting(String firstString, String secondString)
    {
        int[][] distanceMatrix = new int[2][secondString.length() + 1];
        distanceMatrix[0][0] = 0;

        for (int i = 0; i <= firstString.length(); ++i) {
            for (int j = 0; j <= secondString.length(); ++j) {
                if (j == 0 && i > 0) {
                    distanceMatrix[1][0] = i;
                } else if (i == 0) {
                    distanceMatrix[0][j] = j;
                } else {
                    int substitutionCost = distanceMatrix[0][j - 1] + (firstString.charAt(i - 1) == secondString.charAt(j - 1) ? 0 : 1);
                    distanceMatrix[1][j] = Math.min(
                            Math.min(distanceMatrix[1][j - 1] + 1, distanceMatrix[0][j] + 1),
                            substitutionCost
                    );
                }
            }

            if (i > 0) {
                System.arraycopy(distanceMatrix[1], 0, distanceMatrix[0], 0, secondString.length() + 1);
            }
        }

        return distanceMatrix[1][secondString.length()];
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        B_EditDist instance = new B_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}