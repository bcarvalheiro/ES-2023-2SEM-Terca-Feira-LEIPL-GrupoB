package org.es.leipl.tercafeira.grupob;

import net.fortuna.ical4j.data.ParserException;
import org.es.leipl.tercafeira.grupob.pojos.Horario;
import org.es.leipl.tercafeira.grupob.tools.gui.GUI;
import org.json.simple.JSONArray;

import java.io.IOException;

import static org.es.leipl.tercafeira.grupob.tools.ImportFiles.*;

public class Main {
    public static void main(String[] args) throws IOException, ParserException {
        Horario horario = CSVImport("Horario.csv");
        JSONArray json = CSVtoJSon("Horario.csv");
        System.out.println(json);
        saveJSONtoFile(json);
        GUI.createGUI();
    }
}