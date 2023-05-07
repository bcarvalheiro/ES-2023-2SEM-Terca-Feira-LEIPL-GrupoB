package org.es.leipl.tercafeira.grupob.tools;

import net.fortuna.ical4j.data.ParserException;
import org.es.leipl.tercafeira.grupob.pojos.Horario;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ImportFilesTest {

    /**
     This method tests the functionality of saving a JSONArray to a file by calling the static method saveJSONtoFile of the ImportFiles class.
     A JSONArray object is created with one element, "test", and then passed to the saveJSONtoFile method which returns a String with the path of the file created.
     The method then verifies that the file exists, is a file, and has a non-zero length.
     */
    @Test
    @DisplayName("Tests save a JSON to file")
    void saveJSONtoFile() throws IOException {
        JSONArray jsonArray = new JSONArray();
        jsonArray.add("test");
        String filePath ="jsonForTestSaveFile.json";
        ImportFiles.saveJSONtoFile(jsonArray,"jsonForTestSaveFile.json");

        File file = new File(filePath);
        //em vez disto, usar o file 1 de fora
        assertTrue(file.exists());
        assertTrue(file.isFile());
        assertTrue(file.length() > 0 );
    }

    /**
     Converts a CSV file to a JSON array, where each object in the array represents a row in the CSV file.
     Each field in the CSV file becomes a key-value pair in the corresponding JSON object.
     The first row of the CSV file is assumed to contain the header, which becomes the keys of the JSON objects.
     @return a JSON array representing the CSV data
     */
    @Test
    @DisplayName("Tests converting a CSV file to JSON")
    void csvToJson() {

        File file = new File("horario.csv");
        JSONArray jsonArray = ImportFiles.csvtojson(file.getAbsolutePath());

        //verifica se é capaz de retornar um objeto JSON não nulo e não vazio.
        assertNotNull(jsonArray);
        assertTrue(jsonArray.size() > 0);
        assertFalse(jsonArray.isEmpty());


        JSONObject jsonObject = (JSONObject) jsonArray.get(0);
        assertNotNull(jsonObject.get("Curso"));
        assertNotNull(jsonObject.get("Unidade Curricular"));
        assertNotNull(jsonObject.get("Turno"));
        assertNotNull(jsonObject.get("Turma"));
        assertNotNull(jsonObject.get("Inscritos no turno"));
        assertNotNull(jsonObject.get("Dia da semana"));
        assertNotNull(jsonObject.get("Hora início da aula"));
        assertNotNull(jsonObject.get("Hora fim da aula"));
        assertNotNull(jsonObject.get("Data da aula"));
        assertNotNull(jsonObject.get("Sala atribuída à aula"));
        assertNotNull(jsonObject.get("Lotação da sala"));
    }

    /**
     * Test method to verify that the CSVImport method is able to import a CSV file and return a non-null Horario object.
     * The method also checks that the number of Aulas objects in the Horario object returned by the CSVImport method
     * matches the expected value.
     */
    @Test
    @DisplayName("Tests importing a CSV file")
    void csvImport() {

        Horario horario = ImportFiles.csvImport("TestCsv.csv");
        assertNotNull(horario, "Horario object should not be null");
        assertEquals(26003, horario.getAulasList().size());
        assertThrows(IllegalArgumentException.class, () -> ImportFiles.csvImport(""));
    }

    /**
     * Test method to verify that the CSVImport method is able to import a json file and return a non-null Horario object.
     * The method also checks that the number of Aulas objects in the Horario object returned by the jsonImport method
     * matches the expected value.
     */
    @Test
    @DisplayName("Tests importing a JSON file")
    void jsonImport() {
        Horario horario = ImportFiles.jsonImport("TestJson.json");
        assertNotNull(horario, "Horario object should not be null");
        assertEquals(26003, horario.getAulasList().size());
    }

    @Test
    void importICS() throws ParserException, IOException {
        Horario horario = ImportFiles.importICS(new File("TestICSCalendar.ics"));
        assertNotNull(horario, "Horario object should not be null");
        assertEquals(561, horario.getAulasList().size());
    }

    @Test
    public void downloadWebcal() throws IOException {
        File file = ImportFiles.downloadWebcal("webcal://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=bagco1@iscte.pt&password=Mg1cRSYIQA9HnZD5Xqe1t8cbpfRBcsyyoiVOc5e7YJyJplLBz2N5WwDY5uFpKIjnQHNbIiQpDae429ZTQDmAEUeWJNqbgY5MM3li3h4kCqV8ljWdCW1N8rH43jy1dant");
        assertNotNull(file);
        File file2 = ImportFiles.downloadWebcal("");
        assertNull(file2);
    }

    @Test
    public void testImportICS() throws IOException, ParserException {
        File file = ImportFiles.downloadWebcal("webcal://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=bagco1@iscte.pt&password=Mg1cRSYIQA9HnZD5Xqe1t8cbpfRBcsyyoiVOc5e7YJyJplLBz2N5WwDY5uFpKIjnQHNbIiQpDae429ZTQDmAEUeWJNqbgY5MM3li3h4kCqV8ljWdCW1N8rH43jy1dant");
        Horario horario = ImportFiles.importICS(file);
        assertNotNull(horario, "Horario object should not be null");
        assertEquals(531, horario.getAulasList().size());
    }
}
