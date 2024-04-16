package by.it.group251051.cherniak.lesson03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Lesson 3. B_Huffman.
// Восстановите строку по её коду и беспрефиксному коду символов.

// В первой строке входного файла заданы два целых числа
// kk и ll через пробел — количество различных букв, встречающихся в строке,
// и размер получившейся закодированной строки, соответственно.
//
// В следующих kk строках записаны коды букв в формате "letter: code".
// Ни один код не является префиксом другого.
// Буквы могут быть перечислены в любом порядке.
// В качестве букв могут встречаться лишь строчные буквы латинского алфавита;
// каждая из этих букв встречается в строке хотя бы один раз.
// Наконец, в последней строке записана закодированная строка.
// Исходная строка и коды всех букв непусты.
// Заданный код таков, что закодированная строка имеет минимальный возможный размер.
//
//        Sample Input 1:
//        1 1
//        a: 0
//        0

//        Sample Output 1:
//        a

//        Sample Input 2:
//        4 14
//        a: 0
//        b: 10
//        c: 110
//        d: 111
//        01001100100111

//        Sample Output 2:
//        abacabad

public class B_Huffman {

    String decode(File file) throws FileNotFoundException {
        // Метод для декодирования закодированной строки из файла

        StringBuilder result = new StringBuilder();
        // Создаем объект StringBuilder для хранения результата декодирования

        Scanner scanner = new Scanner(file);
        // Создаем объект Scanner для чтения файла

        Integer count = scanner.nextInt();
        Integer length = scanner.nextInt();
        scanner.nextLine(); // Добавим эту строку, чтобы перейти к следующей строке
        // Читаем количество символов и длину закодированной строки

        Map<String, Character> codeToChar = new HashMap<>();
        // Создаем Map для хранения соответствия кода символу

        for (int i = 0; i < count; i++) {
            String line = scanner.nextLine();
            char letter = line.charAt(0);
            String code = line.substring(3);
            codeToChar.put(code, letter);
        }
        // Читаем коды символов и заполняем ими словарь

        String encodedString = scanner.nextLine();
        // Читаем закодированную строку из файла

        StringBuilder currentCode = new StringBuilder();
        for (int i = 0; i < length; i++) {
            currentCode.append(encodedString.charAt(i));
            if (codeToChar.containsKey(currentCode.toString())) {
                result.append(codeToChar.get(currentCode.toString()));
                currentCode.setLength(0);
            }
        }
        // Проходим по закодированной строке, декодируем и добавляем символы в результат

        return result.toString();
        // Возвращаем декодированную строку
    }

    public static void main(String[] args) throws FileNotFoundException {
        // Главный метод

        String root = System.getProperty("user.dir") + "/src/";
        // Получаем путь к текущей директории и добавляем относительный путь к файлу

        File f = new File(root + "by/it/group251051/cherniak/lesson03/encodeHuffman.txt");
        // Создаем файл из полученного пути

        B_Huffman instance = new B_Huffman();
        // Создаем объект instance класса B_Huffman

        String result = instance.decode(f);
        // Вызываем метод decode для объекта instance и сохраняем результат

        System.out.println(result);
        // Выводим результат декодирования
    }
}
