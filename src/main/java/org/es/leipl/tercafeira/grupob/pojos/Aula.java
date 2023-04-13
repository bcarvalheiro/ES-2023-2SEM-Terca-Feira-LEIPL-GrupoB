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
    private Date hora_inicio;

    /**
     * Atribute hora_fim the hh:mm when the class ends
     */
    private Date hora_fim;

    /**
     * Atribute dia is the day of the class
     */
    private Date dia;

    public Aula(Sala sala, UC uc, Turno turno, Date hora_inicio, Date hora_fim, Date dia) {
        this.sala = sala;
        this.uc = uc;
        this.turno = turno;
        this.hora_inicio = hora_inicio;
        this.hora_fim = hora_fim;
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
    public Date getHora_inicio() {
        return hora_inicio;
    }

    /**
     * Sets the hh:mm when the class begins
     *
     * @param hora_inicio
     */
    public void setHora_inicio(Date hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    /**
     * @return the hh:mm when the class ends
     */
    public Date getHora_fim() {
        return hora_fim;
    }

    /**
     * Sets the hh:mm when the class ends
     *
     * @param hora_fim
     */
    public void setHora_fim(Date hora_fim) {
        this.hora_fim = hora_fim;
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
        String result = "Aula de " + uc + " em " + dia + " das " + hora_inicio + " às " + hora_fim + "horas:";
        result += "Sala: " + sala + "\n";
        result += turno;
        return result;
    }

}
