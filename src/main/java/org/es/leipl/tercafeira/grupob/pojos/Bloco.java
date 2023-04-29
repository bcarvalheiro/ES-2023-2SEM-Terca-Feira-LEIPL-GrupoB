package org.es.leipl.tercafeira.grupob.pojos;

import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
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
    private List<String> curso;

    /**
     * Atribute uc is the subject of the class
     */
    private String uc;

    /**
     * Name of the class shift that agregates everyone
     * that is taking a particular class
     */
    private String turno;

    /**
     * List of all classes that are taking this specific class
     */
    private List<String> turma;

    /**
     * Number of all students enrolled in this shift of the class
     */
    private int inscritos;

    /**
     * The day of the week that the class takes place
     */
    private String diaSemana;

    /**
     * Atribute hora_inicio is the hh:mm when the class starts
     */
    private LocalTime horaIni;

    /**
     * Atribute hora_fim the hh:mm when the class ends
     */
    private LocalTime horaFim;

    /**
     * Atribute data is the day of the class
     */
    private LocalDate data;

    /**
     * Atribute sala is the classroom where the class will take place
     */
    private String sala;

    /**
     * Atribute lotacao is the classroom student capacity
     */
    private int lotacao;

    public List<String> getCurso() {
        return curso;
    }

    public void setCurso(List<String> curso) {
        this.curso = curso;
    }

    public String getUc() {
        return uc;
    }

    public void setUc(String uc) {
        this.uc = uc;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public List<String> getTurma() {
        return turma;
    }

    public void setTurma(List<String> turma) {
        this.turma = turma;
    }

    public int getInscritos() {
        return inscritos;
    }

    public void setInscritos(int inscritos) {
        this.inscritos = inscritos;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public LocalTime getHoraIni() {
        return horaIni;
    }

    public void setHoraIni(LocalTime horaIni) {
        this.horaIni = horaIni;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public int getLotacao() {
        return lotacao;
    }

    public void setLotacao(int lotacao) {
        this.lotacao = lotacao;
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
