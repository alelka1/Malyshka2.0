package by.it.group251051.pavlushchenko.lesson03;

import java.io.*;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

//Lesson 3. A_Huffman.
//Разработайте метод encode(File file) для кодирования строки (код Хаффмана)

// По данным файла (непустой строке ss длины не более 104104),
// состоящей из строчных букв латинского алфавита,
// постройте оптимальный по суммарной длине беспрефиксный код.

// Используйте Алгоритм Хаффмана — жадный алгоритм оптимального
// беcпрефиксного кодирования алфавита с минимальной избыточностью.

// В первой строке выведите количество различных букв kk,
// встречающихся в строке, и размер получившейся закодированной строки.
// В следующих kk строках запишите коды букв в формате "letter: code".
// В последней строке выведите закодированную строку. Примеры ниже

//        Sample Input 1:
//        a
//
//        Sample Output 1:
//        1 1
//        a: 0
//        0

//        Sample Input 2:
//        abacabad
//
//        Sample Output 2:
//        4 14
//        a: 0
//        b: 10
//        c: 110
//        d: 111
//        01001100100111

public class A_Huffman {

    abstract class Node implements Comparable<Node> {

        private final int frequence;

        abstract void fillCodes(String code);

        private Node(int frequence) {
            this.frequence = frequence;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(frequence, o.frequence);
        }
    }

    private class InternalNode extends Node {
        Node left;
        Node right;


        InternalNode(Node left, Node right) {
            super(left.frequence + right.frequence);
            this.left = left;
            this.right = right;
        }

        @Override
        void fillCodes(String code) {
            left.fillCodes(code + "0");
            right.fillCodes(code + "1");
        }

    }

    private class LeafNode extends Node {
        //лист
        char symbol; //символы хранятся только в листах

        LeafNode(int frequence, char symbol) {
            super(frequence);
            this.symbol = symbol;
        }

        @Override
        void fillCodes(String code) {
            codes.put(this.symbol, code);
        }
    }

    //индекс данных из листьев
     private static Map<Character, String> codes = new TreeMap<>();


    /**     НАЧАЛО ЗАДАЧИ     */

    String encode(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String s = reader.readLine();

            Map<Character, Integer> count = new TreeMap<>();

            for (int i = 0; i < s.length(); i++) {
                char q = s.charAt(i);
                count.put(q, count.getOrDefault(q, 0) + 1);
            }

            PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
            for (Map.Entry<Character, Integer> entry : count.entrySet()) {
                LeafNode node = new LeafNode(entry.getValue(), entry.getKey());
                priorityQueue.add(node);
            }

            while (priorityQueue.size() != 1) {
                Node lf = priorityQueue.poll();
                Node rg = priorityQueue.poll();
                InternalNode node = new InternalNode(lf, rg);
                priorityQueue.add(node);
            }

            StringBuilder result = new StringBuilder();
            Node start = priorityQueue.poll();
            start.fillCodes("");

            for (int j = 0; j < s.length(); j++) {
                result.append(codes.get(s.charAt(j)));
            }

            return result.toString();
        }
    }

    /**    КОНЕЦ ЗАДАЧИ   */


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/a_khmelev/lesson03/dataHuffman.txt");
        A_Huffman instance = new A_Huffman();
        long startTime = System.currentTimeMillis();
        String result = null;
        try {
            result = instance.encode(f);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        long finishTime = System.currentTimeMillis();
        System.out.printf("%d %d\n", codes.size(), result.length());
        for (Map.Entry<Character, String> entry : codes.entrySet()) {
            System.out.printf("%s: %s\n", entry.getKey(), entry.getValue());
        }
        System.out.println(result);
    }

}