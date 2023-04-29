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

public class MonthCalendar extends Calendar {

    private Month month;

    public MonthCalendar(ArrayList<CalendarEvent> events) {
        super(events);
        month = new Month(LocalDate.now());
    }

    //rever
    @Override
    protected boolean dateInRange(LocalDate date) {
        return Month.getStartOfMonth(date).equals(month.getDay(DayOfWeek.MONDAY));
    }

    @Override
    protected LocalDate getDateFromDay(DayOfWeek day) {
        return month.getDay(day);
    }

    protected int numDaysToShow() {
        return month.getLastday().getDayOfMonth();
    }

    @Override
    protected DayOfWeek getStartDay() {
        return month.getFirstday().getDayOfWeek();
    }

    @Override
    protected DayOfWeek getEndDay() {
        return month.getLastday().getDayOfWeek();
    }

    protected int getStartDayInt() {
        return month.getFirstday().getDayOfMonth();
    }

    protected int getEndDayInt() {
        return month.getLastday().getDayOfMonth();
    }

    @Override
    protected void setRangeToToday() {
        month = new Month(LocalDate.now());
    }

    @Override
    protected double dayToPixel(DayOfWeek dayOfWeek) {
        return TIME_COL_WIDTH + getDayWidth() * (dayOfWeek.getValue() - 1);
    }

    private double dayToPixelMonth(int day) {
        return TIME_COL_WIDTH + getDayWidth() * (day - 1);
    }
    @Override
    public void drawDayHeadings() {
        int y = 20;
        int x;
        LocalDate day;
        DayOfWeek dayOfWeek;

        for (int i = getStartDayInt(); i <= getEndDayInt(); i++) {

            day = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(),i);
            dayOfWeek = day.getDayOfWeek();

            String text = dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.ENGLISH) + " " + day.getDayOfMonth() + "/" + day.getMonthValue();
            x = (int) (dayToPixelMonth(i) + (dayWidth / 2) - (FONT_LETTER_PIXEL_WIDTH * text.length() / 2));
            g2.drawString(text, x, y);
        }
    }

    @Override
    public void drawGrid() {
        // Save the original colour
        final Color ORIG_COLOUR = g2.getColor();
        LocalDate day;
        DayOfWeek dayOfWeek;

        // Set colour to grey with half alpha (opacity)
        Color alphaGray = new Color(128, 128, 128, 128);
        Color alphaGrayLighter = new Color(200, 200, 200, 128);
        g2.setColor(alphaGray);

        // Draw vertical grid lines
        double x;
        for (int i = getStartDayInt(); i <= getEndDayInt(); i++) {
            day = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(),i);
            dayOfWeek = day.getDayOfWeek();

            x = dayToPixelMonth(i);
            g2.draw(new Line2D.Double(x, HEADER_HEIGHT, x, timeToPixel(END_TIME)));
        }

        // Draw horizontal grid lines
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

        // Reset the graphics context's colour
        g2.setColor(ORIG_COLOUR);
    }
    @Override
    public void drawTodayShade() {
        LocalDate today = LocalDate.now();

        // Check that date range being viewed is current date range
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

    @Override
    public void drawCurrentTimeLine() {
        LocalDate today = LocalDate.now();

        // Check that date range being viewed is current date range
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

    public void nextWeek() {
        month = month.nextMonth();
        repaint();
    }

    public void prevWeek() {
        month = month.prevMonth();
        repaint();
    }

    public void nextMonth() {
        month = month.nextMonth();
        repaint();
    }

    public void prevMonth() {
        month = month.prevMonth();
        repaint();
    }

}
