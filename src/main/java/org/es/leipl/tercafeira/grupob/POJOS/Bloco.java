package org.es.leipl.tercafeira.grupob.POJOS;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Bloco {
    List<String> curso;
    String uc;
    String turno;
    List<String> turma;
    int inscritos;
    String diaSemana;
    Date horaIni;
    Date horaFim;
    Date data;
    String sala;
    int lotacao;

    public Bloco(String curso, String uc, String turno, String turma, int inscritos, String diaSemana, Date horaIni, Date horaFim, Date data, String sala, int lotacao) {
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

    public String toString(){
        return "Bloco: " + curso + " " + uc + " " + turno + " " + inscritos + " " + diaSemana + " " + horaIni + " " + horaFim + " " + data + " " + sala + " " + lotacao + "\n";
    }

}
