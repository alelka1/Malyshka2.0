package by.it.group251051.pavlushchenko.lesson02;

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
            double ratio1 = (double) this.cost / this.weight;
            double ratio2 = (double) o.cost / o.weight;
            return Double.compare(ratio2, ratio1);
        }
    }

    double calc(File source) throws FileNotFoundException {
        Scanner input = new Scanner(source);
        int n = input.nextInt();
        int W = input.nextInt();
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(input.nextInt(), input.nextInt());
        }

        Arrays.sort(items);

        for (Item item : items) {
            System.out.println(item);
        }

        System.out.printf("Всего: %d. Вмещает %d кг.\n", n, W);

        double result = 0;
        int currentWeight = 0;

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

        System.out.printf("Cобрать рюкзак удалось на сумму %f\n", result);
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