package org.es.leipl.tercafeira.grupob.tools;
import com.opencsv.CSVReader;
import org.apache.commons.io.FilenameUtils;
import org.es.leipl.tercafeira.grupob.pojos.Bloco;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;


import org.es.leipl.tercafeira.grupob.pojos.Horario;
import org.json.simple.JSONArray;

/**
 * @author GRUPO_B_LEI_PL
 * @version 1.3
 */

public class ImportFiles {
    /**
     * Checks the file extension
     * @param file
     * @return String - extension
     */
    private static String checkExtension(File file){
        String extension = FilenameUtils.getExtension(file.getAbsolutePath());
        return extension;
    }

    /**
     *Saves a JSONArray to a file. Opens a file chooser dialog to select a directory and file name to save the file.
     *If the user selects a file, the method writes the contents of the JSONArray to the file.
     *If an IOException occurs while writing to the file, the method prints a stack trace and outputs an error message to the console.
     @param jsonList the JSONArray to be saved to a file
     */
    public static void saveJSONtoFile(JSONArray jsonList, String filePath) throws IOException {
        FileWriter fw = null;
        try {
            fw = new FileWriter(filePath);
            fw.write(jsonList.toJSONString());

            System.out.println("JSON successfully saved");
        } catch (IOException e) {
            System.out.println("Error saving JSON");
            e.printStackTrace();
        } finally {
            fw.close();
        }
    }

    /**
     * Reads a CSV file from path and converts it to a JSON object,
     * using the first row of the CSV file as the keys for the JSON object.
     * This method doesn't use POJO's
     * @param file the path to the CSV file to be converted
     *
     */
    public static JSONArray csvtojson(String file){
        Horario horario = csvImport(file);
        JSONArray jsonList = new JSONArray();
        for(Bloco aula : horario.getAulasList()){
            jsonList.add(aula.toJson());
        }
        return jsonList;
    }

    /**
     * Converts a given Horario object to a JSONArray.
     *
     * @param horario The Horario object to be converted.
     * @return A JSONArray containing the converted Horario object.
     * @throws IllegalArgumentException if the given Horario object is null.
     */
    public static JSONArray horario2Json(Horario horario) throws IllegalArgumentException {
        if (horario == null) {
            throw new IllegalArgumentException("Horario object cannot be null.");
        }
        JSONArray jsonList = new JSONArray();
        for(Bloco aula : horario.getAulasList()){
            jsonList.add(aula.toJson());
        }
        return jsonList;
    }

    /**
     * Checks the extension of the file and calls the repective function
     * @param file the path and name of the file
     * @return a Horario object
     */
    public static Horario importFile(String file) {
        File f = new File(file);
        System.out.println(checkExtension(f).toLowerCase());
        if (checkExtension(f).toLowerCase().equals("csv"))
            return csvImport(file);
        else if (checkExtension(f).toLowerCase().equals("json"))
            return jsonImport(file);
        else
            return null;
    }

    /**
     * Parses a CSV file containing information about academic courses, and creates a list of POJOs (Plain Old Java Objects)
     * representing the course schedule for a given period. The POJOs are then converted to a JSON object for further processing.
     *
     * @param file the path to the CSV file to be parsed
     * @return a Horario object converted from the CSV File
     */
    public static Horario csvImport(String file){
        File f = new File(file);
        List<Bloco> blocosList = new LinkedList<>();
        if(checkExtension(f).equals("csv") || checkExtension(f).equals("CSV") || checkExtension(f).equals("txt")){
            CSVReader reader = null;
            try{
                reader = new CSVReader(new FileReader(f));
                reader.readNext();
                String[] nextLine;
                int lineNumber = 0;
                while((nextLine = reader.readNext()) != null){
                    lineNumber++;
                    try {
                        if (nextLine.length < 8) {
                            throw new Exception();
                        }
                        String curso = nextLine[0];
                        String UC = nextLine[1];
                        String turno = nextLine[2];
                        String turma = nextLine[3];
                        int inscritos = Integer.parseInt(nextLine[4]);
                        String diaSem = nextLine[5];
                        String[] horaIni_arr = nextLine[6].split(":");
                        String[] horaFim_arr = nextLine[7].split(":");
                        LocalTime horaIni = LocalTime.of(Integer.valueOf(horaIni_arr[0]), Integer.valueOf(horaIni_arr[1]),
                                Integer.valueOf(horaIni_arr[2]));
                        LocalTime horaFim = LocalTime.of(Integer.valueOf(horaFim_arr[0]), Integer.valueOf(horaFim_arr[1]),
                                Integer.valueOf(horaFim_arr[2]));
                        String[] data_arr = (nextLine.length > 8 && nextLine[8] != null && !nextLine[8].isEmpty()) ? nextLine[8].split("/") : new String[0];
                        LocalDate data = null;
                        if (data_arr.length == 3) {
                            data = LocalDate.of(Integer.valueOf(data_arr[2]), Integer.valueOf(data_arr[1]), Integer.valueOf(data_arr[0]));
                        }
                        String sala = (nextLine.length > 9 && nextLine[9] != null && !nextLine[9].isEmpty()) ? nextLine[9] : "";
                        int lotacao = (nextLine.length > 10 && nextLine[10] != null && !nextLine[10].isEmpty()) ? Integer.parseInt(nextLine[10]) : 0;
                        if (curso == null || UC == null || turno == null || turma == null || diaSem == null || data == null) {
                            throw new Exception("Empty fields");
                        }
                        Bloco novoBloco = new Bloco(curso, UC, turno, turma, inscritos, diaSem, horaIni, horaFim, data, sala, lotacao);
                        blocosList.add(novoBloco);
                    }catch (Exception e){
                        System.out.println("Error parsing CSV file on line : " + lineNumber + "");
                        continue;
                    }
                }
            } catch (Exception e){
                System.out.println("Error reading CSV file");
            }
        } else{
            System.out.println("File is not a CSV, can't convert to JSON");
        }
        Horario horario = new Horario(blocosList);

        return horario;
    }

    /**
     * Parses a JSON file containing information about academic courses, and creates a list of POJOs (Plain Old Java Objects)
     * representing the course schedule for a given period. The POJOs are then converted to a JSON object for further processing.
     * @param file the path to the json file to be parsed
     * @return a Horario object converted from the json file
     */
    public static Horario jsonImport(String file) {
        try (InputStream is = Files.newInputStream(Paths.get(file));
             JsonReader reader = Json.createReader(is)) {

            JsonArray aulasArray = reader.readArray();

            LinkedList<Bloco> blocos = new LinkedList<>();
            for (JsonObject aulaObj : aulasArray.getValuesAs(JsonObject.class)) {
                String curso = aulaObj.getString("Curso");
                String uc = aulaObj.getString("Unidade Curricular");
                String turno = aulaObj.getString("Turno");
                String turma = aulaObj.getString("Turma");
                int inscritos = aulaObj.getInt("Inscritos no turno");
                String diaSem = aulaObj.getString("Dia da semana");
                LocalDate data = LocalDate.parse(aulaObj.getString("Data da aula"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalTime horaIni = LocalTime.parse(aulaObj.getString("Hora início da aula"), DateTimeFormatter.ofPattern("HH:mm"));
                LocalTime horaFim = LocalTime.parse(aulaObj.getString("Hora fim da aula"), DateTimeFormatter.ofPattern("HH:mm"));
                String sala = aulaObj.getString("Sala atribuída à aula");
                int lotacao = aulaObj.getInt("Lotação da sala");
                Bloco novoBloco = new Bloco(curso, uc, turno, turma, inscritos, diaSem, horaIni, horaFim, data, sala, lotacao);
                blocos.add(novoBloco);
            }

            return new Horario(blocos);
        } catch (Exception e) {
            System.out.println("Error reading JSON file: " + e.getMessage());
        }
        return new Horario();
    }
}
