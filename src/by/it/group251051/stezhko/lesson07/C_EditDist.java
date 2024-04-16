package by.it.group251051.stezhko.lesson07;

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
        String result = "";
        int o = one.length();
        int t = two.length();
        int i, j;
        int[][] matrix = new int[o + 1][t + 1];
        int[][] opr = new int [o + 1][t + 1];
        for(i = 0; i < o + 1; i++) {
            for(j = 0; j < t + 1; j++) {
                matrix[i][j] = 0;
            }
        }
        for(i = 1; i < o + 1; i++) {
            matrix[i][0] = i;
        }
        for(i = 1; i < t + 1; i++) {
            matrix[0][i] = i;
        }
        for(j = 1; j < t + 1; j++) {
            for (i = 1; i < o + 1; i++) {
                int v = 1;
                if (one.charAt(i - 1) == two.charAt(j - 1)) {
                    v = 0;
                }
                int r = matrix[i - 1][j] + 1;
                int ins = matrix[i][j - 1] + 1;
                int d = matrix[i - 1][j - 1] + v;
                matrix[i][j] = Math.min(Math.min(r, ins), d);
                if (v == 0) {
                    opr[i][j] = 2;
                } else if (matrix[i][j] == r) {
                    opr[i][j] = 3;
                } else if (matrix[i][j] == ins) {
                    opr[i][j] = 0;
                } else if (matrix[i][j] == d) {
                    opr[i][j] = 1;
                }
            }
        }
        for(i = o, j = t; i != 0 || j != 0; ) {
            switch(opr[i][j]) {
                case 0: result = "+" + two.charAt(j - 1) + "," + result; j--; break;
                case 1: result = "-" + one.charAt(i - 1) + "," + result; i--; j--; break;
                case 2: result = "#," + result; i--; j--; break;
                case 3: result = "~" + two.charAt(j - 1) + "," + result; i--; break;
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
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