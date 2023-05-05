package org.es.leipl.tercafeira.grupob.tools.gui.swingCalendar;

import java.util.EventListener;

/**
 * @author davejm
 * @version 1.10
 */

/**
 * Java Object for representation of an Calendar click event listener
 */

public interface CalendarEventClickListener extends EventListener {
    void calendarEventClick(CalendarEventClickEvent e);
}
