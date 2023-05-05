package org.es.leipl.tercafeira.grupob.tools;

import org.es.leipl.tercafeira.grupob.pojos.Bloco;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
/**
 * @author GRUPO_B_LEI_PL
 * @version 0.1
 */

/**
 * Java Object for representation of an csv utils
 * Aids on converting an uploaded CSV file to Calendar Events
 */
public final class CsvUtils {

    private static CsvUtils INSTANCE;
    private static final String HEADER = "Curso,Unidade Curricular,Turno,Turma,Inscritos no turno,Dia da semana,Hora início da aula,Hora fim da aula,Data da aula,Sala atribuída à aula,Lotação da sala";

    private CsvUtils() {}

    /**
     * @return - The singleton instance
     */
    public static CsvUtils getInstance() {
        if (INSTANCE == null)
            INSTANCE = new CsvUtils();
        return INSTANCE;
    }

    /**
     * Converts one calendar event to a String to be used on a CSV file
     * @param bloco - A calendar event
     * @return - A String to be used on a CSV file
     */
    private static String blocoToStringCsv(Bloco bloco) {
        return bloco.getCursosToString()
                + "," + bloco.getUc()
                + "," + bloco.getTurno()
                + "," + bloco.getTurmasToString()
                + "," + bloco.getInscritos()
                + "," + bloco.getDiaSemana()
                + "," + bloco.getHoraIniToString()
                + "," + bloco.getHoraFimToString()
                + "," + bloco.getDataToString()
                + "," + bloco.getSala()
                + "," + bloco.getLotacao()
                ;
    }

    /**
     * Converts a list of calendar events to a CSV file
     * @param blocos - The list of the calendar events
     * @param filePath - The path to save the file
     * @throws IOException - When the path doesn't exist
     * @throws IllegalArgumentException - When at least one of the parameters is null
     */
    public static void blocosToCsvFile(List<Bloco> blocos, String filePath) throws IOException, IllegalArgumentException {
        if ( blocos == null || filePath == null || filePath.isEmpty())
            throw new IllegalArgumentException();
        FileWriter csvWriter = new FileWriter(filePath);
        csvWriter.append(HEADER);
        for (Bloco bloco : blocos) {
            csvWriter.append("\n");
            csvWriter.append(blocoToStringCsv(bloco));
        }
        csvWriter.flush();
        csvWriter.close();
    }


}
