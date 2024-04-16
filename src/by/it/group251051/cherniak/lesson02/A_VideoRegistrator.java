package by.it.group251051.cherniak.lesson02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
даны события events
реализуйте метод calcStartTimes, так, чтобы число включений регистратора на
заданный период времени (1) было минимальным, а все события events
были зарегистрированы.
Алгоритм жадный. Для реализации обдумайте надежный шаг.
*/
public class A_VideoRegistrator {
    // Объявление класса с именем A_VideoRegistrator

    public static void main(String[] args) {
        A_VideoRegistrator instance = new A_VideoRegistrator();
        // Создание объекта instance класса A_VideoRegistrator

        double[] events = new double[] { 1, 1.1, 1.6, 2.2, 2.4, 2.7, 3.9, 8.1, 9.1, 5.5, 3.7 };
        // Инициализация массива с событиями

        List<Double> starts = instance.calcStartTimes(events, 1); // рассчитаем моменты старта, с длинной сеанса 1
        // Вычисление моментов старта используя метод calcStartTimes

        System.out.println(starts); // покажем моменты старта
        // Вывод на экран рассчитанных моментов старта
    }

    List<Double> calcStartTimes(double[] events, double workDuration) {
        // Метод для расчета моментов старта

        List<Double> result = new ArrayList<>();
        // Создание списка для хранения моментов старта

        Arrays.sort(events); // Сортируем события для удобства обработки
        // Сортировка массива событий по возрастанию

        for (int i = 0; i < events.length;) {
            // Цикл для обработки событий

            double start = events[i]; // Начало работы видеокамеры
            double end = start + workDuration; // Момент окончания работы видеокамеры
            result.add(start); // Добавляем момент старта в результат

            // Пропускаем все покрываемые события за время до конца работы, увеличивая
            // индекс
            while (i < events.length && events[i] <= end) {
                i++;
            }
            // Переход к следующему событию, покрываемому текущим интервалом времени
        }

        return result; // Возвращаем итог
        // Возврат списка моментов старта
    }
}
