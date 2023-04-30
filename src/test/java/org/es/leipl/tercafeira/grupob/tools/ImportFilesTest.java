package org.es.leipl.tercafeira.grupob.tools;

import com.opencsv.CSVReader;
import org.es.leipl.tercafeira.grupob.pojos.Aula;
import org.es.leipl.tercafeira.grupob.pojos.Horario;
import org.es.leipl.tercafeira.grupob.tools.ImportFiles;
import org.json.simple.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

public class ImportFilesTest {



    @Test
    @DisplayName("Tests save a JSON to file")
    void saveJSONtoFile() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.add("test");
        ImportFiles.saveJSONtoFile(jsonArray);

        File file = new File("output.json");
        //em vez disto, usar o file 1 de fora
        assertTrue(file.exists());
        assertTrue(file.isFile());
        assertTrue(file.length() > 0 );
    }

    @Test
    @DisplayName("Tests converting a CSV file to JSON")
    void CSVtoJSon() {

        File file = new File("src/test/resources/test.csv");
        JSONArray jsonArray = ImportFiles.CSVtoJSon(file.getAbsolutePath());

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

    @Test
    @DisplayName("Tests importing a CSV file")
    void CSVImport() {
        Horario horario = ImportFiles.CSVImport("C:\\Users\\HP\\IdeaProjects\\ES-2023-2SEM-Terca-Feira-LEIPL-GrupoB\\src\\main\\resources\\horario-exemplo.csv");
        assertNotNull(horario, "Horario object should not be null");
        assertEquals(0, horario.getAulasList().size());
    }
}
