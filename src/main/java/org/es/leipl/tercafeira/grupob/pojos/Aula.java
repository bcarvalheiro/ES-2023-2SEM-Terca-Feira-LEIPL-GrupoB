package org.es.leipl.tercafeira.grupob.pojos;

import java.util.Date;

//git test
public class Aula {
    private Sala sala;
    private UC uc;
    private Turno turno;
    private Date hora_inicio;
    private Date hora_fim;

    private Date dia;

    public Aula(Sala sala, UC uc, Turno turno, Date hora_inicio, Date hora_fim, Date dia) {
        this.sala = sala;
        this.uc = uc;
        this.turno = turno;
        this.hora_inicio = hora_inicio;
        this.hora_fim = hora_fim;
        this.dia = dia;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public Date getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(Date hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public Date getHora_fim() {
        return hora_fim;
    }

    public void setHora_fim(Date hora_fim) {
        this.hora_fim = hora_fim;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public UC getUc() {
        return uc;
    }

    public void setUc(UC uc) {
        this.uc = uc;
    }

    @Override
    public String toString() {
        String result = "Aula de " + uc + " em " + dia + " das " + hora_inicio + " às " + hora_fim + "horas:";
        result += "Sala: " + sala + "\n";
        result += turno;
        return result;
    }

}
