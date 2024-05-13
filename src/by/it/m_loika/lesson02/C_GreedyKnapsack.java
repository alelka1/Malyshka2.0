package by.it.m_loika.lesson02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class C_GreedyKnapsack {
    private static class Item implements Comparable<Item> {
        double cost;
        double weight;

        Item(double cost, double weight) {
            this.cost = cost;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "cost=" + cost +
                    ", weight=" + weight +
                    '}';
        }

        @Override
        public int compareTo(Item o) {
            // Сортировка по убыванию отношения стоимости к весу
            double ratio1 = this.cost / this.weight;
            double ratio2 = o.cost / o.weight;
            return Double.compare(ratio2, ratio1);
        }
    }

    double calc(File source) throws FileNotFoundException {
        Scanner input = new Scanner(source);
        int n = input.nextInt(); // Количество предметов в файле
        double W = input.nextInt(); // Вместимость рюкзака

        Item[] items = new Item[n]; // Получаем список предметов
        for (int i = 0; i < n; i++) {
            items[i] = new Item(input.nextInt(), input.nextInt());
        }

        // Сортируем предметы по убыванию отношения стоимости к весу
        Arrays.sort(items);

        // Покажем предметы
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.printf("Всего предметов: %d. Рюкзак вмещает %.2f кг.\n", n, W);

        double result = 0; // Результат - общая стоимость рюкзака
        int index = 0; // Индекс текущего предмета
        while (W > 0 && index < n) {
            double amount = Math.min(W, items[index].weight); // Выбираем максимальное количество предмета, которое поместится в рюкзаке
            result += amount * (items[index].cost / items[index].weight); // Обновляем общую стоимость
            W -= amount; // Уменьшаем свободную вместимость рюкзака
            index++; // Переходим к следующему предмету
        }

        System.out.printf("Удалось собрать рюкзак на сумму %.2f\n", result);
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/m_loika/lesson02/greedyKnapsack.txt");
        double costFinal = new C_GreedyKnapsack().calc(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %.2f (время %d)", costFinal, finishTime - startTime);
    }
}
