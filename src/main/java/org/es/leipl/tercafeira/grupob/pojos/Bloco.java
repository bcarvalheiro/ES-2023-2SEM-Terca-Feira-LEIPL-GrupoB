package org.es.leipl.tercafeira.grupob.pojos;

import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe de bloco de aulas
 * @author GRUPO_B_LEI_PL
 * @version 1.0
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

    /**
     * Main bloco constructor
     * @param uc - Class name
     * @param turno - turno
     * @param data - class data
     * @param horaIni - start time
     * @param horaFim - end time
     * @param sala - room
     */
    public Bloco(String uc, String turno, LocalDate data, LocalTime horaIni, LocalTime horaFim, String sala) {
        this.uc = uc;
        this.turno = turno;
        this.data = data;
        this.horaIni = horaIni;
        this.horaFim = horaFim;
        this.lotacao = 0;
        this.inscritos = 0;
        this.curso = new LinkedList<>();
        this.curso.add("");
        this.turma = new LinkedList<>();
        this.turma.add("");
        this.diaSemana="";
        this.sala = sala;
    }

    /**
     * Returns curso
     * @return List - list of cusro
     */
    public List<String> getCurso() {
        return curso;
    }

    /**
     * Sets curso
     * @param curso - curso to set
     */
    public void setCurso(List<String> curso) {
        this.curso = curso;
    }

    /**
     * Returns UC
     * @return String - returns uc
     */
    public String getUc() {
        return uc;
    }

    /**
     * Sets UC
     * @param uc - sets uc
     */
    public void setUc(String uc) {
        this.uc = uc;
    }

    /**
     * Returns turno
     * @return return - turno returned
     */
    public String getTurno() {
        return turno;
    }

    /**
     * Sets turno
     * @param turno - turno to set
     */
    public void setTurno(String turno) {
        this.turno = turno;
    }

    /**
     * Returns turma
     * @return turma - turma returned
     */
    public List<String> getTurma() {
        return turma;
    }

    /**
     * Sets turma
     * @param turma - turma to set
     */
    public void setTurma(List<String> turma) {
        this.turma = turma;
    }

    /**
     * Returns inscritos
     * @return inscritos - inscritos returned
     */
    public int getInscritos() {
        return inscritos;
    }

    /**
     * Sets inscritos
     * @param inscritos - inscritos to set
     */
    public void setInscritos(int inscritos) {
        this.inscritos = inscritos;
    }

    /**
     * Returns day of week
     * @return String  - day of week
     */
    public String getDiaSemana() {
        return diaSemana;
    }

    /**
     * Returns start time
     * @return horaIni - start time
     */
    public LocalTime getHoraIni() {
        return horaIni;
    }

    /**
     * Sets the start time
     * @param horaIni - start time to set
     */
    public void setHoraIni(LocalTime horaIni) {
        this.horaIni = horaIni;
    }

    /**
     * Returns start time
     * @return horaIni - start time
     */
    public LocalTime getHoraFim() {
        return horaFim;
    }

    /**
     * Sets the end time for class
     * @param horaFim - end time for class
     */
    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }

    /**
     * Return class date
     * @return date - class date
     */
    public LocalDate getData() {
        return data;
    }

    /**
     * Sets class date
     * @param  data - class date
     */
    public void setData(LocalDate data) {
        this.data = data;
    }

    /**
     * returns class room
     * @return  sala - class room
     */
    public String getSala() {
        return sala;
    }

    /**
     * Sets class room
     * @param sala - class room
     */
    public void setSala(String sala) {
        this.sala = sala;
    }

    /**
     * Returns lotação
     * @return lotacao - max number of students per class
     */
    public int getLotacao() {
        return lotacao;
    }

    /**
     * sets lotação
     * @param lotacao - max number of students per class
     */
    public void setLotacao(int lotacao) {
        this.lotacao = lotacao;
    }

    /**
     * String representation of cursos
     * @return String - string representation of a cursos
     */
    public String getCursosToString() {
        if (curso == null || curso.isEmpty()) {
            return "";
        }
        if (curso.size() == 1)
            return curso.get(0);

        StringBuilder sb = new StringBuilder();
        for (String c : curso) {
            sb.append(c.toUpperCase().trim()).append(", ");
        }

        sb.setLength(sb.length() - 2);

        return "\"" + sb + "\"";
    }

    /**
     * String representation of turmas
     * @return String - string representation of a turmas
     */
    public String getTurmasToString() {
        if (turma == null || turma.isEmpty()) {
            return "";
        }
        if (turma.size() == 1)
            return turma.get(0);

        StringBuilder sb = new StringBuilder();
        for (String t : this.turma) {
            sb.append(t.toUpperCase().trim()).append(", ");
        }

        sb.setLength(sb.length() - 2);

        return "\"" + sb + "\"";
    }

    /**
     * String representation of data
     * @return String - string representation of a data
     */
    public String getDataToString() {
        return data.getDayOfMonth() + "/" + data.getMonthValue() + "/" + data.getYear();
    }

    /**
     * String representation of hour start
     * @return String - string representation of a hours start
     */
    public String getHoraIniToString() {
        return horaIni.getHour() + ":" + horaIni.getMinute() + ":" + horaIni.getSecond();
    }

    /**
     * String representation of hour end
     * @return String - string representation of a hours end
     */
    public String getHoraFimToString() {
        return horaFim.getHour() + ":" + horaFim.getMinute() + ":" + horaFim.getSecond();
    }
    /**
     * Creates a new instance of the Bloco class with the specified parameters.
     *
     * @param curso     a string representing the course(s) associated with the block.
     * @param uc        a string representing the course unit (subject) associated with the block.
     * @param turno     a string representing the shift associated with the block.
     * @param turma     a string representing the class group associated with the block.
     * @param inscritos an integer representing the number of enrolled students in the block.
     * @param diaSemana a string representing the day of the week for the block.
     * @param horaIni   a LocalTime object representing the start time of the block.
     * @param horaFim   a LocalTime object representing the end time of the block.
     * @param data      a LocalDate object representing the date associated with the block.
     * @param sala      a string representing the room associated with the block.
     * @param lotacao   an integer representing the capacity/limit of the room.
     */
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
     * Adds turma to bloco
     * Converts object to Json format
     * @param turma - class to add
     */
    public void addTurma(String turma) {
        this.turma.add(turma);
    }

    /**
     * Adds curso to bloco
     * Converts object to Json format
     * @param curso - curso to add
     */
    public void addCurso(String curso) {
        this.curso.add(curso);
    }

    /**
     * @return a string representation of the object.
     * In general, the toString method returns a string that "textually represents" this object.
     */
    public String toString(){
        return "Bloco: " + curso + " " + uc + " " + turno + " " + inscritos + " " + diaSemana + " " + horaIni + " " + horaFim + " " + data + " " + sala + " " + lotacao + "\n";
    }

    /**
     * Converts object to Json format
     * @return a json representation of the object.
     */
    public JSONObject toJson() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        JSONObject json = new JSONObject();
        json.put("Inscritos no turno", this.inscritos);
        json.put("Hora início da aula", this.horaIni.format(timeFormatter));
        json.put("Lotação da sala", this.lotacao);
        json.put("Turma", this.turma.toString());
        json.put("Turno", this.turno);
        json.put("Hora fim da aula", this.horaFim.format(timeFormatter));
        json.put("Curso", this.curso.toString());
        json.put("Unidade Curricular", this.uc);
        json.put("Dia da semana", this.diaSemana);
        json.put("Data da aula", this.data.format(dateFormatter));
        json.put("Sala atribuída à aula", this.sala);
        return json;
    }
    /**
     * empty bloco construtor
     */
    public Bloco () {

    }
}
