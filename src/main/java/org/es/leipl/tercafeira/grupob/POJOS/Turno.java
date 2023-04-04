package org.es.leipl.tercafeira.grupob.POJOS;

import java.util.LinkedList;
import java.util.List;

public class Turno {

    private List<String> turmasList;

    private List<Curso> cursosList;
    private String designacao;
    private int numeroInscritos;

    public Turno (String designacao, List<String> turmasList, String numeroInscritos) {
        if (isNull(designacao, turmasList, numeroInscritos)) {
            System.out.println("Turno not created, at least one argument is null");
            return;
        }
        if (isEmpty(designacao, turmasList, numeroInscritos)) {
            System.out.println("Turno not created, at least one argument is empty");
        }
        this.designacao = designacao;
        this.turmasList = turmasList;
        this.numeroInscritos = parseNumeroInscritos(numeroInscritos);
    }




    private boolean isNull (String designacao, List<String> turmasList, String numeroInscritos) {
        if (designacao == null || turmasList ==  null || numeroInscritos == null) return true;
        return false;
    }

    private boolean isEmpty(String designacao, List<String> turmasList, String numeroInscritos) {
        if (designacao.trim().isEmpty() || turmasList.isEmpty() || numeroInscritos.trim().isEmpty()) return true;
        return false;
    }


    private int parseNumeroInscritos(String numeroInscritos) {
        int result;
        try{
            return Integer.parseInt(numeroInscritos);
        }catch(NumberFormatException e) {
            System.out.println("Numero de Inscritos not parsable " + e);
            return -1;
        }
    }

    public List<String> getTurmasList() {
        return turmasList;
    }

    public void addTurma(String turma) {

        this.turmasList.add(turma);
    }

    public void addTurmasList(List<String> turmasList) {

        this.turmasList.addAll(turmasList);
    }

    public List<Curso> getcursosList() {
        return cursosList;
    }

    public void addCurso(Curso curso) {

        this.cursosList.add(curso);
    }

    public void addCursosList(List<Curso> cursosList) {

        this.cursosList.addAll(cursosList);
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public int getNumeroInscritos() {
        return numeroInscritos;
    }

    public void setNumeroInscritos(int numeroInscritos) {
        this.numeroInscritos = numeroInscritos;
    }

    @Override
    public String toString() {
        String result = "Designacao: " + getDesignacao() + "\n";
        result += "Inscritos: " + getNumeroInscritos() + "\n";
        result += "Turma(s): ";
        for (String turma : turmasList) {
            result += turma + ",";
        }
        return result;
    }
}
