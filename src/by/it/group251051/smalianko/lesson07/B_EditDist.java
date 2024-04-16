package by.it.group251051.smalianko.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
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

//    int getMin(int a, int b, int c) {
//        return Integer.min(a, Integer.min(b, c));
//    }


    int getDistanceEdinting(String one, String two) {
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



//        for(int i = 0; i < onelength + 1; i++) {
//            for(int j = 0; j < twolength + 1; j++){
//                System.out.print(matr[i][j] + " ");
//            }
//            System.out.println();
//        }

        int result = matr[onelength][twolength];
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
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