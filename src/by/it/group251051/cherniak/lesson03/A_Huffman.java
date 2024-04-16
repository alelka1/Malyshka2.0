package by.it.group251051.cherniak.lesson03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

//Lesson 3. A_Huffman.
//Разработайте метод encode(File file) для кодирования строки (код Хаффмана)

// По данным файла (непустой строке ss длины не более 104104),
// состоящей из строчных букв латинского алфавита,
// постройте оптимальный по суммарной длине беспрефиксный код.

// Используйте Алгоритм Хаффмана — жадный алгоритм оптимального
// безпрефиксного кодирования алфавита с минимальной избыточностью.

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

    // Изучите классы Node InternalNode LeafNode
    abstract class Node implements Comparable<Node> {
        // абстрактный класс элемент дерева
        // (сделан abstract, чтобы нельзя было использовать его напрямую)
        // а только через его версии InternalNode и LeafNode
        private final int frequence; // частота символов

        // генерация кодов (вызывается на корневом узле
        // один раз в конце, т.е. после построения дерева)
        abstract void fillCodes(String code);

        // конструктор по умолчанию
        private Node(int frequence) {
            this.frequence = frequence;
        }

        // метод нужен для корректной работы узла в приоритетной очереди
        // или для сортировок
        @Override
        public int compareTo(Node o) {
            return Integer.compare(frequence, o.frequence);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////
    // расширение базового класса до внутреннего узла дерева
    private class InternalNode extends Node {
        // внутренный узел дерева
        Node left; // левый ребенок бинарного дерева
        Node right; // правый ребенок бинарного дерева

        // для этого дерева не существует внутренних узлов без обоих детей
        // поэтому вот такого конструктора будет достаточно
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

    ////////////////////////////////////////////////////////////////////////////////////
    // расширение базового класса до листа дерева
    private class LeafNode extends Node {
        // лист
        char symbol; // символы хранятся только в листах

        LeafNode(int frequence, char symbol) {
            super(frequence);
            this.symbol = symbol;
        }

        @Override
        void fillCodes(String code) {
            // добрались до листа, значит рекурсия закончена, код уже готов
            // и можно запомнить его в индексе для поиска кода по символу.
            codes.put(this.symbol, code);
        }
    }

    // индекс данных из листьев
    static private Map<Character, String> codes = new TreeMap<>();

    // !!!!!!!!!!!!!!!!!!!!!!!!! НАЧАЛО ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!
    String encode(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String s = scanner.next();

        Map<Character, Integer> count = new HashMap<>();
        // 1. переберем все символы по очереди и рассчитаем их частоту в Map count
        for (char ch : s.toCharArray()) {
            count.put(ch, count.getOrDefault(ch, 0) + 1);
        }

        // 2. создадим приоритетную очередь и положим в нее листья
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : count.entrySet()) {
            priorityQueue.add(new LeafNode(entry.getValue(), entry.getKey()));
        }

        // 3. с обходом приоритетной очереди, собираем дерево Хаффмана
        while (priorityQueue.size() > 1) {
            Node left = priorityQueue.poll();
            Node right = priorityQueue.poll();
            priorityQueue.add(new InternalNode(left, right));
        }

        Node root = priorityQueue.poll();
        root.fillCodes(""); // Заполним коды для каждого символа в дереве

        StringBuilder sb = new StringBuilder();
        // 4. Закодируем исходную строку
        for (char ch : s.toCharArray()) {
            sb.append(codes.get(ch));
        }

        return sb.toString();
    }
    // !!!!!!!!!!!!!!!!!!!!!!!!! КОНЕЦ ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!

    public static void main(String[] args) throws FileNotFoundException {
        // Главный метод

        String root = System.getProperty("user.dir") + "/src/";
        // Получаем путь к текущей директории и добавляем относительный путь к файлу

        File f = new File(root + "by/it/group251051/cherniak/lesson03/dataHuffman.txt");
        // Создаем файл f из полученного пути

        A_Huffman instance = new A_Huffman();
        // Создаем объект instance класса A_Huffman

        long startTime = System.currentTimeMillis();
        // Фиксируем начальное время выполнения

        String result = instance.encode(f);
        // Вызываем метод encode для объекта instance и сохраняем результат в переменную
        // result

        long finishTime = System.currentTimeMillis();
        // Фиксируем конечное время выполнения

        System.out.printf("%d %d\n", codes.size(), result.length());
        // Выводим размер codes (предположительно использованного внутри класса
        // A_Huffman) и длину результата

        for (Map.Entry<Character, String> entry : codes.entrySet()) {
            // Итерируемся по парам (символ, код) из codes
            System.out.printf("%s: %s\n", entry.getKey(), entry.getValue());
            // Выводим каждую пару (символ, код)
        }

        System.out.println(result);
        // Выводим закодированный результат
    }

}
