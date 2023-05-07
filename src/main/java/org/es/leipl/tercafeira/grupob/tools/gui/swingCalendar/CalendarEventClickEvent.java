package org.es.leipl.tercafeira.grupob.tools.gui.swingCalendar;

import java.awt.*;

/**
 * @author davejm
 * @version 1.10
 */

/**
 * Java Object for representation of an Calendar click event
 */
public class CalendarEventClickEvent extends AWTEvent {

    /**
     * Var to store the calendar event
     */
    private CalendarEvent calendarEvent;

    /**
     * Main constructor
     * @param source event source
     * @param calendarEvent event
     */
    public CalendarEventClickEvent(Object source, CalendarEvent calendarEvent) {
        super(source, 0);
        this.calendarEvent = calendarEvent;
    }

    /**
     * Returns the calendar event
     * @return dateTime the current date time of the  event
     */
    public CalendarEvent getCalendarEvent() {
        return calendarEvent;
    }
}
