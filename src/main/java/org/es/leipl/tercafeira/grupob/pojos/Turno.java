package org.es.leipl.tercafeira.grupob.pojos;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GRUPO_B_LEI_PL
 * @version 0.0
 */

/**
 * This object (shift of the class) agregates everyone
 * that is taking a particular class/lesson
 */
public class Turno {

    /**
     * List of all classes that are taking this specific class
     */
    private List<String> turmasList = new ArrayList<>();

    /**
     * List of all courses that are taking this specific class
     */
    private List<String> cursosList = new ArrayList<>();

    /**
     * Name of this shift of the class
     */
    private String designacao;

    /**
     * Number of all students enrolled in this shift of the class
     */
    private int numeroInscritos;

    public Turno (String designacao, List<String> turmasList, String numeroInscritos, List<String> cursosList) {
        if (isNull(designacao, turmasList, numeroInscritos, cursosList)) {
            System.out.println("Turno not created, at least one argument is null");
        }
        else if (isEmpty(designacao, turmasList, numeroInscritos, cursosList)) {
            System.out.println("Turno not created, at least one argument is empty");
        }
        else {
            this.designacao = designacao;
            this.turmasList = turmasList;
            this.numeroInscritos = parseNumeroInscritos(numeroInscritos);
            this.cursosList = cursosList;
        }
    }



    /**
     * checks if any of the params are null
     *
     * @param designacao
     * @param turmasList
     * @param numeroInscritos
     * @param cursosList
     *
     * @return true if any of the params is null
     */

    private boolean isNull (String designacao, List<String> turmasList, String numeroInscritos, List<String> cursosList) {
        return designacao == null || turmasList == null || numeroInscritos == null || cursosList == null;
    }

    /**
     * checks if any of the params are empty
     *
     * @param designacao
     * @param turmasList
     * @param numeroInscritos
     * @param cursosList
     *
     * @return true if any of the params is null
     */

    private boolean isEmpty(String designacao, List<String> turmasList, String numeroInscritos, List<String> cursosList) {
        return designacao.trim().isEmpty() || turmasList.isEmpty() || numeroInscritos.trim().isEmpty() || cursosList.isEmpty();
    }

    /**
     * Tries to parse a string to Integer
     *
     * @param numeroInscritos
     *
     * @return The Integer value of the param or -1 if the String is not parsable
     */
    private int parseNumeroInscritos(String numeroInscritos) {

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

    /**
     * Adds a single class to the class list
     *
     * @param turma
     */
    public void addTurma(String turma) {

        this.turmasList.add(turma);
    }

    /**
     * Adds a class list to the class list
     *
     * @param turmasList
     */
    public void addTurmasList(List<String> turmasList) {

        this.turmasList.addAll(turmasList);
    }

    public List<String> getcursosList() {
        return cursosList;
    }

    /**
     * Adds a single course to the course list
     *
     * @param curso
     */
    public void addCurso(String curso) {

        this.cursosList.add(curso);
    }

    /**
     * Adds a course list to the courses list
     *
     * @param cursosList
     */
    public void addCursosList(List<String> cursosList) {

        this.cursosList.addAll(cursosList);
    }

    /**
     * @return the name of this class shift
     */
    public String getDesignacao() {
        return designacao;
    }

    /**
     * Sets the name of this class shift
     *
     * @param designacao
     */
    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    /**
     * @return number of students enroll in this class shift
     */
    public int getNumeroInscritos() {
        return numeroInscritos;
    }

    /**
     * Sets the number of enrolled students in this class shift
     *
     * @param numeroInscritos must be equal or greater than 0
     */
    public void setNumeroInscritos(int numeroInscritos) {
        if (numeroInscritos >= 0)
            this.numeroInscritos = numeroInscritos;
    }

    public void setTurmasList(List<String> turmasList) {
        this.turmasList = turmasList;
    }

    public void setCursosList(List<String> cursosList) {
        this.cursosList = cursosList;
    }

    /**
     * @return a string representation of the object.
     * In general, the toString method returns a string that "textually represents" this object.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Designacao: " + getDesignacao() + "\n");
        result.append("Inscritos: ").append(getNumeroInscritos()).append("\n");
        result.append("Turma(s): ");
        for (String turma : turmasList) {
            result.append(turma).append(",");
        }
        return result.toString();
    }
}
