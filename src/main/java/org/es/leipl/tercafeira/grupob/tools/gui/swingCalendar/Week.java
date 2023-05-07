package org.es.leipl.tercafeira.grupob.tools.gui.swingCalendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author davejm
 * @version 1.10
 */

/**
 * Java Object for representation of a Week
 */

public class Week {
    /**
     * list of days
     */
    private ArrayList<LocalDate> days;

    /**
     * Creates a new instance of the Week class with the specified LocalDate object representing a date within the week.
     *
     * @param date a LocalDate object representing a date within the week.
     */
    public Week(LocalDate date) {
        days = new ArrayList<>();
        LocalDate monday = getStartOfWeek(date);
        days.add(monday);
        for (int i = 1; i < 7; i++) {
            days.add(monday.plusDays(i));
        }
    }

    /**
     * Returns the start of the week, in date
     * @param date
     * @return LocalDate
     */
    public static LocalDate getStartOfWeek(LocalDate date) {
        LocalDate day = date;
        while (day.getDayOfWeek() != DayOfWeek.MONDAY) {
            day = day.minusDays(1);
        }
        return day;
    }

    /**
     * Returns the first date of the week, in date
     * @return LocalDate
     */
    public LocalDate getDay(DayOfWeek dayOfWeek) {
        // DayOfWeek enum starts with monday == 1
        return days.get(dayOfWeek.getValue() - 1);
    }

    /**
     * Returns the next week, by adding 1 day to sunday of the current week
     * @return Month
     */
    public Week nextWeek() {
        final LocalDate sunday = getDay(DayOfWeek.SUNDAY);
        return new Week(sunday.plusDays(1));
    }

    /**
     * Returns the previous week, by subtracting 1 day to monday of the current week
     * @return Month
     */
    public Week prevWeek() {
        final LocalDate monday = getDay(DayOfWeek.MONDAY);
        return new Week(monday.minusDays(1));
    }

    /**
     * String representation of the week
     * @return Month
     */
    public String toString() {
        return "Week of the " + getDay(DayOfWeek.MONDAY);
    }

    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        Week currentWeek = new Week(now);
        System.out.println(currentWeek);
        System.out.println(currentWeek.prevWeek());
        System.out.println(currentWeek.nextWeek());
    }

}
