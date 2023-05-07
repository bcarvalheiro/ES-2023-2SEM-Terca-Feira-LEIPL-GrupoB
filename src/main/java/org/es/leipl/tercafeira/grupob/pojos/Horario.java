package org.es.leipl.tercafeira.grupob.pojos;

import java.util.LinkedList;
import java.util.List;
/**
 * @author GRUPO_B_LEI_PL
 * @version 0.0
 */

/**
 * Java Object for representation of a student schedule
 */
public class Horario {
    /**
     * aulasList is the list of all classes in a student schedule
     */
    private List<Bloco> aulasList;

    public Horario () {
        aulasList = new LinkedList<>();
    };
    /**
     * Creates a new instance of the Horario class with the given list of aulas (classes).
     *
     * @param aulasList a list of Bloco objects representing the classes.
     * @throws IllegalArgumentException if the aulasList is null or empty.
     */
    public Horario (List<Bloco> aulasList) {
        if (aulasList == null) {
            throw new IllegalArgumentException("Aulas list cannot be null or empty");
        }
        if (aulasList.isEmpty()) {
            throw new IllegalArgumentException("Aulas list cannot be null or empty");
        }
        this.aulasList = aulasList;
    }

    public void addAula(Bloco aula) {
        aulasList.add(aula);
    }

    public void removeAulas(List<Bloco> aulas) {
        aulasList.removeAll(aulas);
    }

    /**
     * @return a string representation of the object.
     * In general, the toString method returns a string that "textually represents" this object.
     */
    @Override
    public String toString() {
        String result = "Horário: \n";
        result += "Aulas(s): \n";
        for (Bloco aula : aulasList) {
            result += aula;
        }

        return result;
    }
    /**
     * @return aulasList the list of classes
     */
    public List<Bloco> getAulasList() {
        return aulasList;
    }

}