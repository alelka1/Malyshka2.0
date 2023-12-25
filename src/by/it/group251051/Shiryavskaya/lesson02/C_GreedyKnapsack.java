package by.it.group251051.Shiryavskaya.lesson02;

/*
Даны
1) объем рюкзака 4
2) число возможных предметов 60
3) сам набор предметов
    100 50
    120 30
    100 50
Все это указано в файле (by/it/a_khmelev/lesson02/greedyKnapsack.txt)

Необходимо собрать наиболее дорогой вариант рюкзака для этого объема
Предметы можно резать на кусочки (т.е. алгоритм будет жадным)
 */
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
            // Мы можем сравнивать предметы на основе их стоимости за единицу веса,
            // чтобы выбирать наиболее ценные предметы для помещения в рюкзак.
            // Вы можете использовать этот метод, чтобы определить, какой предмет стоит
            // выбрать.
            double ratio1 = (double) this.cost / this.weight;
            double ratio2 = (double) o.cost / o.weight;
            if (ratio1 < ratio2) {
                return 1; // Возвращаем 1, чтобы отсортировать в порядке убывания
            } else if (ratio1 > ratio2) {
                return -1; // Возвращаем -1, чтобы отсортировать в порядке убывания
            }

            return 0;
        }
    }

    double calc(File source) throws FileNotFoundException {
        Scanner input = new Scanner(source);
        int n = input.nextInt(); // сколько предметов в файле
        int W = input.nextInt(); // какой вес у рюкзака
        Item[] items = new Item[n]; // получим список предметов
        for (int i = 0; i < n; i++) { // создавая каждый конструктором
            items[i] = new Item(input.nextInt(), input.nextInt());
        }
        // покажем предметы
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n", n, W);

        // Сортируем предметы
        Arrays.sort(items);

        int curWeight = 0; // Текущий вес в рюкзаке
        double result = 0; // Результирующая стоимость вещей в рюкзаке

        // Проходим по предметам и собираем рюкзак
        for (int i = 0; i < n; i++) {
            if (curWeight + items[i].weight <= W) { // Если предмет полностью помещается в рюкзак
                curWeight += items[i].weight;
                result += items[i].cost;
                System.out.println("Добавляем " + items[i]);
            } else { // Если предмет помещается частично
                int remain = W - curWeight; // Сколько осталось места в рюкзаке
                double fraction = (double) remain / items[i].weight; // Находим долю предмета, которую можем положить
                result += items[i].cost * fraction; // Добавляем стоимость части предмета в рюкзак
                System.out.println("Добавляем " + items[i] + " частично: " + fraction);
                break; // Рюкзак заполнен, выходим из цикла
            }
        }

        System.out.printf("Удалось собрать рюкзак на сумму %f\n", result);
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/group251051/Shiryavskaya/lesson02/greedyKnapsack.txt");
        double costFinal = new C_GreedyKnapsack().calc(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)", costFinal, finishTime - startTime);
    }
}