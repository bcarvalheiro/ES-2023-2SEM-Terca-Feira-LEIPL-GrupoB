package org.es.leipl.tercafeira.grupob.pojos;


import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * The HorarioTest class is responsible for testing the Horario class, which represents a student's schedule of classes.
 * It is responsible for verifying if the methods implemented in the Horario class work correctly.
 */

public class HorarioTest {

    Bloco b1 = new Bloco("ME","Teoria dos Jogos e dos Contratos","01789TP01","MEA1",30,"Seg",LocalTime.of(13,00,00),LocalTime.of(14,30,00),LocalDate.of(2022,11,21),"AA2.25",34);
    Bloco b2 = new Bloco("PIUDHIST","Seminário de Projecto I (Piudhist)","SP-I_(Piudhist)S01","DHMCMG1",0,"Seg",LocalTime.of(18,00,00),LocalTime.of(20,00,00),LocalDate.of(2022,10,03),"AA2.28",30);
    List<Bloco> aulasList = new ArrayList<>();

    Horario h = new Horario(aulasList);


    /**
     * This method tests the toString() method of the Horario class.
     * It adds two blocks to the aulasList and compares the expected string with the actual result of calling the toString() method of the Horario instance.
     *
     */
    @Test
    public void testToString() {

        aulasList.add(b1);
        aulasList.add(b2);


        String expected = "Horário: \nAulas(s): \n";
        assertEquals(expected, h.toString());
    }

    /**
     * This method tests the getAulasList() method of the Horario class.
     * It adds two blocks to the aulasList and compares the expected aulasList with the actual result of calling the getAulasList() method of the Horario instance.
     *
     */
    @Test
    public void getAulasList() {

        aulasList.add(b1);
        aulasList.add(b2);


        assertEquals(aulasList, h.getAulasList());
    }
}