package org.es.leipl.tercafeira.grupob;

import com.opencsv.exceptions.CsvValidationException;
import net.fortuna.ical4j.data.ParserException;
import org.es.leipl.tercafeira.grupob.pojos.Bloco;
import org.es.leipl.tercafeira.grupob.tools.gui.GUI;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;


/**
 * @author GRUPO_B_LEI_PL
 * @version 0.0
 */
public class Main {
    public static void main(String[] args) throws IOException, ParserException, CsvValidationException {
        GUI.createGUI();
        GUI.showGUI();
       // Bloco bloco = new Bloco("LEI,MEI", "POO", "TP1", "A", 30, "Segunda", LocalTime.of(13, 00, 00), LocalTime.of(14, 30, 00), LocalDate.of(2022, 12, 02), "Sala 1", 40);
       // String actual = bloco.toJson().toJSONString();
       // System.out.println(actual);
    }
}