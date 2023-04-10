package org.es.leipl.tercafeira.grupob.Tools;
import com.opencsv.CSVReader;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import org.apache.commons.io.FilenameUtils;
import org.es.leipl.tercafeira.grupob.POJOS.Bloco;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.es.leipl.tercafeira.grupob.POJOS.Horario;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class ImportFiles {

    //Gets calendar from webcal link
    //saves in .ics format
    private static void getFromWebCal(String filePath) throws IOException, ParserException {
        URL url = new URL(filePath);
        URLConnection urlConn = url.openConnection();
        InputStream is = urlConn.getInputStream();
        CalendarBuilder calBuilder = new CalendarBuilder();
        Calendar calendar = calBuilder.build(is);
        File newCal = new File("calendar.ics");
        FileWriter fw = new FileWriter(newCal);
        fw.write(calendar.toString());
        fw.close();
    }

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
     * Saves a JSON object to a file named "output.json" in the current directory.
     * @param jsonList
     */
    public static void saveJSONtoFile(JSONArray jsonList) {
        try {
            FileWriter fw = new FileWriter("output.json");
            fw.write(jsonList.toJSONString());
            fw.close();
            System.out.println("JSON successfully saved");
        } catch (IOException e) {
            System.out.println("Error saving JSON");
            e.printStackTrace();
        }
    }
//TO-DO: CHANGE THE JAVADOC
    /**
     * Reads a CSV file from path and converts it to a JSON object,
     * using the first row of the CSV file as the keys for the JSON object.
     * The resulting JSON object is saved to a file named "output.json" in the current directory.
     *This methods doesn't uses POJO's
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
                //Parse CSV para o CSVReader
                reader = new CSVReader(new FileReader(f));
                String[] jsonProperties = reader.readNext();
                String[] nextLine;
                while((nextLine = reader.readNext()) != null){
                    SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss");
                    //Pode ser mais que um Curso
                    String curso = nextLine[0];
                    String UC = nextLine[1];
                    String turno = nextLine[2];
                    String turma = nextLine[3];
                    int inscritos = Integer.parseInt(nextLine[4]);
                    String diaSem = nextLine[5];
                    Date horaIni = dateFormat2.parse(nextLine[6]);
                    Date horaFim = dateFormat2.parse(nextLine[7]);
                    Date data = (nextLine.length > 8 && nextLine[8] != null && !nextLine[8].isEmpty()) ? dateFormat1.parse(nextLine[8]) : new Date(0);
                    String sala = (nextLine.length > 9 && nextLine[9] != null && !nextLine[9].isEmpty()) ? nextLine[9] : "";
                    int lotacao = (nextLine.length > 10 && nextLine[10] != null && !nextLine[10].isEmpty()) ? Integer.parseInt(nextLine[10]) : 0;
                    Bloco novoBloco = new Bloco(curso, UC, turno, turma, inscritos, diaSem, horaIni, horaFim, data, sala, lotacao);
                    blocosList.add(novoBloco);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        } else{
            System.out.println("File is not a CSV, can't convert to JSON");
        }
        System.out.println(blocosList.toString() + blocosList.size());
        Horario horario = new Horario(blocosList);
        return horario;
    }

    private static void getFromURL(String filePath) throws IOException {
        URL calendarURL = new URL(filePath);
        URLConnection calendarConn = calendarURL.openConnection();
    }

    private static void getFromLocalFile(File file) {    }

//    public void convertICStoCSV(File file) throws IOException, ParserException {
//        String csvFilePath = "calendarFromICS.csv";
//        CalendarBuilder calBuilder = new CalendarBuilder();
//        Calendar calendar = null;
//        try(FileReader fr = new FileReader(file)) {
//            calendar = calBuilder.build(fr);
//        }catch (ParserException e){
//            e.printStackTrace();
//            throw e;
//        }
//        FileWriter fw = new FileWriter(new File(csvFilePath));
//        Iterator<Component> components = calendar.getComponents().iterator();
//        while(components.hasNext()){
//            Component component = components.next();
//            PropertyList properties = component.getProperties();
//            StringBuilder sb = new StringBuilder();
//            for (Property p : properties){
//                sb.append(p.getName()).append(",").append(p.getValue()).append(",");
//            }
//            sb.deleteCharAt(sb.length()-1);
//            sb.append("\n");
//            fw.write(sb.toString());
//        }
//        fw.close();
//        }
    }
