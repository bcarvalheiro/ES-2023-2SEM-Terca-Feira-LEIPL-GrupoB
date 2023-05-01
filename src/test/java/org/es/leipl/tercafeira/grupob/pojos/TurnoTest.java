package org.es.leipl.tercafeira.grupob.pojos;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TurnoTest {

    private final String nome = "turno1";
    private final int numeroInscritos = 50;
    private final List<String> cursosList = Arrays.asList("LEI", "IGE");
    private final List<String> turmasList = Arrays.asList("LEI_1", "LEI_2", "IGE_1");
    private final Turno emptyTurno =
            new Turno("", null, Integer.toString(numeroInscritos), null);

    private final Turno turno =
            new Turno(nome, turmasList, "p", cursosList);

    /**
    This test class tests an empty Turno object by asserting that its fields
     have the expected values.
     */
    @Test
    @DisplayName("Empty Turno Test")
    public void emptyTurnoTest() {
        assertAll("turno",
                () -> assertTrue(emptyTurno.getcursosList().isEmpty()),
                () -> assertTrue(emptyTurno.getTurmasList().isEmpty()),
                () -> assertNull(emptyTurno.getDesignacao()),
                () -> assertEquals(0, emptyTurno.getNumeroInscritos())
        );
    }

    /**
     Test class for creating a Turno object with a parsing error in the "numeroInscritos" parameter.
     */
    @Test
    @DisplayName("create Turno With numeroInscritos parsing error Test")
    public void createTurnoTest() {
        assertAll("turno",
                () -> assertEquals(nome, turno.getDesignacao()),
                () -> assertEquals(turmasList, turno.getTurmasList()),
                () -> assertEquals(cursosList, turno.getcursosList()),
                () -> assertEquals(-1, turno.getNumeroInscritos())
        );
    }

    /**
     Test cases to verify the behavior of the setNumeroInscritos method of the Turno class.
     Set the number of registered students
     Assert that the number of registered students was set correctly
     */
    @Test
    @DisplayName("setLotacao Test")
    public void setLotacaoTest() {
        emptyTurno.setNumeroInscritos(numeroInscritos);
       assertEquals(numeroInscritos, emptyTurno.getNumeroInscritos());
    }

    /**
     Test class for the method setNumeroInscritos in the Turno class.
     Set the number of enrolled students in the emptyTurno instance.
     Check if the method setNumeroInscritos successfully sets the number of enrolled students.
     */
    @Test
    @DisplayName("setDesignacaoTest")
    public void setDesignacaoTest() {
        emptyTurno.setDesignacao(nome);
        assertEquals(nome, emptyTurno.getDesignacao());
    }

    /**
     This unit test is used to test the method addCurso of the Turno class.
     It tests if a new course can be added to an empty Turno object and verifies if the
     getcursosList method returns a list containing the added course.
     Adding a new course to the emptyTurno object
     Verifying if the getcursosList method returns a list containing the added course
     */
    @Test
    @DisplayName("addCursoTest")
    public void addCursoTest() {
        emptyTurno.addCurso("LEI");
        assertEquals("LEI", emptyTurno.getcursosList().get(0));
    }

    /**
     This test case verifies that the addCursosList() method correctly adds a list of courses
     to a turno object. The getcursosList() method is used to check that the list of courses was
     correctly added to the object.
     */
    @Test
    @DisplayName("addCursosListTest")
    public void addCursosListTest() {
        emptyTurno.setCursosList(new ArrayList<>());
        emptyTurno.addCursosList(cursosList);
        assertEquals(cursosList, emptyTurno.getcursosList());
    }

    /**
     This method tests the addition of a "Turma" to the "Turno" object.
     It adds a "Turma" with the identifier "LEI_1" to an empty "Turno" object
     and asserts that the "Turma" was added correctly by checking the value of the
     first element in the "TurmasList".
     */
    @Test
    @DisplayName("addTurmaTest")
    public void addTurmaTest() {
        emptyTurno.addTurma("LEI_1");
        assertEquals("LEI_1", emptyTurno.getTurmasList().get(0));
    }

    /**
     This method tests the addTurmasList() method of the Turno class.
     It sets an empty list of turmas to a Turno object, then adds a list of turmas and checks if the list was correctly added.
     */
    @Test
    @DisplayName("addTurmasListTest")
    public void addTurmasListTest() {
        emptyTurno.setTurmasList(new ArrayList<>());
        emptyTurno.addTurmasList(turmasList);
        assertEquals(turmasList, emptyTurno.getTurmasList());
    }

}
