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
    private LocalDateTime dateTime;

    public CalendarEmptyClickEvent(Object source, LocalDateTime dateTime) {
        super(source, 0);
        this.dateTime = dateTime;
    }

    /**
     * @return dateTime the current date time of the empty event
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
