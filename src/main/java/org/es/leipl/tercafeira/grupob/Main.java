package org.es.leipl.tercafeira.grupob;

import net.fortuna.ical4j.data.ParserException;
import org.es.leipl.tercafeira.grupob.Tools.FileUpload;
import org.es.leipl.tercafeira.grupob.Tools.ImportFiles;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ParserException {
        ImportFiles.CSVtoJSon("C:/Users/bruno/ES_SchedulePlanner/arquivo/Horario.csv");
    }
}