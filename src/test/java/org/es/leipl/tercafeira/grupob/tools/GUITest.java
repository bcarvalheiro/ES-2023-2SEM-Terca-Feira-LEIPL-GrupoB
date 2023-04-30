package org.es.leipl.tercafeira.grupob.tools;

import org.es.leipl.tercafeira.grupob.pojos.Bloco;
import org.es.leipl.tercafeira.grupob.tools.gui.GUI;
import org.es.leipl.tercafeira.grupob.tools.gui.swingCalendar.CalendarEvent;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GUITest {

    Bloco rightBloco = new Bloco("LEI", "POO", "TP1", "A", 30, "Segunda", LocalTime.of(13, 00, 00), LocalTime.of(14, 30, 00), LocalDate.of(2022, 12, 02), "Sala 1", 40);
    Bloco badBloco_emptyUC = new Bloco("LEI,MEI", "", "TP1", "A", 30, "Segunda", LocalTime.of(13, 00, 00), LocalTime.of(14, 30, 00), LocalDate.of(2022, 12, 02), "Sala 1", 40);
    Bloco badBloco_wrongStartHour = new Bloco("LEI,MEI", "POO", "TP1", "A", 30, "Segunda", LocalTime.of(07, 00, 00), LocalTime.of(14, 30, 00), LocalDate.of(2022, 12, 02), "Sala 1", 40);
    Bloco badBloco_wrongEndHour = new Bloco("LEI,MEI", "POO", "TP1", "A", 30, "Segunda", LocalTime.of(13, 00, 00), LocalTime.of(23, 30, 00), LocalDate.of(2022, 12, 02), "Sala 1", 40);

    /**
     * Tests the addEvent with a Bloco as argument
     * It has tree blocks that shouldn't be added to calendar
     * so calendarEventList lenght should be one
     */
    @Test(expected = AssertionError.class)
    public void addEvent() throws Exception {
        try {
            GUI.createGUI();
            //Test1: Add a valid block to succeed
            GUI.addEvent(rightBloco);
            ArrayList<CalendarEvent> calendarEventList = GUI.getCalendarEvents();
            assertEquals(1, calendarEventList.size());

            //Test2: Add a block with empty UC to fail
            GUI.addEvent(badBloco_emptyUC);
            fail("Expected an Exception to be thrown");
        } catch (Exception e) {
            assertEquals("Empty fields", e.getMessage());

            try {
                //Test3: add a block with wrong start hour to fail
                GUI.addEvent(badBloco_wrongStartHour);
                fail("Expected an Exception to be thrown");
            } catch (Exception e1) {
                assertEquals("Invalid start or end time", e1.getMessage());

                try {
                    //Test4 : Adding a block with an invalid end hour to fail
                    GUI.addEvent(badBloco_wrongEndHour);
                    fail("Expected an Exception to be thrown");

                } catch (Exception e2) {
                    assertEquals("Invalid start or end time", e2.getMessage());
                }
            }

        }
    }
}

