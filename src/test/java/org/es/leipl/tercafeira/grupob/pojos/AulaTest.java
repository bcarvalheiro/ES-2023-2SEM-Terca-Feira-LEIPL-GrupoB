package org.es.leipl.tercafeira.grupob.pojos;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
This class tests the Aula class. It includes a test method that tests the getters and setters
 of the Aula class.
 The test method sets the values of Sala, UC, Turno, HoraInicio, HoraFim, and Dia for an emptpy
 Aula object, and then uses assertAll() to check if the values of each field are equal to the values
 previously set.
 */

public class AulaTest {
    private final int lotacao = 50;
    private final Sala sala = new Sala("Sala_1", Integer.toString(lotacao));

    private final List<String> cursosList = Arrays.asList("LEI", "IGE");
    private final List<String> turmasList = Arrays.asList("LEI_1", "LEI_2", "IGE_1");
    private final Turno turno =
            new Turno("Turno_1", turmasList, "p", cursosList);

    private final UC uc = new UC("UC_1", turno);

    Date date = new Date(System.currentTimeMillis());

    private final Aula emptyAula = new Aula(null, null, null, null, null, null);

    /**
     This method tests the getters and setters of the Aula class.
     It sets the values of Sala, UC, Turno, HoraInicio, HoraFim and Dia for an empty Aula object.
     Then, it uses assertAll to check if the values of each field are equal to the values previously set.
     */

    @Test
    @DisplayName("aula getters and setters")
    public void aulaGettersSettersTest() {
        emptyAula.setSala(sala);
        emptyAula.setUc(uc);
        emptyAula.setTurno(turno);
        emptyAula.setHoraInicio(date);
        emptyAula.setHoraFim(date);
        emptyAula.setDia(date);

        assertAll("aula",
                () -> assertEquals(sala, emptyAula.getSala()),
                () -> assertEquals(uc, emptyAula.getUc()),
                () -> assertEquals(turno, emptyAula.getTurno()),
                () -> assertEquals(date, emptyAula.getHoraInicio()),
                () -> assertEquals(date, emptyAula.getHoraFim()),
                () -> assertEquals(date, emptyAula.getDia())
        );
    }

}
