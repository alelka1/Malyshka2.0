package by.it.group251051.Kallaur.lesson07;

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
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        int s1 = one.length();
        int s2 = two.length();

        int[][] matrix = new int[s1 + 1][s2 + 1];

        for(int j = 0; j < s1 + 1; j++)
            matrix[j][0] = j;

        for (int i = 0; i < s2 +1; i++)
            matrix[0][i] = i;

        for(int i = 1; i <= s1; i++) {
            for(int j = 1; j <= s2; j++) {
                int temp = check(one, two, i, j);
                matrix[i][j] = Math.min(matrix[i-1][j-1] + temp, Math.min(matrix[i-1][j] + temp, matrix[i][j-1] + temp));
            }
        }

        StringBuilder result = new StringBuilder();

        for (int i = s1, j = s2; i != 0 || j != 0; ){
            if (i > 0 && j > 0 && matrix[i - 1][j - 1] + check(one, two, i, j) == matrix[i][j]) {
                if(one.charAt(i - 1) == two.charAt(j - 1))
                    result.insert(0, "#,");
                else
                    result.insert(0, "~" + two.charAt(j - 1) + ",");
                i--;
                j--;
            }
            else if (i > 0 && matrix[i][j] == matrix[i - 1][j] + 1)
                result.insert(0, "-" + one.charAt(--i) + ",");
            else if (j > 0 && matrix[i][j] == matrix[i][j - 1] + 1)
                result.insert(0, "+" + two.charAt(--j) + ",");
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result.toString();
    }
    int check(String one, String two, int i, int j) {
        return one.charAt(i - 1) == two.charAt(j - 1) ? 0 : 1;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}