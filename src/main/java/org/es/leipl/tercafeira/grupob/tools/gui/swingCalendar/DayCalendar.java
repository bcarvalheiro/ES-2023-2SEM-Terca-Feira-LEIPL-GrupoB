package org.es.leipl.tercafeira.grupob.tools.gui.swingCalendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author davejm
 * @version 1.10
 */

/**
 * Java Object for representation of a Day Calendar
 */

public class DayCalendar extends Calendar {
    /**
     * Calendar date var
     */
    private LocalDate calDate;

    /**
     * Main constructor
     * @param events - list of events
     */
    public DayCalendar(ArrayList<CalendarEvent> events) {
        super(events);
        calDate = LocalDate.now();
    }

    /**
     * Main constructor
     * @param events - list of events
     * @param date - day date
     */
    public DayCalendar(ArrayList<CalendarEvent> events, LocalDate date) {
        super(events);
        calDate = date;
    }

    /**
     * Overrides the Abstract method to return true if input date equals calendar date
     * @param date -  date to check in range
     * @return true if data in range
     */
    @Override
    protected boolean dateInRange(LocalDate date) {
        return calDate.equals(date);
    }

    /**
     * Overrides the Abstract method to return a day of the week from the calendar date
     * @param day - day from date
     */
    @Override
    protected LocalDate getDateFromDay(DayOfWeek day) {
        return calDate;
    }

    /**
     * Overrides the Abstract method to return 1 day to show
     * @return 1
     */
    @Override
    protected int numDaysToShow() {
        return 1;
    }

    /**
     * Overrides the Abstract method to return a day of week from the calendar date
     * @return DayofWeek
     */
    @Override
    protected DayOfWeek getStartDay() {
        return calDate.getDayOfWeek();
    }

    /**
     * Overrides the Abstract method to return a day of week from the calendar date
     * @return DayofWeek
     */
    @Override
    protected DayOfWeek getEndDay() {
        return calDate.getDayOfWeek();
    }

    /**
     * Overrides the Abstract method to set the range to current date
     */
    @Override
    protected void setRangeToToday() {
        calDate = LocalDate.now();
    }

    /**
     * Overrides the Abstract method to return the pixel representation to the defined in the global var
     * @return dayToPixel
     */
    @Override
    protected double dayToPixel(DayOfWeek dayOfWeek) {
        return TIME_COL_WIDTH;
    }

    /**
     * Overrides the Abstract method to add 1 day to the next method
     */
    public void nextDay() {
        calDate = calDate.plusDays(1);
        repaint();
    }

    /**
     * Overrides the Abstract method to remove 1 day to the next method
     */
    public void prevDay() {
        calDate = calDate.minusDays(1);
        repaint();
    }
}
