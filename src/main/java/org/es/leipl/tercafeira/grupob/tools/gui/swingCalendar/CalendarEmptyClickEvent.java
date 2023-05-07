package org.es.leipl.tercafeira.grupob.tools.gui.swingCalendar;

import java.awt.*;
import java.time.LocalDateTime;

/**
 * @author davejm
 * @version 1.10
 */

/**
 * Java Object for representation of an empty Calendar click event
 */
public class CalendarEmptyClickEvent extends AWTEvent {

    /**
     * Var to store the empty event datetime
     */
    private LocalDateTime dateTime;

    /**
     * Main calendar constructor
     * @param source - event object
     * @param dateTime - datetime of the vent
     */
    public CalendarEmptyClickEvent(Object source, LocalDateTime dateTime) {
        super(source, 0);
        this.dateTime = dateTime;
    }

    /**
     * Returns to local date time for the event
     * @return dateTime the current date time of the empty event
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
