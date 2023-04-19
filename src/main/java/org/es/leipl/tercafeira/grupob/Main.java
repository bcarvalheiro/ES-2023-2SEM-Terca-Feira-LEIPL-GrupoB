package org.es.leipl.tercafeira.grupob;

import com.opencsv.exceptions.CsvValidationException;
import net.fortuna.ical4j.data.ParserException;
import org.es.leipl.tercafeira.grupob.tools.gui.GUI;
import org.es.leipl.tercafeira.grupob.tools.gui.SwingCalendar.Calendar;
import org.es.leipl.tercafeira.grupob.tools.gui.SwingCalendar.CalendarEvent;
import org.es.leipl.tercafeira.grupob.tools.gui.SwingCalendar.DayCalendar;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * @author GRUPO_B_LEI_PL
 * @version 0.0
 */
public class Main {
    public static void main(String[] args) throws IOException, ParserException, CsvValidationException {
        //Horario horario = CSVImport("Horario.csv");
        //JSONArray json = CSVtoJSon("Horario.csv");
        //System.out.println(json);
        //saveJSONtoFile(json);
        GUI.createGUI();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);
        CalendarEvent calendarEvent = new CalendarEvent(LocalDate.now(), LocalTime.now(),
                LocalTime.now(), "Teste teste", Color.BLUE);

        ArrayList<CalendarEvent> calendarEvents = new ArrayList<>();
        calendarEvents.add(calendarEvent);

        // make the JFrame object visible
        frame.setVisible(true);
        //Calendar calendar = new WeekCalendar(calendarEvents);
        Calendar calendar = new DayCalendar(calendarEvents);
        frame.add(calendar);
        frame.setVisible(true);


    }
}