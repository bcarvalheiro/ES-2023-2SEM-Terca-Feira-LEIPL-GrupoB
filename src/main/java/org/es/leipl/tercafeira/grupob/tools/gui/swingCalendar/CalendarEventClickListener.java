package org.es.leipl.tercafeira.grupob.tools.gui.swingCalendar;

import java.util.EventListener;

public interface CalendarEventClickListener extends EventListener {
    // Event dispatch methods
    void calendarEventClick(CalendarEventClickEvent e);
}
