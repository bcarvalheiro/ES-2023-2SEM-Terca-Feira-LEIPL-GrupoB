package org.es.leipl.tercafeira.grupob.tools.gui.swingCalendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author davejm
 * @version 1.10
 */

/**
 * Java Object for representation of a Week Calendar
 */

public class WeekCalendar extends Calendar {
    /**
     * Calendar week
     */
    private Week week;

    public WeekCalendar(ArrayList<CalendarEvent> events) {
        super(events);
        week = new Week(LocalDate.now());
    }

    /**
     * Overrides the Abstract method to return true if input date equals the start of the week
     * @param date
     * @return
     */
    @Override
    protected boolean dateInRange(LocalDate date) {
        return Week.getStartOfWeek(date).equals(week.getDay(DayOfWeek.MONDAY));
    }

    /**
     * Overrides the Abstract method to return a day of the week from the week day
     * @param day
     */
    @Override
    protected LocalDate getDateFromDay(DayOfWeek day) {
        return week.getDay(day);
    }

    /**
     * Overrides the Abstract method to return 7, number of week days
     * @return 7
     */
    protected int numDaysToShow() {
        return 7;
    }

    /**
     * Overrides the Abstract method to return monday as start of the week
     * @return DayofWeek
     */
    @Override
    protected DayOfWeek getStartDay() {
        return DayOfWeek.MONDAY;
    }

    /**
     * Overrides the Abstract method to return sunday as end of the week
     * @return DayofWeek
     */
    @Override
    protected DayOfWeek getEndDay() {
        return DayOfWeek.SUNDAY;
    }

    /**
     * Overrides the Abstract method to set the range to current date of the week
     */
    @Override
    protected void setRangeToToday() {
        week = new Week(LocalDate.now());
    }

    /**
     * Overrides the Abstract method to return the pixel representation to the defined in the global var + day width
     * @return dayToPixel
     */
    @Override
    protected double dayToPixel(DayOfWeek dayOfWeek) {
        return TIME_COL_WIDTH + getDayWidth() * (dayOfWeek.getValue() - 1);
    }

    /**
     * New method to calculate the nexth week
     */
    public void nextWeek() {
        week = week.nextWeek();
        repaint();
    }

    /**
     * New method to calculate the previous week
     */
    public void prevWeek() {
        week = week.prevWeek();
        repaint();
    }

    /**
     * New method to calculate the nexth month
     */
    public void nextMonth() {
        week = new Week(week.getDay(DayOfWeek.MONDAY).plusWeeks(4));
        repaint();
    }

    /**
     * New method to calculate the previous month
     */
    public void prevMonth() {
        week = new Week(week.getDay(DayOfWeek.MONDAY).minusWeeks(4));
        repaint();
    }

}
