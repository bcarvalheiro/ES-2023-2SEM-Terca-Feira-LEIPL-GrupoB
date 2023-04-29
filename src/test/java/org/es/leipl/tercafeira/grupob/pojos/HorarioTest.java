package org.es.leipl.tercafeira.grupob.pojos;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class HorarioTest {

    Bloco b1 = new Bloco("ME","Teoria dos Jogos e dos Contratos","01789TP01","MEA1",30,"Seg",new Date("13:00:00"),new Date("14:30:00"),new Date("21/11/2022"),"AA2.25",34);
    Bloco b2 = new Bloco("PIUDHIST","Seminário de Projecto I (Piudhist)","SP-I_(Piudhist)S01","DHMCMG1",0,"Seg",new Date("18:00:00"),new Date("20:00:00"),new Date("03/10/2022"),"AA2.28",30);
    List<Bloco> aulasList = new ArrayList<>();

    Horario h = new Horario(aulasList);

    @Test
    public void testToString() {

        aulasList.add(b1);
        aulasList.add(b2);


        String expected = "Horário: \nAulas(s): \n";
        assertEquals(expected, h.toString());
    }

    @Test
    public void getAulasList() {

        aulasList.add(b1);
        aulasList.add(b2);


        assertEquals(aulasList, h.getAulasList());
    }
}