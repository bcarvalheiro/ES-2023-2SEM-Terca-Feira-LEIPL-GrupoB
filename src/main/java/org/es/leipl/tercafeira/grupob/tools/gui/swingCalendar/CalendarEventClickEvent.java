package org.es.leipl.tercafeira.grupob.tools.gui.swingCalendar;

import java.awt.*;

public class CalendarEventClickEvent extends AWTEvent {

    private CalendarEvent calendarEvent;

    public CalendarEventClickEvent(Object source, CalendarEvent calendarEvent) {
        super(source, 0);
        this.calendarEvent = calendarEvent;
    }

    public CalendarEvent getCalendarEvent() {
        return calendarEvent;
    }
}
