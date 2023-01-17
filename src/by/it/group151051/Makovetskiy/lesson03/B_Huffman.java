package by.it.group151051.Makovetskiy.lesson03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

    String decode(File file) throws IOException {
        StringBuilder result = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String str = reader.readLine();
        String[] tokens = str.split(" ");
        int count = Integer.parseInt(tokens[0]);
        int length = Integer.parseInt(tokens[1]);
        Map<String, String> chars = new HashMap<>();
        for (int i = 0; i < count; i++) {
            str = reader.readLine();
            tokens = str.split(": ");
            chars.put(tokens[0], tokens[1]);
        }
        String haf = reader.readLine();
        //4 14
        //a: 0
        //b: 10
        //c: 110
        //d: 111
        //01001100100111
        for (int i = 0; i < length; ) { //i = 11 j = 13
            StringBuilder codePrev = new StringBuilder(String.valueOf(haf.charAt(i)));
            StringBuilder codeNext = new StringBuilder(String.valueOf(haf.charAt(i)));
            for (int j = i+1; j < length; j++) {
                codeNext.append(haf.charAt(j));
                boolean exist = false;
                for (String value : chars.values()) {
                    if (value.startsWith(codeNext.toString())) {
                        codePrev = new StringBuilder(codeNext.toString());
                        exist = true;
                        break;
                    }
                }
                if (!exist) {
                    for (Map.Entry<String, String> entry : chars.entrySet()) {
                        if (entry.getValue().equals(codePrev.toString())) {
                            result.append(entry.getKey());
                            i+= codePrev.toString().length();
                            break;
                        }
                    }
                    break;
                }
                if (j == length-1) {
                    for (Map.Entry<String, String> entry : chars.entrySet()) {
                        if (entry.getValue().equals(codeNext.toString())) {
                            result.append(entry.getKey());
                            return result.toString();
                        }
                    }
                }
            }
        }
        return result.toString(); 
    }

    public static void main(String[] args) throws IOException {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/a_khmelev/lesson03/encodeHuffman.txt");
        B_Huffman instance = new B_Huffman();
        String result = instance.decode(f);
        System.out.println(result);
    }
}
