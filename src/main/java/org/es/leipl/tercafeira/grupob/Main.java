package org.es.leipl.tercafeira.grupob;

import com.opencsv.exceptions.CsvValidationException;
import net.fortuna.ical4j.data.ParserException;
import org.es.leipl.tercafeira.grupob.tools.Webcal;
import org.es.leipl.tercafeira.grupob.tools.gui.GUI;
import java.io.IOException;


/**
 * @author GRUPO_B_LEI_PL
 * @version 0.0
 */
public class Main {
    public static void main(String[] args) throws IOException, ParserException, CsvValidationException {
        GUI.createGUI();
        GUI.showGUI();
        String url ="https://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=dadcl1@iscte.pt&password=7YMg7TEBxyjKL0saPhAR1FNmMUfXMSSR9kPCNo4nOOa84cRL7Fn9d277xDXbmAhdhbVGltb31RpnAx2AIw41ptVtHs5PlSx1Z9ASWrMVqZy3rqEsok5iVxwLewx72vQq";

        Webcal call = new Webcal(url);
        String result = call.startWebcall();
        System.out.println(result);
    }
}