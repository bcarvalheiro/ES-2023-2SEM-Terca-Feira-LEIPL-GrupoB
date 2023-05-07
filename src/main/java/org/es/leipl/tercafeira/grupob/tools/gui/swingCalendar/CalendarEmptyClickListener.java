package org.es.leipl.tercafeira.grupob.tools.gui.swingCalendar;

import java.util.EventListener;

/**
 * @author davejm
 * @version 1.10
 */

/**
 * Java Object for representation of an empty Calendar click event listener
 */
public interface CalendarEmptyClickListener extends EventListener {

    /**
     * empty calendar click listener
     * @param e - event
     */
    void calendarEmptyClick(CalendarEmptyClickEvent e);
}
