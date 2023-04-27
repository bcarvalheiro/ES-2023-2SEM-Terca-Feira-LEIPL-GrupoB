package org.es.leipl.tercafeira.grupob.tools.gui.swingCalendar;


import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public final class CalendarUtils {

    private static CalendarUtils INSTANCE;
    private static final String HEADER = "Data da aula,Hora início da aula,Hora fim da aula,Unidade Curricular";

    private CalendarUtils() {}

    public static CalendarUtils getInstance() {
        if (INSTANCE == null)
            INSTANCE = new CalendarUtils();
        return INSTANCE;
    }


    public String calendarEventToString(CalendarEvent event, String delimiter) {
        return event.getDate() + delimiter + event.getStart() + delimiter + event.getEnd() + delimiter + event.getText();
    }

    public void calendarEventsToCsvFile(List<CalendarEvent> events, String filePath, String delimiter) throws IOException {
        FileWriter csvWriter = new FileWriter(filePath);
        csvWriter.append(HEADER);
        for (CalendarEvent event : events) {
            csvWriter.append("\n");
            csvWriter.append(calendarEventToString(event, delimiter));
        }
        csvWriter.flush();
        csvWriter.close();
    }

}
