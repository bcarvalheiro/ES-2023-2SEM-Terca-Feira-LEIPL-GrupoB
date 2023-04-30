package org.es.leipl.tercafeira.grupob.tools.gui.swingCalendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

public class Month {

    private ArrayList<LocalDate> days;
    private LocalDate firstday;
    private LocalDate lastday;
    private int month;
    private int year;

    // Gets week variables from any date (can be within week)
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

    public static LocalDate getStartOfMonth(LocalDate date) {
        LocalDate day = date;
        day = day.withDayOfMonth(1);
        return day;
    }

    public LocalDate getFirstday() {
        return firstday;
    }

    public LocalDate getLastday() {
        return lastday;
    }
    public LocalDate getDay(DayOfWeek dayOfWeek) {
        return days.get(dayOfWeek.getValue() - 1);
    }

    public Month nextMonth() {
        return new Month(firstday.plusMonths(1));
    }

    public Month prevMonth() {
        return new Month(firstday.minusMonths(1));
    }

    public String toString() {
        return "Month of the " + getDay(DayOfWeek.MONDAY);
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        Month currentMonth = new Month(now);
        System.out.println(currentMonth);
        System.out.println(currentMonth.prevMonth());
        System.out.println(currentMonth.nextMonth());
    }

}
