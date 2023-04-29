package org.es.leipl.tercafeira.grupob.tools.gui.swingCalendar;

import org.es.leipl.tercafeira.grupob.pojos.Bloco;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public final class CalendarUtils {

    private static CalendarUtils INSTANCE;
    private static final String HEADER = "Data da aula,Hora início da aula,Hora fim da aula,Unidade Curricular";

    private CalendarUtils() {}

    /**
     * @return - The singleton instance
     */
    public static CalendarUtils getInstance() {
        if (INSTANCE == null)
            INSTANCE = new CalendarUtils();
        return INSTANCE;
    }

    /**
     * Converts one calendar event to a String to be used on a CSV file
     * @param event - A calendar event
     * @return - A String to be used on a CSV file
     */
    public String calendarEventToString(CalendarEvent event) {
        Bloco bloco = event.getBloco();
        return event.getDate() + "," + event.getStart() + "," + event.getEnd() + "," + event.getText();
    }

    /**
     * Converts a list of calendar events to a CSV file
     * @param events - The list of the calendar events
     * @param filePath - The path to save the file
     * @throws IOException - When the path doesn't exist
     * @throws IllegalArgumentException - When at least one of the parameters is null
     */
    public void calendarEventsToCsvFile(List<CalendarEvent> events, String filePath) throws IOException, IllegalArgumentException {
        if ( events == null || filePath == null || filePath.isEmpty())
            throw new IllegalArgumentException();
        FileWriter csvWriter = new FileWriter(filePath);
        csvWriter.append(HEADER);
        for (CalendarEvent event : events) {
            csvWriter.append("\n");
            csvWriter.append(calendarEventToString(event));
        }
        csvWriter.flush();
        csvWriter.close();
    }

}
