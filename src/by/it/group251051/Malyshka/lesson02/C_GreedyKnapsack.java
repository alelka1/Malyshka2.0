package by.it.group251051.Malyshka.lesson02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class C_GreedyKnapsack {
    private static class Item implements Comparable<Item> {
        int cost;
        int weight;

        Item(int cost, int weight) {
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
            // Сортируем в порядке убывания стоимости за единицу веса
            double ratio1 = (double) this.cost / this.weight;
            double ratio2 = (double) o.cost / o.weight;
            return Double.compare(ratio2, ratio1);
        }
    }

    double calc(File source) throws FileNotFoundException {
        Scanner input = new Scanner(source);
        int n = input.nextInt();      //сколько предметов в файле
        int W = input.nextInt();      //какой вес у рюкзака
        Item[] items = new Item[n];   //получим список предметов
        for (int i = 0; i < n; i++) { //создавая каждый конструктором
            items[i] = new Item(input.nextInt(), input.nextInt());
        }

        // Сортируем предметы по убыванию стоимости за единицу веса
        Arrays.sort(items);

        // Покажем предметы после сортировки
        for (Item item : items) {
            System.out.println(item);
        }

        System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n", n, W);

        double result = 0;
        int currentWeight = 0;

        // Заполняем рюкзак жадным образом
        for (Item item : items) {
            if (currentWeight + item.weight <= W) {
                result += item.cost;
                currentWeight += item.weight;
            } else {
                // Резка предмета на кусочки
                double remainingWeight = W - currentWeight;
                result += item.cost * (remainingWeight / item.weight);
                break;
            }
        }

        System.out.printf("Удалось собрать рюкзак на сумму %f\n", result);
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        String root = System.getProperty("user.dir") + "/src/by/it/a_khmelev/lesson02/";
        File f = new File(root + "greedyKnapsack.txt");
        double costFinal = new C_GreedyKnapsack().calc(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)", costFinal, finishTime - startTime);
    }
}

