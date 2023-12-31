package by.it.group251051.prach.lesson02;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Arrays;

/*
даны интервальные события events
реализуйте метод calcStartTimes, так, чтобы число принятых к выполнению
непересекающихся событий было максимально.
Алгоритм жадный. Для реализации обдумайте надежный шаг.
*/

public class B_Sheduler {
    //событие у аудитории(два поля: начало и конец)
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

    public static void main(String[] args) {
        B_Sheduler instance = new B_Sheduler();
        Event[] events = {new Event(0, 3), new Event(0, 1), new Event(1, 2), new Event(3, 5),
                new Event(1, 3), new Event(1, 3), new Event(1, 3), new Event(3, 6),
                new Event(2, 7), new Event(2, 3), new Event(2, 7), new Event(7, 9),
                new Event(3, 5), new Event(2, 4), new Event(2, 3), new Event(3, 7),
                new Event(4, 5), new Event(6, 7), new Event(6, 9), new Event(7, 9),
                new Event(8, 9), new Event(4, 6), new Event(8, 10), new Event(7, 10)
        };

        List<Event> starts = instance.calcStartTimes(events, 0, 10);  //рассчитаем оптимальное заполнение аудитории
        System.out.println(starts);                                 //покажем рассчитанный график занятий
    }

    List<Event> calcStartTimes(Event[] events, int from, int to) {
        //events - события которые нужно распределить в аудитории
        //в период [from, int] (включительно).
        //оптимизация проводится по наибольшему числу непересекающихся событий.
        //начало и конец событий могут совпадать.
        List<Event> result;
        result = new ArrayList<>();
        //ваше решение.

        /*
         * Массив событий events сортируется по их времени начала и времени окончания.
         * Это сделано для того, чтобы последующий проход по массиву был удобен для выбора подходящих событий.
         * Используется лямбда-выражение для сравнения событий и сортировки их в порядке от наименьшего времени начала к наибольшему.
         * При равенстве времени начала используется время окончания для определения порядка.
         * */
        Arrays.sort(events, (Event prev, Event next) -> {
            if (prev.start == next.start) {
                if (prev.stop == next.stop) {
                    return 0;
                }
                return prev.stop < next.stop ? -1 : 1;
            }
            return prev.start < next.start ? -1 : 1;
        });

        /*
         * Затем выполняется цикл по отсортированному массиву events.
         * Для каждого события проверяется, если его время начала (event.start) находится после from (времени начала интервала, с которого начинается поиск событий)
         * и время окончания события (event.stop) находится до или равно to (времени окончания интервала, на котором заканчивается поиск событий).
         * */
        for (Event event : events) {
            if (event.start >= from) {
                /*
                 * Как только найдено событие, время окончания которого превышает to, цикл завершается.
                 */
                if (event.stop > to) {
                    break;
                }

                /*
                 * Если условия выше выполняются, событие добавляется в список result, и значение from обновляется до времени окончания текущего события.
                 * Это позволяет продолжать поиск следующих событий, начиная с времени окончания предыдущего.
                 * */
                result.add(event);
                from = event.stop;
            }
        }

        return result;                        //вернем итог
    }
}