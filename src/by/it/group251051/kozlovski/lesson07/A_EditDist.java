package by.it.group251051.kozlovski.lesson07;

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


        int result = Calculate(one,two);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    static int Calculate( String one, String two){ // функция расчета расстояния Левенштейна

        if(one.isEmpty()){//если одна из строк пуста, то расстояние равно длине не пустой строчки
            return two.length();
        }

        if(two.isEmpty()){
            return one.length();
        }
        //три возможные опции изменения одного символа в другой
        int sub = Calculate(one.substring(1),two.substring(1))
                + Cost_of_sub(one.charAt(0), two.charAt(0));
        int ins = Calculate(one,two.substring(1)) + 1;
        int del = Calculate(one.substring(1),two) + 1;
        return Min(sub,ins,del);
    }

    public static int Cost_of_sub(char a, char b){ //цена замены одного символа на другой
        return a==b ? 0 : 1;
    }

    public static int Min(int a, int b, int c){ //выберем опцию, которая дает минимальное количество затраченных шагов
        return Integer.min(a, Integer.min(b,c));
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
