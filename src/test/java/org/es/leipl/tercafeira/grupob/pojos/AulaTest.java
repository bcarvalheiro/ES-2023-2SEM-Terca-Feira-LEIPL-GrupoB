package org.es.leipl.tercafeira.grupob.pojos;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AulaTest {
    private final int lotacao = 50;
    private final Sala sala = new Sala("Sala_1", Integer.toString(lotacao));

    private final List<String> cursosList = Arrays.asList("LEI", "IGE");
    private final List<String> turmasList = Arrays.asList("LEI_1", "LEI_2", "IGE_1");
    private final Turno turno =
            new Turno("Turno_1", turmasList, "p", cursosList);

    private final UC uc = new UC("UC_1", turno);

    Date date = new Date(System.currentTimeMillis());



    private final Aula aula = new Aula(sala, uc, turno, new Date(System.currentTimeMillis()),
            new Date((System.currentTimeMillis() + (10 * 60 * 1000))), new Date());

    private final Aula emptyAula = new Aula(null, null, null, null, null, null);
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
