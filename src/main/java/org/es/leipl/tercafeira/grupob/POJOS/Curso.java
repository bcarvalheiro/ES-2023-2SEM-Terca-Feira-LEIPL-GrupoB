package org.es.leipl.tercafeira.grupob.POJOS;

import java.util.LinkedList;
import java.util.List;

public class Curso {
    private List<UC> ucList = new LinkedList<>();
    private String nome;

    public Curso (String nome, UC uc) {
        if (isNull(nome, uc)) {
            System.out.println("Curso not created, at least one argument is null");
            return;
        }
        if (nome.trim().isEmpty()) {
            System.out.println("Curso not created, nome is empty");
            return;
        }
        this.nome = nome;
        ucList.add(uc);
    }

    private boolean isNull (String nome, UC uc) {
        if (nome == null || uc == null) return true;
        return false;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void addUC(UC uc) {
        ucList.add(uc);
    }

    public void addUCList(List<UC> ucList) {
        this.ucList.addAll(ucList);
    }

    @Override
    public String toString() {
        String result = "Nome: " + nome + "\n";
        result += "UC(s): \n";
        for (UC uc : ucList) {
            result += uc;
        }
        return result;
    }
}
