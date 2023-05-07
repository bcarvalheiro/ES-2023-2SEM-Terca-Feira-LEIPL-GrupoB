package org.es.leipl.tercafeira.grupob.tools.gui.swingCalendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

/**
 * @author GRUPO_B_LEI_PL
 * @version 0.1
 */

/**
 * Java Object for representation of a Month
 */

public class Month {
    /**
     * list of days
     */
    private ArrayList<LocalDate> days;

    /**
     * Stores the first day of the month
     */
    private LocalDate firstday;

    /**
     * Stores the last day of the month
     */
    private LocalDate lastday;

    /**
     * Stores the int value of the month of the month date
     */
    private int month;

    /**
     * Stores the int value of the year of the month date
     */
    private int year;

    /**
     * Creates a new instance of the Month class with the specified LocalDate object representing a date within the month.
     *
     * @param date a LocalDate object representing a date within the month.
     */
    public Month(LocalDate date) {
        days = new ArrayList<>();
        firstday = getStartOfMonth(date);
        days.add(firstday);
        lastday = firstday.with(TemporalAdjusters.lastDayOfMonth());
        month = date.getMonthValue();
        year = date.getYear();

        for (int i = 1; i < lastday.getDayOfMonth(); i++) {
            days.add(firstday.plusDays(i));
        }
    }

    /**
     * Returns the start of the month, in date
     * @param date - date to get month start
     * @return LocalDate
     */
    public static LocalDate getStartOfMonth(LocalDate date) {
        LocalDate day = date;
        day = day.withDayOfMonth(1);
        return day;
    }

    /**
     * Returns the first date of the month, in date
     * @return LocalDate
     */
    public LocalDate getFirstday() {
        return firstday;
    }

    /**
     * Returns the last date of the month, in date
     * @return LocalDate
     */
    public LocalDate getLastday() {
        return lastday;
    }

    /**
     * Returns a local date from a day of the week
     * @param dayOfWeek - day of the week
     * @return LocalDate
     */
    public LocalDate getDay(DayOfWeek dayOfWeek) {
        return days.get(dayOfWeek.getValue() - 1);
    }

    /**
     * Returns the next month, by adding 1 month to the first day of the month
     * @return Month
     */
    public Month nextMonth() {
        return new Month(firstday.plusMonths(1));
    }

    /**
     * Returns the previous month, by adding 1 month to the first day of the month
     * @return Month
     */
    public Month prevMonth() {
        return new Month(firstday.minusMonths(1));
    }

    /**
     * String representation of the month
     * @return Month
     */
    public String toString() {
        return "Month of the " + getDay(DayOfWeek.MONDAY);
    }

    /**
     * Returns the current month of the month date, in int
     * @return Month
     */
    public int getMonth() {
        return month;
    }

    /**
     * Returns the current year of the month date, in int
     * @return Month
     */
    public int getYear() {
        return year;
    }

    /**
     * Test
     * @param args input
     */
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        Month currentMonth = new Month(now);
        System.out.println(currentMonth);
        System.out.println(currentMonth.prevMonth());
        System.out.println(currentMonth.nextMonth());
    }

}
