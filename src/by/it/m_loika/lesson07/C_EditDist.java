package by.it.m_loika.lesson07;

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
    Итерационно вычислить алгоритм преобразования двух данных непустых строк
    Вывести через запятую редакционное предписание в формате:
     операция("+" вставка, "-" удаление, "~" замена, "#" копирование)
     символ замены или вставки

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    #,#,

    Sample Input 2:
    short
    ports
    Sample Output 2:
    -s,~p,#,#,#,+s,

    Sample Input 3:
    distance
    editing
    Sample Output 2:
    +e,#,#,-s,#,~i,#,-c,~g,


    P.S. В литературе обычно действия редакционных предписаний обозначаются так:
    - D (англ. delete) — удалить,
    + I (англ. insert) — вставить,
    ~ R (replace) — заменить,
    # M (match) — совпадение.
*/


public class C_EditDist {

    String getDistanceEdinting(String one, String two) {
        int m = one.length();
        int n = two.length();

        // Initialize the dp array
        int[][] dp = new int[m + 1][n + 1];
        StringBuilder[][] operations = new StringBuilder[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
            operations[i][0] = new StringBuilder();
            for (int j = 0; j < i; j++) {
                operations[i][0].append("-").append(one.charAt(j));
            }
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
            operations[0][j] = new StringBuilder();
            for (int k = 0; k < j; k++) {
                operations[0][j].append("+").append(two.charAt(k));
            }
        }

        // Fill the dp array
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (one.charAt(i - 1) == two.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                    operations[i][j] = new StringBuilder(operations[i - 1][j - 1]).append("#,");
                } else {
                    int minCost = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
                    dp[i][j] = 1 + minCost;
                    if (dp[i - 1][j - 1] == minCost) {
                        operations[i][j] = new StringBuilder(operations[i - 1][j - 1]).append("~").append(two.charAt(j - 1));
                    } else if (dp[i - 1][j] == minCost) {
                        operations[i][j] = new StringBuilder(operations[i - 1][j]).append("-").append(one.charAt(i - 1));
                    } else {
                        operations[i][j] = new StringBuilder(operations[i][j - 1]).append("+").append(two.charAt(j - 1));
                    }
                }
            }
        }

        return operations[m][n].toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/m_loika/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }
}

