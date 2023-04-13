package org.es.leipl.tercafeira.grupob.services;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

/**
 * @author GRUPO_B_LEI_PL
 * @version 0.0
 */

public class CSVLoader {

    public static void main(String[] args) throws Exception {

        try {
            //Vai buscar o ficheiro csv
            FileReader filereader = new FileReader("src/main/resources/horario-exemplo.csv");
            //Lê o ficheiro csv fazendo a separação do conteúdo do mesmo com : ";"
            CSVParser parser = new CSVParserBuilder().withSeparator(',').build();

            CSVReader reader = new CSVReaderBuilder(filereader).withCSVParser(parser).build();

            List<String[]> nextLine = reader.readAll();
            nextLine.forEach(elem ->
                    System.out.println("Curso: " + elem[0] +
                            " Unidade Curricular: " + elem[1] +
                            " Turno: " + elem[2] +
                            " Turma: " + elem[3] +
                            " Inscritos no turno: " + elem[4] +
                            " Dia da semana: " + elem[5] +
                            " Hora início da aula: " + elem[6] +
                            " Hora fim da aula: " + elem[7] +
                            " Data da aula: " + elem[8] +
                            " Sala atribuída à aula: " + elem[9] +
                            " Lotação da sala" + elem[10])
            );
        }
        catch (FileNotFoundException e) {
            System.out.println("Ficheiro CSV não encontrado");
        }
    }
}

