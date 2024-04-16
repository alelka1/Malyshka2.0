package by.it.group251051.cherniak.lesson02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
/*
даны интервальные события events
реализуйте метод calcStartTimes, так, чтобы число принятых к выполнению
непересекающихся событий было максимально.
Алгоритм жадный. Для реализации обдумайте надежный шаг.
*/

public class B_Sheduler {
    // Объявление класса с именем B_Sheduler

    static class Event {
        int start;
        int stop;

        Event(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public String toString() {
            return "(" + start + ":" + stop + ")";
        }
    }
    // Внутренний класс для представления событий

    public static void main(String[] args) {
        B_Sheduler instance = new B_Sheduler();
        // Создание объекта instance класса B_Sheduler

        Event[] events = { new Event(0, 3), new Event(0, 1), new Event(1, 2), new Event(3, 5),
                new Event(1, 3), new Event(1, 3), new Event(1, 3), new Event(3, 6),
                new Event(2, 7), new Event(2, 3), new Event(2, 7), new Event(7, 9),
                new Event(3, 5), new Event(2, 4), new Event(2, 3), new Event(3, 7),
                new Event(4, 5), new Event(6, 7), new Event(6, 9), new Event(7, 9),
                new Event(8, 9), new Event(4, 6), new Event(8, 10), new Event(7, 10)
        };
        // Инициализация массива событий

        List<Event> starts = instance.calcStartTimes(events, 0, 10); // рассчитаем оптимальное заполнение аудитории
        // Расчет оптимального заполнения аудитории используя метод calcStartTimes

        System.out.println(starts); // покажем рассчитанный график занятий
        // Вывод на экран рассчитанного графика занятий
    }

    List<Event> calcStartTimes(Event[] events, int from, int to) {
        Arrays.sort(events, Comparator.comparingInt(e -> e.stop)); // сортировка по времени завершения
        // Сортировка массива событий по времени завершения

        List<Event> result = new ArrayList<>(); // Результирующий список событий
        // Создание списка для хранения результата

        int lastEndTime = from; // Время окончания предыдущего события
        // Инициализация времени окончания предыдущего события

        for (Event event : events) {
            // Цикл для обработки событий
            if (event.start >= lastEndTime && event.stop <= to) {
                result.add(event); // Добавляем событие, если оно не пересекается с предыдущими
                lastEndTime = event.stop; // Обновляем время окончания
            }
            // Проверка и добавление события в список, если оно не пересекается с
            // предыдущими
        }

        return result; // Возвращаем результирующий список
        // Возврат списка событий
    }
}
