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

    @Test
    @DisplayName("setLotacao Test")
    public void setLotacaoTest() {
        emptyTurno.setNumeroInscritos(numeroInscritos);
       assertEquals(numeroInscritos, emptyTurno.getNumeroInscritos());
    }

    @Test
    @DisplayName("setDesignacaoTest")
    public void setDesignacaoTest() {
        emptyTurno.setDesignacao(nome);
        assertEquals(nome, emptyTurno.getDesignacao());
    }

    @Test
    @DisplayName("addCursoTest")
    public void addCursoTest() {
        emptyTurno.addCurso("LEI");
        assertEquals("LEI", emptyTurno.getcursosList().get(0));
    }

    @Test
    @DisplayName("addCursosListTest")
    public void addCursosListTest() {
        emptyTurno.setCursosList(new ArrayList<>());
        emptyTurno.addCursosList(cursosList);
        assertEquals(cursosList, emptyTurno.getcursosList());
    }

    @Test
    @DisplayName("addTurmaTest")
    public void addTurmaTest() {
        emptyTurno.addTurma("LEI_1");
        assertEquals("LEI_1", emptyTurno.getTurmasList().get(0));
    }

    @Test
    @DisplayName("addTurmasListTest")
    public void addTurmasListTest() {
        emptyTurno.setTurmasList(new ArrayList<>());
        emptyTurno.addTurmasList(turmasList);
        assertEquals(turmasList, emptyTurno.getTurmasList());
    }

}
