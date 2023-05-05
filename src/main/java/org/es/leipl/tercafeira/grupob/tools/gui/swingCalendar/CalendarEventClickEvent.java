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

    private CalendarEvent calendarEvent;

    public CalendarEventClickEvent(Object source, CalendarEvent calendarEvent) {
        super(source, 0);
        this.calendarEvent = calendarEvent;
    }

    /**
     * @return dateTime the current date time of the  event
     */
    public CalendarEvent getCalendarEvent() {
        return calendarEvent;
    }
}
