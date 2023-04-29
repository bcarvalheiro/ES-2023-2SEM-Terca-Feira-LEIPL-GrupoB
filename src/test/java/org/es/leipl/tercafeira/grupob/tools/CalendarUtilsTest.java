package org.es.leipl.tercafeira.grupob.tools;

import org.es.leipl.tercafeira.grupob.pojos.Bloco;
import org.es.leipl.tercafeira.grupob.pojos.Horario;
import org.es.leipl.tercafeira.grupob.tools.gui.swingCalendar.Calendar;
import org.es.leipl.tercafeira.grupob.tools.gui.swingCalendar.CalendarEvent;
import org.es.leipl.tercafeira.grupob.tools.gui.swingCalendar.CalendarUtils;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalendarUtilsTest {

    CalendarUtils calendarUtils = CalendarUtils.getInstance();

    @Test
    @DisplayName("Tests saving a list of calendarEvent to a csv file")
    void testCalendarEventToCsv() throws IOException {
        Bloco bloco = new Bloco("LEI, IGE", "DIAM", "01789TP01", "MEA1", 30, "Sex", LocalTime.of(18, 0, 0, 0), LocalTime.of(19, 30, 0, 0), LocalDate.of(2023, 05, 05), "AA2.25", 34);
        Bloco bloco2 = new Bloco("LEI", "ES", "01789TP01", "MEA1, MEA2", 30, "Qui", LocalTime.of(18, 0, 0, 0), LocalTime.of(19, 30, 0, 0), LocalDate.of(2023, 05, 05), "AA2.25", 34);

        File file = new File("output.csv");
        calendarUtils.calendarEventsToCsvFile(Arrays.asList(bloco, bloco2), file.getPath());
        assertTrue(file.exists());
        assertTrue(file.isFile());
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String firstLine = br.readLine();
            assertEquals("Curso,Unidade Curricular,Turno,Turma,Inscritos no turno,Dia da semana,Hora início da aula,Hora fim da aula,Data da aula,Sala atribuída à aula,Lotação da sala", firstLine);
            String secondLine = br.readLine();
            assertEquals("\"LEI, IGE\",DIAM,01789TP01,MEA1,30,Sex,18:0:0,19:30:0,5/5/2023,AA2.25,34", secondLine);
            String thirdLine = br.readLine();
            assertEquals("LEI,ES,01789TP01,\"MEA1, MEA2\",30,Qui,18:0:0,19:30:0,5/5/2023,AA2.25,34", thirdLine);
        }

    }

}
