package by.it.group251051.kozlovski.lesson07;

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

        int[][] matr = new int[one.length()+1][two.length()+1]; // создаем матрицу в которую будем записывать шаги

        for(int i = 0; i <= one.length(); i++){
            for (int j =0; j<= two.length(); j++){
                if(i == 0){
                    matr[i][j] = j;

                } else if (j == 0) {
                    matr[i][j] = i;

                } else {
                    matr[i][j] = Integer.min(matr[i-1][j-1] + Cost_of_sub(one.charAt(i-1),two.charAt(j-1)),
                            Integer.min(matr[i-1][j]+1,matr[i][j-1]+1));
                    // выбираем действие, которое затрачивает наименьшее кол-во шагов
                }
            }
        }

        int i =  one.length();
        int j = two.length();

        StringBuilder result = new StringBuilder(); // создаем строку в которую будем записывать результат

        while (i != 0 || j  != 0 ){ // пока не закончиться матрица
            if (i > 0 && j > 0 && matr[i -1][j-1] + (one.charAt(i-1) == two.charAt(j-1) ? 0:1) == matr[i][j]){
                // и индексы стоят на ячейке с наименьшим шагом
                if (one.charAt(i-1) == two.charAt(j -1)){ // если символы соответствуют друг другу
                    result.insert(0, "#,"); // это копирование

                } else {
                    result.insert(0,"~" + two.charAt(j-1)+ ","); // иначе замена

                }
                i--;
                j--;

            } else if (j>0 && matr[i][j] == matr[i][j-1]+1) { // иначе если число в ячейке матрицы увеличилось
                result.insert(0,"+" + two.charAt(j-1)+ ","); // значит это вставка + символ
                j--;

            } else if (i >  0 && matr[i][j] == matr[i - 1][j]+1) { // иначе если число в ячейке матрицы уменьшилось
                result.insert(0,"-" + one.charAt(i -1) + ","); // значит это удаление - символ
                i--;
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result.toString(); // возвращаем строку с обозначениями каждого шага
    }

    public static int Cost_of_sub(char a, char b){  //цена замены одного символа на другой
        return a==b ? 0 : 1;
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