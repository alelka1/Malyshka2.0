package by.it.group251051.smalianko.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
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
        int onelength = one.length();
        int twolength = two.length();

        int[][] matr = new int[onelength + 1][twolength + 1];

        matr[0][0] = 0;


        for(int j = 1; j < onelength + 1; j++) {
            matr[j][0] = j;
        }
        for (int i = 1; i < twolength +1; i++) {
            matr[0][i] = i;
        }

        for(int i = 1; i <= onelength; i++) {
            for(int j = 1; j <= twolength; j++) {
                int tmp = (one.charAt(i - 1 ) == two.charAt(j - 1)) ? 0 : 1;
                matr[i][j] = Math.min((matr[i-1][j-1] + tmp), Math.min((matr[i][j-1] + tmp), (matr[i-1][j] + tmp)));
            }
        }

        StringBuilder result = new StringBuilder();

        int i = onelength;
        int j = twolength;


//        while (i > 0 && j > 0) {
//            int diagonal = matr[i - 1][j - 1];
//            int vertical = matr[i - 1][j];
//            int horizontal = matr[i][j - 1];
//            int current = matr[i][j];
//            if (diagonal <= Math.min(horizontal, vertical) && ((diagonal == current) || (diagonal == current-1))){
//                i--;
//                j--;
//                if(diagonal < current){
//                    result.insert(0,"~" + two.charAt(j) + " ");
//                } else {
//                    result.insert(0, "# ");
//                }
//            } else if (horizontal <= vertical && ((horizontal == current) || (horizontal == current-1))){
//                j--;
//                result.insert(0,"+" + two.charAt(j) + " ");
//            } else {
//                i--;
//                result.insert(0,"-" + one.charAt(i) + " ");;
//            }
//        }


        while (i != 0 || j != 0){
            if (i > 0 && j > 0 && matr[i - 1][j - 1] + (one.charAt(i - 1) == two.charAt(j - 1) ? 0 : 1) == matr[i][j]){
                if(one.charAt(i - 1) == two.charAt(j - 1)){
                    result.insert(0, "#,");
                }else {
                    result.insert(0, "~" + two.charAt(j - 1) + ",");
                }
                i--;
                j--;
            } else if (j > 0 && matr[i][j] == matr[i][j - 1] + 1) {
                result.insert(0, "+" + two.charAt(j - 1) + ",");
                j--;
            } else if ( i > 0 && matr[i][j] == matr[i - 1][j] + 1) {
                result.insert(0, "-" + one.charAt(i - 1) + ",");
                i--;
            }
        }

        return result.toString();





















        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //return result.toString();
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