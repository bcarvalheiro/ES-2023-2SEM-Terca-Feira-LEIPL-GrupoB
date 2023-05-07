package org.es.leipl.tercafeira.grupob.tools.gui.swingCalendar;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author davejm
 * @version 1.10
 */

/**
 * Java Object for representation of an CalendarEvent object
 */
public class CalendarEvent {

    private static final Color DEFAULT_COLOR = Color.PINK;

    private LocalDate date;
    private LocalTime start;
    private LocalTime end;
    private String text;
    private Color color;

    /**
     * Main calendar event construtor
     * @param date event date
     * @param start - event start time
     * @param end - event end time
     * @param text - event text
     */
    public CalendarEvent(LocalDate date, LocalTime start, LocalTime end, String text) {
        this(date, start, end, text, DEFAULT_COLOR);
    }

    /**
     * Secunndary calendar event construtor
     * @param date event date
     * @param start - event start time
     * @param end - event end time
     * @param text - event text
     * @param color - colour of the event
     */
    public CalendarEvent(LocalDate date, LocalTime start, LocalTime end, String text, Color color) {
        this.date = date;
        this.start = start;
        this.end = end;
        this.text = text;
        this.color = color;
    }

    /**
     * Returns the event local date
     * @return Locadate current event date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the current event date
     * @param date - sets the event date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Returns the event local start time
     * @return start current event start time
     */
    public LocalTime getStart() {
        return start;
    }

    /**
     * Sets the current event start time
     * @param start - sets the event start time
     */
    public void setStart(LocalTime start) {
        this.start = start;
    }

    /**
     * Returns the event local end time
     * @return end current event end time
     */
    public LocalTime getEnd() {
        return end;
    }

    /**
     * Sets the current event end time
     * @param end - sets the event end time
     */
    public void setEnd(LocalTime end) {
        this.end = end;
    }

    /**
     * Returns the event text
     * @return text returns the event name
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the current event text
     * @param text - text to set for the current event
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * String representation of the class Calendar Event
     * @return String - description of the current event
     */
    public String toString() {
        return getDate() + " " + getStart() + "-" + getEnd() + ". " + getText();
    }

    /**
     * Returns the event color
     * @return color - event color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the reference object with which to compare.
     * @return true if this object is the same as the o argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CalendarEvent that = (CalendarEvent) o;

        if (!date.equals(that.date)) return false;
        if (!start.equals(that.start)) return false;
        return end.equals(that.end);
    }
    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int result = date.hashCode();
        result = 31 * result + start.hashCode();
        result = 31 * result + end.hashCode();
        return result;
    }
}
