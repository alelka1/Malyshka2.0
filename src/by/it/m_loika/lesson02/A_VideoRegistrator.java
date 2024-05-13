package by.it.m_loika.lesson02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class A_VideoRegistrator {

    public static void main(String[] args)  {
        A_VideoRegistrator instance = new A_VideoRegistrator();
        double[] events = new double[]{1, 1.1, 1.6, 2.2, 2.4, 2.7, 3.9, 8.1, 9.1, 5.5, 3.7};
        List<Double> starts = instance.calcStartTimes(events, 1); //рассчитаем моменты старта, с длинной сеанса 1
        System.out.println(starts);                            //покажем моменты старта
    }

    List<Double> calcStartTimes(double[] events, double workDuration) {
        List<Double> result = new ArrayList<>();
        Arrays.sort(events); // Сортировка событий по возрастанию времени
        int i = 0; // Индекс текущего события
        double startTime = events[0]; // Начальное время работы регистратора

        while (i < events.length) {
            result.add(startTime); // Добавляем время начала работы регистратора
            double endTime = startTime + workDuration; // Вычисляем время окончания работы регистратора

            // Пропускаем все события, которые происходят во время работы регистратора
            while (i < events.length && events[i] <= endTime) {
                i++;
            }

            // Если еще остались события, устанавливаем новое время начала работы регистратора
            if (i < events.length) {
                startTime = events[i];
            }
        }

        return result;
    }
}
