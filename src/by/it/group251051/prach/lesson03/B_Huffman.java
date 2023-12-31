package by.it.group251051.prach.lesson03;

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
        StringBuilder result = new StringBuilder();

        // Прочитаем строки из входного файла
        Scanner scanner = new Scanner(file);
        int count = scanner.nextInt();
        int length = scanner.nextInt();
        scanner.nextLine(); // Пропускаем перевод строки после второго числа

        // Считываем коды символов в карту
        Map<String, Character> charCodes = new HashMap<>();
        for (int i = 0; i < count; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split(": ");
            charCodes.put(parts[1], parts[0].charAt(0));
        }

        // Считываем закодированную строку
        String encodedString = scanner.nextLine();

        // Раскодируем закодированную строку
        StringBuilder code = new StringBuilder();
        for (char bit : encodedString.toCharArray()) {
            code.append(bit);
            if (charCodes.containsKey(code.toString())) {
                result.append(charCodes.get(code.toString()));
                code = new StringBuilder();
            }
        }

        return result.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/a_khmelev/lesson03/encodeHuffman.txt");
        B_Huffman instance = new B_Huffman();
        String result = instance.decode(f);
        System.out.println(result);
    }


}
