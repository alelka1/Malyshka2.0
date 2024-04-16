package by.it.group251051.cherniak.lesson07;

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
    // Класс для вычисления расстояния редактирования

    int getDistanceEdinting(String one, String two) {
        // Метод для получения расстояния редактирования между двумя строками
        int result = editDistanceRecursive(one, two, one.length(), two.length());
        // Получаем результат вызовом рекурсивной функции editDistanceRecursive
        return result;
        // Возвращаем результат
    }

    int editDistanceRecursive(String str1, String str2, int m, int n) {
        // Рекурсивная функция для вычисления расстояния редактирования

        if (m == 0) {
            return n;
        }
        // Если одна из строк пустая, возвращаем длину другой строки

        if (n == 0) {
            return m;
        }
        // Если одна из строк пустая, возвращаем длину другой строки

        if (str1.charAt(m - 1) == str2.charAt(n - 1)) {
            return editDistanceRecursive(str1, str2, m - 1, n - 1);
        }
        // Если последние символы совпадают, просто игнорируем их и рекурсивно вызываем
        // для оставшихся строк

        return 1 + min(
                editDistanceRecursive(str1, str2, m, n - 1), // Вставка
                editDistanceRecursive(str1, str2, m - 1, n), // Удаление
                editDistanceRecursive(str1, str2, m - 1, n - 1) // Замена
        );
        // Иначе, выбираем минимум из трех операций и добавляем 1
    }

    int min(int x, int y, int z) {
        // Метод для вычисления минимума из трех чисел
        return Math.min(Math.min(x, y), z);
        // Возвращаем минимум из трех чисел
    }

    public static void main(String[] args) throws FileNotFoundException {
        // Главный метод

        String root = System.getProperty("user.dir") + "/src/";
        // Получаем путь к текущей директории и добавляем относительный путь к файлу

        InputStream stream = new FileInputStream(root + "by/it/group251051/cherniak/lesson07/dataABC.txt");
        // Создаем файл из полученного пути

        A_EditDist instance = new A_EditDist();
        // Создаем объект instance класса A_EditDist

        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        // Выводим расстояние редактирования для нескольких пар строк из файла
    }
}
