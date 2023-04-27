package org.es.leipl.tercafeira.grupob;

import com.opencsv.exceptions.CsvValidationException;
import net.fortuna.ical4j.data.ParserException;
import org.es.leipl.tercafeira.grupob.tools.gui.GUI;

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
        GUI.addEvent(LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(2), "Teste teste");
        GUI.showGUI();
    }
}