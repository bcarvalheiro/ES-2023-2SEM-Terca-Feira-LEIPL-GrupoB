package org.es.leipl.tercafeira.grupob.pojos;

import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

/**
 * @author GRUPO_B_LEI_PL
 * @version 0.0
 */

public class Bloco {
    /**
     * List of all courses that are taking this specific class
     */
    List<String> curso;

    /**
     * Atribute uc is the subject of the class
     */
    String uc;

    /**
     * Name of the class shift that agregates everyone
     * that is taking a particular class
     */
    String turno;

    /**
     * List of all classes that are taking this specific class
     */
    List<String> turma;

    /**
     * Number of all students enrolled in this shift of the class
     */
    int inscritos;

    /**
     * The day of the week that the class takes place
     */
    String diaSemana;

    /**
     * Atribute hora_inicio is the hh:mm when the class starts
     */
    LocalTime horaIni;

    /**
     * Atribute hora_fim the hh:mm when the class ends
     */
    LocalTime horaFim;

    /**
     * Atribute data is the day of the class
     */
    LocalDate data;

    /**
     * Atribute sala is the classroom where the class will take place
     */
    String sala;

    /**
     * Atribute lotacao is the classroom student capacity
     */
    int lotacao;

    public String getUc() {
        return uc;
    }

    public String getTurno() {
        return turno;
    }

    public int getInscritos() {
        return inscritos;
    }

    public LocalTime getHoraIni() {
        return horaIni;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public LocalDate getData() {
        return data;
    }

    public String getSala() {
        return sala;
    }

    public Bloco(String curso, String uc, String turno, String turma, int inscritos, String diaSemana, LocalTime horaIni, LocalTime horaFim, LocalDate data, String sala, int lotacao) {
        this.curso = new LinkedList<>();
        for(String s: curso.split(",")){
            this.curso.add(s);
        }
        this.turma = new LinkedList<>();
        for(String t: turma.split(",")){
            this.turma.add(t);
        }
        this.uc = uc;
        this.turno = turno;
        this.inscritos = inscritos;
        this.diaSemana = diaSemana;
        this.horaIni = horaIni;
        this.horaFim = horaFim;
        this.data = data;
        this.sala = sala;
        this.lotacao = lotacao;
    }

    /**
     * @return a string representation of the object.
     * In general, the toString method returns a string that "textually represents" this object.
     */
    public String toString(){
        return "Bloco: " + curso + " " + uc + " " + turno + " " + inscritos + " " + diaSemana + " " + horaIni + " " + horaFim + " " + data + " " + sala + " " + lotacao + "\n";
    }

    /**
     * @return a json representation of the object.
     */
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Inscritos no turno", this.inscritos);
        json.put("Hora início da aula", this.horaIni);
        json.put("Lotação da sala", this.lotacao);
        json.put("Turma", this.turma);
        json.put("Turno", this.turno);
        json.put("Hora fim da aula", this.horaFim);
        json.put("Curso", this.curso);
        json.put("Unidade Curricular", this.uc);
        json.put("Dia da semana", this.diaSemana);
        json.put("Data da aula", this.data);
        json.put("Sala atribuída à aula", this.sala);
        return json;
    }
}
