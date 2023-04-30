package org.es.leipl.tercafeira.grupob.pojos;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class BlocoTest {

    Bloco bloco = new Bloco("LEI,MEI", "POO", "TP1", "A", 30, "Segunda", LocalTime.of(13, 00, 00), LocalTime.of(14, 30, 00), LocalDate.of(2022, 12, 02), "Sala 1", 40);


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

    @Test
    public void testGetUC() {
        Bloco bloco = new Bloco("LEI,MEI", "POO", "TP1", "A", 30, "Segunda", LocalTime.of(13, 00, 00), LocalTime.of(14, 30, 00), LocalDate.of(2022, 12, 02), "Sala 1", 40);
        assertEquals("POO", bloco.getUc());
    }

    @Test
    public void testGetTurno() {
        Bloco bloco = new Bloco("LEI,MEI", "POO", "TP1", "A", 30, "Segunda", LocalTime.of(13, 00, 00), LocalTime.of(14, 30, 00), LocalDate.of(2022, 12, 02), "Sala 1", 40);
        assertEquals("TP1", bloco.getTurno());
    }

    @Test
    public void testGetTurma() {
        Bloco bloco = new Bloco("LEI,MEI", "POO", "TP1", "A", 30, "Segunda", LocalTime.of(13, 00, 00), LocalTime.of(14, 30, 00), LocalDate.of(2022, 12, 02), "Sala 1", 40);
        assertEquals("A", bloco.getTurma());
    }

    @Test
    public void testGetInscritos() {
        Bloco bloco = new Bloco("LEI,MEI", "POO", "TP1", "A", 30, "Segunda", LocalTime.of(13, 00, 00), LocalTime.of(14, 30, 00), LocalDate.of(2022, 12, 02), "Sala 1", 40);
        assertEquals(30, bloco.getInscritos());
    }

    @Test
    public void testGetDiaSemana() {
        Bloco bloco = new Bloco("LEI,MEI", "POO", "TP1", "A", 30, "Segunda", LocalTime.of(13, 00, 00), LocalTime.of(14, 30, 00), LocalDate.of(2022, 12, 02), "Sala 1", 40);
        assertEquals("Segunda", bloco.getDiaSemana());
    }

    @Test
    public void testGetHoraInicio() {
        Bloco bloco = new Bloco("LEI,MEI", "POO", "TP1", "A", 30, "Segunda", LocalTime.of(13, 00, 00), LocalTime.of(14, 30, 00), LocalDate.of(2022, 12, 02), "Sala 1", 40);
        assertEquals(LocalTime.of(13, 00, 00), bloco.getHoraIni());
    }

    @Test
    public void testGetHoraFim() {
        Bloco bloco = new Bloco("LEI,MEI", "POO", "TP1", "A", 30, "Segunda", LocalTime.of(13, 00, 00), LocalTime.of(14, 30, 00), LocalDate.of(2022, 12, 02), "Sala 1", 40);
        assertEquals(LocalTime.of(14, 30, 00), bloco.getHoraFim());
    }

    @Test
    public void testGetData() {
        assertEquals(LocalDate.of(2022, 12, 02), bloco.getData());
    }

    @Test
    public void testGetSala() {
        assertEquals("Sala 1", bloco.getSala());
    }

    @Test
    public void testGetLotacao() {
        assertEquals(40, bloco.getLotacao());
    }

    @Test
    public void testSetHorarioIni() {
        bloco.setHoraIni(LocalTime.of(14, 00, 00));
        assertEquals(LocalTime.of(14, 00, 00), bloco.getHoraIni());
    }
}