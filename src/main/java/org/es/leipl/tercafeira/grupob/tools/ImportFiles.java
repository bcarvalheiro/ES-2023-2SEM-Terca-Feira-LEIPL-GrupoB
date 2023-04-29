package org.es.leipl.tercafeira.grupob.tools;
import com.opencsv.CSVReader;
import org.apache.commons.io.FilenameUtils;
import org.es.leipl.tercafeira.grupob.pojos.Bloco;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFileChooser;
import org.es.leipl.tercafeira.grupob.pojos.Horario;
import org.json.simple.JSONArray;


public class ImportFiles {
    /**
     * Checks the file extension
     * @param file
     * @return
     */
    private static String checkExtention(File file){
        String extention = FilenameUtils.getExtension(file.getAbsolutePath());
        return extention;
    }

    /**
     *Saves a JSONArray to a file. Opens a file chooser dialog to select a directory and file name to save the file.
     *If the user selects a file, the method writes the contents of the JSONArray to the file.
     *If an IOException occurs while writing to the file, the method prints a stack trace and outputs an error message to the console.
     @param jsonList the JSONArray to be saved to a file
     */
    public static void saveJSONtoFile(JSONArray jsonList) {
        //Create a new file chooser object
        JFileChooser fileChooser = new JFileChooser();
        //Set the file chooser to allow file selection and show the "Save" dialog
        fileChooser.setDialogTitle("Save file");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setApproveButtonText("Save");
        // Show the file chooser dialog and wait for the user to choose a directory and file name
        int result = fileChooser.showSaveDialog(null);
        String filePath = "";
        // If the user selects a directory and file name, get the path and do something with it
        if (result == JFileChooser.APPROVE_OPTION) {
            filePath = fileChooser.getSelectedFile().getPath();
            System.out.println("File path: " + filePath);
        }
        if (filePath != "" && filePath != null) {
            try {
                FileWriter fw = new FileWriter(filePath + ".json");
                fw.write(jsonList.toJSONString());
                fw.close();
                System.out.println("JSON successfully saved");
            } catch (IOException e) {
                System.out.println("Error saving JSON");
                e.printStackTrace();
            }
        }
    }

    /**
     * Reads a CSV file from path and converts it to a JSON object,
     * using the first row of the CSV file as the keys for the JSON object.
     * This methods doesn't uses POJO's
     * @param file the path to the CSV file to be converted
     *
     */
    public static JSONArray CSVtoJSon(String file){
        Horario horario = CSVImport(file);
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
    public static JSONArray Horario2Json(Horario horario){
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
     * Parses a CSV file containing information about academic courses, and creates a list of POJOs (Plain Old Java Objects)
     * representing the course schedule for a given period. The POJOs are then converted to a JSON object for further processing.
     *
     * @param file the path to the CSV file to be parsed
     * @return a JSON object representing the course schedule for the given period
     * @throws IOException if there is an error reading the CSV file
     */
    public static Horario CSVImport(String file){
        File f = new File(file);
        List<Bloco> blocosList = new LinkedList<>();
        if(checkExtention(f).equals("csv") || checkExtention(f).equals("CSV") || checkExtention(f).equals("txt")){
            CSVReader reader = null;
            try{
                reader = new CSVReader(new FileReader(f));
                String[] jsonProperties = reader.readNext();
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
                           //newww
                            throw new Exception("Empty fields");
                        }
                        Bloco novoBloco = new Bloco(curso, UC, turno, turma, inscritos, diaSem, horaIni, horaFim, data, sala, lotacao);
                        blocosList.add(novoBloco);
                    }catch (Exception e){
                        System.out.println("Error parsing CSV file on line : " + lineNumber + "");
                        //e.printStackTrace();
                        continue;
                    }
                }
            } catch (Exception e){
                System.out.println("Error reading CSV file");
                e.printStackTrace();
            }
        } else{
            System.out.println("File is not a CSV, can't convert to JSON");
        }
        Horario horario = new Horario(blocosList);
        return horario;
    }
}
