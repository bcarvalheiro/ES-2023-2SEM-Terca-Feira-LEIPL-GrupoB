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
    public Horario (List<Bloco> aulasList) {
        if (aulasList == null) {
            System.out.println("Horario not created, aulas list is null");
            return;
        }
        if (aulasList.isEmpty()) {
            System.out.println("Horario not created, aulas list is empty");
            return;
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