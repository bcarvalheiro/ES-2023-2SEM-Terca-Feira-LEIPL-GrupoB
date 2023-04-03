package org.es.leipl.tercafeira.grupob.POJOS;

import java.util.LinkedList;
import java.util.List;

public class UC {
    private List<Turno> turnosList = new LinkedList<>();
    private String nome;

    public UC (String nome, Turno turno) {
        if (isNull(nome, turno)) {
            System.out.println("UC not created, at least one argument is null");
            return;
        }
        if (nome.trim().isEmpty()) {
            System.out.println("UC not created, nome is empty");
            return;
        }
        this.nome = nome;
        turnosList.add(turno);
    }

    private boolean isNull (String nome, Turno turno) {
        if (nome == null || turno == null) return true;
        return false;
    }

    public void addTurno(Turno turno) {
        this.turnosList.add(turno);
    }

    public void addUCList(List<Turno> turnosList) {
        this.turnosList.addAll(turnosList);
    }

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
