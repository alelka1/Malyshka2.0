package by.it.group251051.stezhko.lesson02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
            return "("+ start +":" + stop + ")";
        }
    }

    public static void main(String[] args) {
        B_Sheduler instance = new B_Sheduler();
        Event[] events = {  new Event(0, 3),  new Event(0, 1), new Event(1, 2), new Event(3, 5),
                new Event(1, 3),  new Event(1, 3), new Event(1, 3), new Event(3, 6),
                new Event(2, 7),  new Event(2, 3), new Event(2, 7), new Event(7, 9),
                new Event(3, 5),  new Event(2, 4), new Event(2, 3), new Event(3, 7),
                new Event(4, 5),  new Event(6, 7), new Event(6, 9), new Event(7, 9),
                new Event(8, 9),  new Event(4, 6), new Event(8, 10), new Event(7, 10)
        };
        List<Event> starts = instance.calcStartTimes(events,0,10);  //рассчитаем оптимальное заполнение аудитории
        System.out.println(starts);                                 //покажем рассчитанный график занятий
    }

    List<Event> calcStartTimes(Event[] events, int from, int to) {
        //events - события которые нужно распределить в аудитории
        //в период [from, int] (включительно).
        //оптимизация проводится по наибольшему числу непересекающихся событий.
        //начало и конец событий могут совпадать.
        List<Event> result;
        result = new ArrayList<>();
        if (events.length == 0) return result;
        Event sortedEvents[] = events.clone();
        Arrays.sort(sortedEvents, (a,b) -> (a.start == b.start ? 0 : a.start > b.start ? 1 : -1));
        int newStart = sortedEvents[0].start;
        for (int i = 0; i < sortedEvents.length; i++) {
            int start = sortedEvents[i].start;
            if (start < from || start < newStart) continue;
            int minLengthOfEvent = (to - from) * 222;
            int iOfEventWithMinLength = -1;
            int j = i;
            while(j < sortedEvents.length && sortedEvents[j].start == start && sortedEvents[j].stop <= to) {
                int len = sortedEvents[j].stop - sortedEvents[j].start;
                if (len < minLengthOfEvent) {
                    minLengthOfEvent = len;
                    iOfEventWithMinLength = j;
                }
                j++;
            }
            if (iOfEventWithMinLength != -1) {
                i = j - 1;
                result.add(sortedEvents[iOfEventWithMinLength]);
                newStart = sortedEvents[iOfEventWithMinLength].stop;
            }
        }
        return result;                        //вернем итог
    }
}