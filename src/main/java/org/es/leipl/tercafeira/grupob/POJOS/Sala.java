package org.es.leipl.tercafeira.grupob.POJOS;

public class Sala {
    private String designacao;
    private String edificio;
    private int piso;
    private String identificadorSala;
    private int lotacao;

    public Sala (String designacao, String lotacao) {
        if (designacao == null || designacao.trim().isEmpty() || lotacao == null) {
            this.designacao = "Aguarda atribuição de sala";

        }
        else if (designacao.trim().isEmpty()) {
            this.designacao = "Aguarda atribuição de sala";
        }
        else {
            this.designacao = designacao;
            this.lotacao = Integer.parseInt(lotacao);
        }
    }

    private void parseFields() {
        //TODO
    }

    @Override
    public String toString() {
        //To Do
        return "Sala: " + designacao + " Capacidade: " + lotacao +"\n";
    }


}
