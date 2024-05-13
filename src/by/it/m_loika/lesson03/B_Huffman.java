package by.it.m_loika.lesson03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class B_Huffman {

    String decode(File file) throws FileNotFoundException {
        StringBuilder result = new StringBuilder();

        // читаем строку для кодирования из тестового файла
        Scanner scanner = new Scanner(file);
        int k = scanner.nextInt(); // количество различных букв
        int l = scanner.nextInt(); // размер закодированной строки

        Map<String, Character> codes = new HashMap<>();
        for (int i = 0; i < k; i++) {
            String letter = scanner.next().replaceAll(":", "");
            String code = scanner.next();
            codes.put(code, letter.charAt(0));
        }

        String encodedString = scanner.next();

        StringBuilder currentCode = new StringBuilder();
        for (char c : encodedString.toCharArray()) {
            currentCode.append(c);
            if (codes.containsKey(currentCode.toString())) {
                result.append(codes.get(currentCode.toString()));
                currentCode = new StringBuilder();
            }
        }

        return result.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/m_loika/lesson03/encodeHuffman.txt");
        B_Huffman instance = new B_Huffman();
        String result = instance.decode(f);
        System.out.println(result);
    }
}
