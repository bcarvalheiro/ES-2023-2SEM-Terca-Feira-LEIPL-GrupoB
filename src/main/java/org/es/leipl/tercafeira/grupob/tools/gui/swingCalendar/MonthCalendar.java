package org.es.leipl.tercafeira.grupob.tools.gui.swingCalendar;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

/**
 * @author GRUPO_B_LEI_PL
 * @version 0.1
 */

/**
 * Java Object for representation of a Month Calendar
 */

public class MonthCalendar extends Calendar {
    /**
     * Calendar month
     */
    private Month month;

    public MonthCalendar(ArrayList<CalendarEvent> events) {
        super(events);
        month = new Month(LocalDate.now());
    }

    /**
     * Overrides the Abstract method to return true if input date equals the start of the month
     * @param date
     * @return
     */
    @Override
    protected boolean dateInRange(LocalDate date) {
        return Month.getStartOfMonth(date).equals(month.getDay(DayOfWeek.MONDAY));
    }

    /**
     * Overrides the Abstract method to return a day of the week from the month day
     * @param day
     */
    @Override
    protected LocalDate getDateFromDay(DayOfWeek day) {
        return month.getDay(day);
    }

    /**
     * Overrides the Abstract method to return the number of days the month has
     * @return int
     */
    protected int numDaysToShow() {
        return month.getLastday().getDayOfMonth();
    }

    /**
     * Overrides the Abstract method to return a day of week from the first day of the month
     * @return DayofWeek
     */
    @Override
    protected DayOfWeek getStartDay() {
        return month.getFirstday().getDayOfWeek();
    }

    /**
     * Overrides the Abstract method to return a day of week from the last day of the month
     * @return DayofWeek
     */
    @Override
    protected DayOfWeek getEndDay() {
        return month.getLastday().getDayOfWeek();
    }

    /**
     * Overrides the Abstract method to return a day of week from the first day of the month, in int
     * @return Int
     */
    protected int getStartDayInt() {
        return month.getFirstday().getDayOfMonth();
    }

    /**
     * Overrides the Abstract method to return a day of week from the last day of the month, in int
     * @return Int
     */
    protected int getEndDayInt() {
        return month.getLastday().getDayOfMonth();
    }

    /**
     * Overrides the Abstract method to set the range to current date of the month
     */
    @Override
    protected void setRangeToToday() {
        month = new Month(LocalDate.now());
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
     * New method to convert a day pixel to a day
     * @param day
     * @return dayToPixel
     */
    private double dayToPixelMonth(int day) {
        return TIME_COL_WIDTH + getDayWidth() * (day - 1);
    }


    /**
     * Overrides the method since the original is only able to handle 7 days, month view needs a maximum of 31
     */
    @Override
    public void drawDayHeadings() {
        int y = 20;
        int x;
        LocalDate day;
        DayOfWeek dayOfWeek;

        for (int i = getStartDayInt(); i <= getEndDayInt(); i++) {

            day = LocalDate.of(month.getYear(), month.getMonth(),i);
            dayOfWeek = day.getDayOfWeek();

            String text = dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.ENGLISH) + " " + day.getDayOfMonth() + "/" + day.getMonthValue();
            x = (int) (dayToPixelMonth(i) + (dayWidth / 2) - (FONT_LETTER_PIXEL_WIDTH * text.length() / 2));
            g2.drawString(text, x, y);
        }
    }

    /**
     * Overrides the method since the original is only able to handle 7 days, month view needs a maximum of 31
     */
    @Override
    public void drawGrid() {
        final Color ORIG_COLOUR = g2.getColor();

        Color alphaGray = new Color(128, 128, 128, 128);
        Color alphaGrayLighter = new Color(200, 200, 200, 128);
        g2.setColor(alphaGray);

        double x;
        for (int i = getStartDayInt(); i <= getEndDayInt(); i++) {
            x = dayToPixelMonth(i);
            g2.draw(new Line2D.Double(x, HEADER_HEIGHT, x, timeToPixel(END_TIME)));
        }

        double y;
        int x1;
        for (LocalTime time = START_TIME; time.compareTo(END_TIME) <= 0; time = time.plusMinutes(30)) {
            y = timeToPixel(time);
            if (time.getMinute() == 0) {
                g2.setColor(alphaGray);
                x1 = 0;
            } else {
                g2.setColor(alphaGrayLighter);
                x1 = TIME_COL_WIDTH;
            }
            g2.draw(new Line2D.Double(x1, y, dayToPixelMonth(getEndDayInt()) + dayWidth, y));
        }

        g2.setColor(ORIG_COLOUR);
    }

    /**
     * Overrides the method since the original is only able to handle 7 days, month view needs a maximum of 31
     */
    @Override
    public void drawTodayShade() {
        LocalDate today = LocalDate.now();

        if (!dateInRange(today)) return;

        final double x = dayToPixelMonth(today.getDayOfMonth());
        final double y = timeToPixel(START_TIME);
        final double width = dayWidth;
        final double height = timeToPixel(END_TIME) - timeToPixel(START_TIME);

        final Color origColor = g2.getColor();
        Color alphaGray = new Color(200, 200, 200, 64);
        g2.setColor(alphaGray);
        g2.fill(new Rectangle2D.Double(x, y, width, height));
        g2.setColor(origColor);
    }

    /**
     * Overrides the method since the original is only able to handle 7 days, month view needs a maximum of 31
     */
    @Override
    public void drawCurrentTimeLine() {
        LocalDate today = LocalDate.now();

        if (!dateInRange(today)) return;

        final double x0 = dayToPixel(today.getDayOfWeek());
        final double x1 = dayToPixel(today.getDayOfWeek()) + dayWidth;
        final double y = timeToPixel(LocalTime.now());

        final Color origColor = g2.getColor();
        final Stroke origStroke = g2.getStroke();

        g2.setColor(new Color(255, 127, 110));
        g2.setStroke(new BasicStroke(2));
        g2.draw(new Line2D.Double(x0, y, x1, y));

        g2.setColor(origColor);
        g2.setStroke(origStroke);
    }

    /**
     * New method to calculate the nexth month
     */
    public void nextMonth() {
        month = month.nextMonth();
        repaint();
    }

    /**
     * New method to calculate the previous month
     */
    public void prevMonth() {
        month = month.prevMonth();
        repaint();
    }
}
