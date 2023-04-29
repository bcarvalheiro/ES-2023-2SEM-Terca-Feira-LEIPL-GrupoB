package org.es.leipl.tercafeira.grupob.pojos;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class BlocoTest {

    Bloco bloco = new Bloco("LEI,MEI", "POO", "TP1", "A", 30, "Segunda", new Date("13:00:00"), new Date("14:30:00"), new Date("02/12/2022"), "Sala 1", 40);


    @Test
    public void testToString() {

        String expected = "Bloco: [LEI, MEI] POO TP1 30 Segunda " + "13:00:00" + " " + "14:30:00" + " " + "02/12/2022" + " " + " Sala 1 40\n";
        String actual = bloco.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void toJson() {

        String expected = "{\"Lotação da sala\":40,\"Inscritos no turno\":30,\"Sala atribuída à aula\":\"Sala 1\",\"Hora início da aula\":\"08:30\",\"Unidade Curricular\":\"POO\",\"Hora fim da aula\":\"10:00\",\"Data da aula\":\"2023-04-24\",\"Turma\":[\"A\"],\"Turno\":\"TP1\",\"Curso\":[\"LEI\",\"MEI\"],\"Dia da semana\":\"Segunda\"}";
        String actual = bloco.toJson().toJSONString();

        assertEquals(expected, actual);

    }
}