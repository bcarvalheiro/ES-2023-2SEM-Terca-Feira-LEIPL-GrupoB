package org.es.leipl.tercafeira.grupob;

import com.opencsv.exceptions.CsvValidationException;
import net.fortuna.ical4j.data.ParserException;
import org.es.leipl.tercafeira.grupob.pojos.Bloco;
import org.es.leipl.tercafeira.grupob.tools.gui.GUI;
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
        //Bloco blocoTeste = new Bloco("LEI", "DIAM", "01789TP01", "MEA1", 30, "Sex", LocalTime.of(18, 0, 0, 0), LocalTime.of(19, 30, 0, 0), LocalDate.of(2023, 05, 05), "AA2.25", 34);
        //GUI.addEvent(blocoTeste);
        GUI.showGUI();
    }
}