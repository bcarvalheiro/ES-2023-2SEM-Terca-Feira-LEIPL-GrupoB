package org.es.leipl.tercafeira.grupob.POJOS;

import java.util.LinkedList;
import java.util.List;

public class Horario {
    private List<Aula> aulasList = new LinkedList<>();

    public Horario (List<Aula> aulasList) {
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
        for (Aula aula : aulasList) {
            result += aula;
        }

        return result;
    }

}
