package org.es.leipl.tercafeira.grupob.POJOS;

import java.util.LinkedList;
import java.util.List;

public class Horario {
    private List<Bloco> aulasList = new LinkedList<>();

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


    @Override
    public String toString() {
        String result = "Horário: \n";
        result += "Aulas(s): \n";
        for (Bloco aula : aulasList) {
            result += aula;
        }

        return result;
    }

    public List<Bloco> getAulasList() {
        return aulasList;
    }

}
