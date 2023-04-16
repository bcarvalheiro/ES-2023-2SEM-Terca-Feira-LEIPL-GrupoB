package org.es.leipl.tercafeira.grupob;

import com.opencsv.exceptions.CsvValidationException;
import net.fortuna.ical4j.data.ParserException;
import org.es.leipl.tercafeira.grupob.POJOS.Bloco;
import org.es.leipl.tercafeira.grupob.POJOS.Horario;
import org.es.leipl.tercafeira.grupob.Tools.FileUpload;
import org.es.leipl.tercafeira.grupob.Tools.ImportFiles;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.Scanner;

import static org.es.leipl.tercafeira.grupob.Tools.ImportFiles.*;

public class Main {
    public static void main(String[] args) throws IOException, ParserException, CsvValidationException {
        Horario horario = CSVImport("Horario.csv");
        JSONArray json = CSVtoJSon("Horario.csv");
        //System.out.println(json);
        saveJSONtoFile(json);


    }
}