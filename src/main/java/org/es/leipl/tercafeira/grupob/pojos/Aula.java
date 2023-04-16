package org.es.leipl.tercafeira.grupob.pojos;

import java.util.Date;

/**
 * @author GRUPO_B_LEI_PL
 * @version 0.0
 */


/**
 * Class Aula object represents a school subject class
 */
public class Aula {

    /**
     * Atribute sala is the classroom where the class will be taking place
     */
    private Sala sala;
    /**
     * Atribute uc is the subject of the class
     */
    private UC uc;

    /**
     * Atribute turno agregates everyone
     * that is taking a particular class
     */
    private Turno turno;

    /**
     * Atribute hora_inicio is the hh:mm when the class starts
     */
    private Date horaInicio;

    /**
     * Atribute hora_fim the hh:mm when the class ends
     */
    private Date horaFim;

    /**
     * Atribute dia is the day of the class
     */
    private Date dia;

    public Aula(Sala sala, UC uc, Turno turno, Date horaInicio, Date horaFim, Date dia) {
        this.sala = sala;
        this.uc = uc;
        this.turno = turno;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.dia = dia;
    }

    /**
     * @return the classroom where the class will take place
     */
    public Sala getSala() {
        return sala;
    }

    /**
     * Sets the classroom
     *
     * @param sala
     */
    public void setSala(Sala sala) {
        this.sala = sala;
    }

    /**
     * @return the class shift
     */
    public Turno getTurno() {
        return turno;
    }

    /**
     * Sets the class shift
     *
     * @param turno
     */
    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    /**
     * @return the hh:mm when the class begins
     */
    public Date getHoraInicio() {
        return horaInicio;
    }

    /**
     * Sets the hh:mm when the class begins
     *
     * @param horaInicio
     */
    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * @return the hh:mm when the class ends
     */
    public Date getHoraFim() {
        return horaFim;
    }

    /**
     * Sets the hh:mm when the class ends
     *
     * @param horaFim
     */
    public void setHoraFim(Date horaFim) {
        this.horaFim = horaFim;
    }

    /**
     * @return the day of the class
     */
    public Date getDia() {
        return dia;
    }

    /**
     * Sets the day of the class
     *
     * @param dia
     */
    public void setDia(Date dia) {
        this.dia = dia;
    }

    /**
     * @return the subject of the class
     */
    public UC getUc() {
        return uc;
    }

    /**
     * Sets the subject of the class
     *
     * @param uc
     */
    public void setUc(UC uc) {
        this.uc = uc;
    }

    /**
     * @return a string representation of the object.
     * In general, the toString method returns a string that "textually represents" this object.
     */
    @Override
    public String toString() {
        String result = "Aula de " + uc + " em " + dia + " das " + horaInicio + " às " + horaFim + "horas:";
        result += "Sala: " + sala + "\n";
        result += turno;
        return result;
    }

}
