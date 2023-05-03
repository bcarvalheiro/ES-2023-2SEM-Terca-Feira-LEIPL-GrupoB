package org.es.leipl.tercafeira.grupob.pojos;

import org.es.leipl.tercafeira.grupob.pojos.Bloco;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class HorarioTest {

    /**
     * Tests the creation of a new Horario object with a non-empty list of Aulas.
     *
     * This test creates a new Horario object with a list of Aulas containing a single Bloco object.
     * It then checks that the list of Aulas in the Horario object is equal to the original list.
     *
     * The test also checks that an empty list and a null list can be used to create a Horario object.
     * In both cases, the resulting Horario object should have an empty list of Aulas.
     */
    @Test
    public void testHorarioCreation() {
        List<Bloco> aulasList = new ArrayList<>();
        Bloco b1 = new Bloco("ME","Teoria dos Jogos e dos Contratos","01789TP01","MEA1",30,"Seg",LocalTime.of(13,00,00),LocalTime.of(14,30,00),LocalDate.of(2022,11,21),"AA2.25",34);
        aulasList.add(b1);

        Horario h1 = new Horario(aulasList);
        assertEquals(aulasList, h1.getAulasList());

        // test empty list
        Horario h2 = new Horario(new ArrayList<>());
        assertTrue(h2.getAulasList().isEmpty());

        // test null list
        Horario h3 = new Horario(null);
        assertTrue(h3.getAulasList().isEmpty());
    }

    /**
     Test method for {@link Horario#toString()}.
     Tests the output of the toString() method for a Horario object.
     Creates a Horario object with two Bloco objects, and asserts that the output of the toString() method matches the expected string.
     Also tests the output of the toString() method for an empty Horario object.
     */
    @Test
    public void testToString() {
        List<Bloco> aulasList = new ArrayList<>();
        Bloco b1 = new Bloco("ME","Teoria dos Jogos e dos Contratos","01789TP01","MEA1",30,"Seg",LocalTime.of(13,00,00),LocalTime.of(14,30,00),LocalDate.of(2022,11,21),"AA2.25",34);
        Bloco b2 = new Bloco("PIUDHIST","Seminário de Projecto I (Piudhist)","SP-I_(Piudhist)S01","DHMCMG1",0,"Seg",LocalTime.of(18,00,00),LocalTime.of(20,00,00),LocalDate.of(2022,10,03),"AA2.28",30);
        aulasList.add(b1);
        aulasList.add(b2);

        Horario h = new Horario(aulasList);
        String expected = "Horário: \nAulas(s): \n" + b1.toString() + b2.toString();
        assertEquals(expected, h.toString());

        // test empty list
        Horario h2 = new Horario(new ArrayList<>());
        expected = "Horário: \nAulas(s): \n";
        assertEquals(expected, h2.toString());
    }
}
