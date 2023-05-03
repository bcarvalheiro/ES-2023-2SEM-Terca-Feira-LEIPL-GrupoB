package org.es.leipl.tercafeira.grupob.pojos;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UCTest {
    private final String nome = "JUnit Testing";

    private List<Turno> turnoList = new ArrayList<>();
    private final Turno turno = new Turno("turno1", null, "50",null);
    private final UC uc = new UC(nome, turno);

    private final UC emptyUC = new UC("", null);

    @Test
    @DisplayName("UC without nome is null")
    public void withoutNomeAndTurnoTest() {
        assertAll("uc",
                () -> assertNull(emptyUC.getNome()),
                () -> assertTrue(emptyUC.getTurnosList().isEmpty())
        );
    }

    @Test
    @DisplayName("UC creation Test")
    public void ucCreationTest() {
        assertAll("uc",
                () -> assertEquals(nome, uc.getNome()),
                () -> assertEquals(turno, uc.getTurnosList().get(0))
        );
    }

    @Test
    @DisplayName("UC setNome")
    public void ucsetNomeTest() {
        emptyUC.setNome(nome);
        assertEquals(nome, uc.getNome());
    }


    @Test
    @DisplayName("UC turno add")
    public void ucTurnoAddTest() {
        emptyUC.addTurno(turno);
        turnoList.add(turno);
        assertEquals(turnoList, uc.getTurnosList());
    }

    @Test
    @DisplayName("UC turno add")
    public void ucTurnosListAddTest() {
        uc.setTurnosList(new ArrayList<>());
        turnoList.add(turno);
        turnoList.add(turno);
        uc.addTurnos(turnoList);
        assertEquals(turnoList, uc.getTurnosList());
    }

}
