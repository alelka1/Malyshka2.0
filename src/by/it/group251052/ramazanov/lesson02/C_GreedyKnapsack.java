package by.it.group251052.ramazanov.lesson02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class C_GreedyKnapsack {
    private static class Item {
        int cost;
        int weight;

        Item(int cost, int weight) {
            this.cost = cost;
            this.weight = weight;
        }
    }

    double calc(File source) throws FileNotFoundException {
        Scanner input = new Scanner(source);
        int n = input.nextInt(); // Сколько предметов в файле
        int W = input.nextInt(); // Какой вес у рюкзака
        Item[] items = new Item[n]; // Получим список предметов

        for (int i = 0; i < n; i++) { // Создаем каждый предмет конструктором
            items[i] = new Item(input.nextInt(), input.nextInt());
        }

        // Сортируем предметы по убыванию отношения стоимости к весу
        Arrays.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                double ratio1 = (double) item1.cost / item1.weight;
                double ratio2 = (double) item2.cost / item2.weight;
                return Double.compare(ratio2, ratio1); // Сортируем в убывающем порядке
            }
        });

        double result = 0; // Общая стоимость рюкзака
        int currentWeight = 0; // Текущий вес рюкзака

        for (Item item : items) {
            if (currentWeight + item.weight <= W) { // Предмет помещается целиком
                result += item.cost;
                currentWeight += item.weight;
            } else { // Предмет не помещается целиком
                double remainingWeight = W - currentWeight;
                result += (item.cost * remainingWeight) / item.weight; // Добавляем часть предмета
                break; // Рюкзак заполнен
            }
        }

        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/a_khmelev/lesson02/greedyKnapsack.txt");
        double costFinal = new C_GreedyKnapsack().calc(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)", costFinal, finishTime - startTime);
    }
}
