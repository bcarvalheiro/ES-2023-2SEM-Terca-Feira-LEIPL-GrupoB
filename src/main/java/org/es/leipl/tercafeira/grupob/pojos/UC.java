package org.es.leipl.tercafeira.grupob.pojos;

import java.util.LinkedList;
import java.util.List;

/**
 * @author GRUPO_B_LEI_PL
 * @version 0.0
 */

/**
 * Java Object for representation of the class subject
 */
public class UC {

    /**
     * turnosList is the list of all the class shift that students can enroll for this subject
     */
    private List<Turno> turnosList = new LinkedList<>();

    /**
     * Name of this class subject
     */
    private String nome;

    public UC (String nome, Turno turno) {
        if (isNull(nome, turno)) {
            System.out.println("UC not created, at least one argument is null");
        }
        else if (nome.trim().isEmpty()) {
            System.out.println("UC not created, nome is empty");
        }
        else {
            this.nome = nome;
            turnosList.add(turno);
        }
    }

    /**
     * checks if any of the params are null
     *
     * @param nome
     * @param turno
     *
     * @return true if any of the params is null
     */
    private boolean isNull (String nome, Turno turno) {
        if (nome == null || turno == null) return true;
        return false;
    }

    /**
     * Adds a single class shift to the class shift list
     *
     * @param turno
     */
    public void addTurno(Turno turno) {
        this.turnosList.add(turno);
    }

    /**
     * Adds a class shift list to the class shift list
     *
     * @param turnosList
     */
    public void addTurnos(List<Turno> turnosList) {
        this.turnosList.addAll(turnosList);
    }

    public List<Turno> getTurnosList() {
        return turnosList;
    }

    public void setTurnosList(List<Turno> turnosList) {
        this.turnosList = turnosList;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return a string representation of the object.
     * In general, the toString method returns a string that "textually represents" this object.
     */
    @Override
    public String toString() {
        String result = "Nome: " + nome + "\n";
        result += "Turno(s): \n";
        for (Turno turno : turnosList) {
            result += turno;
        }

        return result;
    }


}
