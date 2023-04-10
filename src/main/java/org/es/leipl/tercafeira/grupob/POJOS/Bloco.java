package org.es.leipl.tercafeira.grupob.POJOS;

import org.json.simple.JSONObject;

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
