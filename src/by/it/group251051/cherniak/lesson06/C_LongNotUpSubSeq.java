package by.it.group251051.cherniak.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/*
Задача на программирование: наибольшая невозростающая подпоследовательность

Дано:
    целое число 1<=n<=1E5 ( ОБРАТИТЕ ВНИМАНИЕ НА РАЗМЕРНОСТЬ! )
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    для которой каждый элемент A[i[k]] не больше любого предыдущего
    т.е. для всех 1<=j<k, A[i[j]]>=A[i[j+1]].

    В первой строке выведите её длину k,
    во второй - её индексы i[1]<i[2]<…<i[k]
    соблюдая A[i[1]]>=A[i[2]]>= ... >=A[i[n]].

    (индекс начинается с 1)

Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ

    Sample Input:
    5
    5 3 4 4 2

    Sample Output:
    4
    1 3 4 5
*/

public class C_LongNotUpSubSeq {

    int getNotUpSeqSize(InputStream stream) throws FileNotFoundException {
        // Метод для нахождения размера наибольшей невозрастающей подпоследовательности

        // Подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        // Получаем общую длину последовательности
        int n = scanner.nextInt();
        int[] m = new int[n];
        // Читаем всю последовательность
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
        }

        int[] dp = new int[n];
        Arrays.fill(dp, 1); // Изначально у всех элементов наибольшая невозрастающая подпоследовательность
                            // длиной 1
        // Инициализируем массив dp, представляющий длину наибольшей невозрастающей
        // подпоследовательности

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (m[j] >= m[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1; // Увеличиваем длину подпоследовательности, если выполняется условие
                }
            }
        }
        // Ищем длины наибольших невозрастающих подпоследовательностей для всех
        // элементов последовательности

        int maxLength = 0;
        int maxIndex = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] > maxLength) {
                maxLength = dp[i];
                maxIndex = i;
            }
        }
        // Находим и запоминаем индекс начала самой длинной невозрастающей
        // подпоследовательности

        Stack<Integer> sequenceIndexes = new Stack<>();
        sequenceIndexes.push(maxIndex);
        int currentMax = maxLength - 1;
        for (int i = maxIndex - 1; i >= 0; i--) {
            if (m[i] >= m[maxIndex] && dp[i] == currentMax) {
                sequenceIndexes.push(i);
                maxIndex = i;
                currentMax--;
            }
        }
        // Формируем невозрастающую последовательность, сохраняя индексы элементов

        System.out.println(maxLength);
        while (!sequenceIndexes.isEmpty()) {
            System.out.print((sequenceIndexes.pop() + 1) + " ");
        }
        // Выводим длину наибольшей невозрастающей подпоследовательности и саму
        // подпоследовательность

        return maxLength;
        // Возвращаем длину наибольшей невозрастающей подпоследовательности
    }

    public static void main(String[] args) throws FileNotFoundException {
        // Главный метод

        String root = System.getProperty("user.dir") + "/src/";
        // Получаем путь к текущей директории и добавляем относительный путь к файлу

        InputStream stream = new FileInputStream(root + "by/it/group251051/cherniak/lesson06/dataC.txt");
        // Создаем файл из полученного пути

        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        // Создаем объект instance класса C_LongNotUpSubSeq

        int result = instance.getNotUpSeqSize(stream);
        // Получаем длину наибольшей невозрастающей подпоследовательности

        System.out.print(result);
        // Выводим результат
    }

}